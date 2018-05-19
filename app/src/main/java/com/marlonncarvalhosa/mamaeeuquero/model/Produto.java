package com.marlonncarvalhosa.mamaeeuquero.model;

import android.widget.ImageView;

public class Produto {
    private String id;
    private String nome;
    private String cat;
    private String preco;
    private String local;
    private String descrição;

    public Produto() {

    }

    public Produto(String id, String nome, String cat, String preco , String local , String descrição , String data) {
        this.id = id;
        this.nome = nome;
        this.cat = cat;
        this.preco = preco;
        this.local = local;
        this.descrição = descrição;
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

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
