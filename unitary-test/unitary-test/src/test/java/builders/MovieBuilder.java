package builders;

import br.ce.wcaquino.entidades.Filme;
import net.datafaker.Faker;


public class MovieBuilder {

    private static final Faker faker = new Faker();

    private Filme filme;

    private MovieBuilder() {
    }

    public static MovieBuilder aMovie() {
        MovieBuilder movieBuilder = new MovieBuilder();

        movieBuilder.filme = new Filme();
        movieBuilder.filme.setNome(faker.book().title());
        movieBuilder.filme.setEstoque(faker.number().numberBetween(1, 11));
        movieBuilder.filme.setPrecoLocacao(
                faker.number().randomDouble(2, 5, 30)
        );

        return movieBuilder;
    }

    public Filme now() {
        return filme;
    }

    public Filme withoutStock() {
        filme.setEstoque(0);
        return filme;
    }
}