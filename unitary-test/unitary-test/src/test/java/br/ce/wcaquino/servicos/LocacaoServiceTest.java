package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exception.MovieWithoutStockException;
import br.ce.wcaquino.utils.DateUtils;
import builders.MovieBuilder;
import builders.UserBuilder;
import matchers.PropertyMatcher;
import matchers.SameDateMatcher;
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
import static builders.MovieBuilder.aMovie;
import static builders.UserBuilder.aUser;
import static matchers.PropertyMatcher.isSameDate;
import static org.hamcrest.MatcherAssert.assertThat;

public class LocacaoServiceTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private LocacaoService service;
    private Usuario usuario = aUser().now();

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

        movie1 = aMovie().now();
        movie2 = aMovie().now();
        movie3 = aMovie().now();
        movie4 = aMovie().now();
        movie5 = aMovie().now();
        movie6 = aMovie().now();

        movieWithoutStock = aMovie().withoutStock();
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

        assertThat(locacao.getDataLocacao(), isSameDate(new Date()));
    }

    @Test
    public void shouldSetReturnDateToNextDay() {
        Locacao locacao = service.alugarFilme(usuario, List.of(movie1));
        Assert.assertTrue(DateUtils.isSameDate(
                locacao.getDataRetorno(),
                DateUtils.getDateWithDayDifference(1)
        ));
    }

    // ─── Discount scenarios ──────────────────────────────────────────────────

    @Test
    public void shouldApplyNoDiscountForFirstMovie() {
        Locacao locacao = service.alugarFilme(usuario, List.of(movie1));

        Assert.assertEquals(
                calculateExpected(movie1),
                locacao.getValor(),
                0.01
        );
    }

    @Test
    public void shouldApplyNoDiscountForSecondMovie() {
        Locacao locacao = service.alugarFilme(usuario, List.of(movie1, movie2));

        Assert.assertEquals(
                calculateExpected(movie1, movie2),
                locacao.getValor(),
                0.01
        );
    }

    @Test
    public void shouldApply25PercentDiscountForThirdMovie() {
        Locacao locacao = service.alugarFilme(usuario, List.of(movie1, movie2, movie3));

        Assert.assertEquals(
                calculateExpected(movie1, movie2, movie3),
                locacao.getValor(),
                0.01
        );
    }

    @Test
    public void shouldApply50PercentDiscountForFourthMovie() {
        Locacao locacao = service.alugarFilme(usuario, List.of(movie1, movie2, movie3, movie4));

        Assert.assertEquals(
                calculateExpected(movie1, movie2, movie3, movie4),
                locacao.getValor(),
                0.01
        );
    }

    @Test
    public void shouldApply75PercentDiscountForFifthMovie() {
        Locacao locacao = service.alugarFilme(usuario, List.of(movie1, movie2, movie3, movie4, movie5));

        Assert.assertEquals(
                calculateExpected(movie1, movie2, movie3, movie4, movie5),
                locacao.getValor(),
                0.01
        );
    }

    @Test
    public void shouldApply100PercentDiscountForSixthMovie() {
        Locacao locacao = service.alugarFilme(usuario, List.of(movie1, movie2, movie3, movie4, movie5, movie6));

        Assert.assertEquals(
                calculateExpected(movie1, movie2, movie3, movie4, movie5, movie6),
                locacao.getValor(),
                0.01
        );
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


    private double calculateExpected(Filme... filmes) {
        double total = 0.0;

        for (int i = 0; i < filmes.length; i++) {
            double multiplier = switch (i) {
                case 2 -> filmes.length - 1 == i ? 0.75 : 1.0;
                case 3 -> filmes.length - 1 == i ? 0.50 : 1.0;
                case 4 -> filmes.length - 1 == i ? 0.25 : 1.0;
                case 5 -> filmes.length - 1 == i ? 0.00 : 1.0;
                default -> 1.0;
            };

            total += filmes[i].getPrecoLocacao() * multiplier;
        }

        return total;
    }
}