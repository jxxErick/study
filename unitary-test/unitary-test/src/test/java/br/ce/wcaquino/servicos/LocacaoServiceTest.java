package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exception.MovieWithoutStockException;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static br.ce.wcaquino.servicos.LocacaoService.ERR_MSG_MOVIE_EMPTY_LIST;
import static br.ce.wcaquino.servicos.LocacaoService.ERR_MSG_MOVIE_WITHOUT_STOCK;

public class LocacaoServiceTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private LocacaoService service;
    private Usuario usuario;

    // Base movies with fixed price of 4.0 to make discount math easy to verify
    private Filme movie1;
    private Filme movie2;
    private Filme movie3;
    private Filme movie4;
    private Filme movie5;
    private Filme movie6;
    private Filme movieWithoutStock;

    @Before
    public void setUp() {
        service = new LocacaoService();

        usuario = new Usuario();
        usuario.setNome("User 1");

        movie1 = buildMovie("Movie 1", 4.0, 2);
        movie2 = buildMovie("Movie 2", 4.0, 2);
        movie3 = buildMovie("Movie 3", 4.0, 2);
        movie4 = buildMovie("Movie 4", 4.0, 2);
        movie5 = buildMovie("Movie 5", 4.0, 2);
        movie6 = buildMovie("Movie 6", 4.0, 2);

        movieWithoutStock = buildMovie("Movie Out of Stock", 4.0, 0);
    }

    // ─── Helper ──────────────────────────────────────────────────────────────

    private Filme buildMovie(String name, double price, int stock) {
        Filme f = new Filme();
        f.setNome(name).setPrecoLocacao(price).setEstoque(stock);
        return f;
    }

    // ─── Rental date tests ───────────────────────────────────────────────────

    @Test
    public void shouldSetRentalDateToToday() {
        Locacao locacao = service.alugarFilme(usuario, List.of(movie1));
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
    }

    @Test
    public void shouldSetReturnDateToNextDay() {
        Locacao locacao = service.alugarFilme(usuario, List.of(movie1));
        Assert.assertTrue(DataUtils.isMesmaData(
                locacao.getDataRetorno(),
                DataUtils.obterDataComDiferencaDias(1)
        ));
    }

    // ─── Discount scenarios ──────────────────────────────────────────────────

    @Test
    public void shouldApplyNoDiscountForFirstMovie() {
        Locacao locacao = service.alugarFilme(usuario, List.of(movie1));
        Assert.assertEquals(4.0, locacao.getValor(), 0.01);
    }

    @Test
    public void shouldApplyNoDiscountForSecondMovie() {
        Locacao locacao = service.alugarFilme(usuario, List.of(movie1, movie2));
        Assert.assertEquals(8.0, locacao.getValor(), 0.01);
    }

    @Test
    public void shouldApply25PercentDiscountForThirdMovie() {
        Locacao locacao = service.alugarFilme(usuario, List.of(movie1, movie2, movie3));
        Assert.assertEquals(11.0, locacao.getValor(), 0.01);
    }

    @Test
    public void shouldApply50PercentDiscountForFourthMovie() {
        Locacao locacao = service.alugarFilme(usuario, List.of(movie1, movie2, movie3, movie4));
        Assert.assertEquals(14.0, locacao.getValor(), 0.01);
    }

    @Test
    public void shouldApply75PercentDiscountForFifthMovie() {
        Locacao locacao = service.alugarFilme(usuario, List.of(movie1, movie2, movie3, movie4, movie5));
        Assert.assertEquals(17.0, locacao.getValor(), 0.01);
    }

    @Test
    public void shouldApply100PercentDiscountForSixthMovie() {
        Locacao locacao = service.alugarFilme(usuario, List.of(movie1, movie2, movie3, movie4, movie5, movie6));
        Assert.assertEquals(20.0, locacao.getValor(), 0.01);
    }

    // ─── Validation / exception tests ────────────────────────────────────────

    @Test
    public void shouldThrowExceptionWhenMovieListIsEmpty() {
        exception.expect(MovieWithoutStockException.class);
        exception.expectMessage(ERR_MSG_MOVIE_EMPTY_LIST);

        service.alugarFilme(usuario, List.of());
    }

    @Test
    public void shouldThrowExceptionWhenMovieHasNoStock() {
        exception.expect(MovieWithoutStockException.class);
        exception.expectMessage(ERR_MSG_MOVIE_WITHOUT_STOCK);

        service.alugarFilme(usuario, List.of(movie1, movieWithoutStock));
    }

    @Test
    public void shouldThrowExceptionWhenOnlyMovieHasNoStock() {
        exception.expect(MovieWithoutStockException.class);
        exception.expectMessage(ERR_MSG_MOVIE_WITHOUT_STOCK);

        service.alugarFilme(usuario, List.of(movieWithoutStock));
    }
}