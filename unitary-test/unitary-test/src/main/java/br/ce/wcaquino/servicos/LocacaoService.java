package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Date;
import java.util.List;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exception.MovieWithoutStockException;

public class LocacaoService {

    public static String ERR_MSG_MOVIE_WITHOUT_STOCK = "ESTOQUE INSUFICIENTE PARA FILME: ";

    public static String ERR_MSG_MOVIE_EMPTY_LIST = "DEVE SELECIONAR AO MENOS UM FILME";


	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) {

        if(filmes.isEmpty()){
            throw new MovieWithoutStockException(ERR_MSG_MOVIE_EMPTY_LIST);
        }

        if(filmes.stream().anyMatch(filme -> filme.getEstoque() <= 0)){
            throw new MovieWithoutStockException(ERR_MSG_MOVIE_WITHOUT_STOCK);
        }

		Locacao locacao = new Locacao();
		locacao.setFilmes(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filmes.stream()
                .mapToDouble(Filme::getPrecoLocacao)
                .sum());
		
		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}
}