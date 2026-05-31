package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exception.MovieWithoutStockException;
import br.ce.wcaquino.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static br.ce.wcaquino.utils.DateUtils.addDays;

public class LocacaoService {

    public static String ERR_MSG_MOVIE_WITHOUT_STOCK = "ESTOQUE INSUFICIENTE PARA FILME: ";

    public static String ERR_MSG_MOVIE_EMPTY_LIST = "DEVE SELECIONAR AO MENOS UM FILME";

    private static final Map<Integer, Double> DISCOUNT_BY_POSITION = Map.of(
            2, 0.75,  // 3º filme: 25% de desconto
            3, 0.50,  // 4º filme: 50% de desconto
            4, 0.25,  // 5º filme: 75% de desconto
            5, 0.00   // 6º filme em diante: gratuito
    );


    public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) {

        if (filmes == null || filmes.isEmpty()) {
            throw new MovieWithoutStockException(ERR_MSG_MOVIE_EMPTY_LIST);
        }

        Locacao locacao = new Locacao();
        locacao.setFilmes(filmes);
        locacao.setUsuario(usuario);
        locacao.setDataLocacao(new Date());

        double valorTotal = 0.0;
        for (int i = 0; i < filmes.size(); i++) {
            Filme filme = filmes.get(i);

            if (filme.getEstoque() <= 0) {
                throw new MovieWithoutStockException(ERR_MSG_MOVIE_WITHOUT_STOCK + filme.getNome());
            }

            boolean isUltimoFilme = (i == filmes.size() - 1);
            double multiplicador = isUltimoFilme
                    ? DISCOUNT_BY_POSITION.getOrDefault(i, 1.00)
                    : 1.0;

            valorTotal += filme.getPrecoLocacao() * multiplicador;
        }

        locacao.setValor(valorTotal);
        Date dateReturn = DateUtils
                .isDayOfWeek(new Date(), Calendar.SATURDAY)
                ? addDays(new Date(), 2)
                : addDays(new Date(), 1);
        locacao.setDataRetorno(dateReturn);

        return locacao;
    }
}