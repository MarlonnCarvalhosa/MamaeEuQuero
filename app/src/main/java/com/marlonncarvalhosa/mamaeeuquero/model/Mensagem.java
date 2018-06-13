package com.marlonncarvalhosa.mamaeeuquero.model;

public class Mensagem {
    private String nome;
    private String texto;
    private String id;
    private String uIdUsuario;


    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public Mensagem() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Mensagem(String nome, String texto, String id, String uIdUsuario) {
        this.nome = nome;
        this.texto = texto;
        this.id = id;
        this.uIdUsuario = uIdUsuario;
    }

    public String getuIdUsuario() {
        return uIdUsuario;
    }

    public void setuIdUsuario(String uIdUsuario) {
        this.uIdUsuario = uIdUsuario;
    }
}

