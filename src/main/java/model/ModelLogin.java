package model;

import java.io.Serializable;

public class ModelLogin implements Serializable {

    private Long id;
    private String nome;
    private String email;
    private String login;
    private String senha;

    public boolean isNovo(){
        if(this.id == null) {
            return true;
        } else if(this.id > 0) {
            return false;
        }
        return false;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
