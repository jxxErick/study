package br.ce.wcaquino.servicos;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ce.wcaquino.exception.MovieWithoutStockException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.rules.ExpectedException;

import static br.ce.wcaquino.servicos.LocacaoService.ERR_MSG_MOVIE_EMPTY_LIST;
import static br.ce.wcaquino.servicos.LocacaoService.ERR_MSG_MOVIE_WITHOUT_STOCK;

public class LocacaoServiceTest {

    LocacaoService service = new LocacaoService();
    Usuario usuario = new Usuario();
    Filme movieWithStock1 = new Filme();
    Filme movieWithStock2 = new Filme();
    List<Filme> movieWithStock = new ArrayList<Filme>();
    Filme movieWithoutStock = new Filme();



    @Before
    public void setUp() {
        usuario.setNome("Usuario 1");

        movieWithStock1.setNome("Filme 1")
                .setEstoque(2)
                .setPrecoLocacao(5.0);

        movieWithoutStock.setNome("Filme 2")
                .setEstoque(0)
                .setPrecoLocacao(5.0);

        movieWithStock2.setNome("Filme 3")
                .setEstoque(1)
                .setPrecoLocacao(9.7);

        movieWithStock.add(movieWithStock1);
        movieWithStock.add(movieWithStock2);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

	@Test
	public void testMovieWithStock() {
		//cenario

		//acao
		Locacao locacao = service.alugarFilme(usuario, movieWithStock);
		
		//verificacao
		Assert.assertEquals(14.7, locacao.getValor(), 0.01);
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
    }

    @Test
    public void testMovieWithoutStock(){
        movieWithStock.add(movieWithoutStock);

        exception.expect(MovieWithoutStockException.class);
        exception.expectMessage(ERR_MSG_MOVIE_WITHOUT_STOCK);
        Locacao locacao = service.alugarFilme(usuario, movieWithStock);

        exception.expect(MovieWithoutStockException.class);
        exception.expectMessage(ERR_MSG_MOVIE_EMPTY_LIST);
        movieWithStock.clear();
        service.alugarFilme(usuario, movieWithStock);
    }
}
