package com.marlonncarvalhosa.mamaeeuquero.model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String id;
    private String nomeUsuario;
    private String dataNascimento;
    private String numeroCelular;
    private String email;
    private String senha;

    public Usuario(){}

    public Usuario (String id,String nomeUsuario,String dataNascimento,String numeroCelular,String email,String senha){
       this.id=id;
       this.nomeUsuario=nomeUsuario;
       this.dataNascimento=dataNascimento;
       this.numeroCelular=numeroCelular;
       this.email=email;
       this.senha=senha;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
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
