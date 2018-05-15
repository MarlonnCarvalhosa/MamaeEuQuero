package com.marlonncarvalhosa.mamaeeuquero.model;

public class Produto {
    private String id;
    private String nome;
    private String cat;
    private String preco;

    public Produto() {

    }

    public Produto(String id, String nome, String cat, String preco) {
        this.id = id;
        this.nome = nome;
        this.cat = cat;
        this.preco = preco;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}
