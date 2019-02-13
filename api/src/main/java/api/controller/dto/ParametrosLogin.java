package api.controller.dto;

import javax.validation.constraints.NotBlank;

/**
 * Created by Gustavo Galvao on 03/08/2018.
 */
public class ParametrosLogin{
    @NotBlank
    String login;
    @NotBlank
    String senha;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

