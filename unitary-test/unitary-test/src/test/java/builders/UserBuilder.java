package builders;

import br.ce.wcaquino.entidades.Usuario;

public class UserBuilder {

    private Usuario usuario;

    private UserBuilder() {

    }

    public static UserBuilder aUser() {
        UserBuilder user = new UserBuilder();
        user.usuario = new Usuario();
        user.usuario.setNome("Ana Flavia");
        return user;
    }

    public Usuario now(){
        return usuario;
    }



}
