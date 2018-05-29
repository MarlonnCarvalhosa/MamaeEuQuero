package com.marlonncarvalhosa.mamaeeuquero.model;

import android.widget.ImageView;

import java.io.Serializable;

public class Produto implements Serializable{
    private String id;
    private String nome;
    private String cat;
    private String preco;
    private String local;
    private String descrição;
    private String pathImagem;
    private String imageUrl;
    private  String dataInicial;
    private String horarioInicial;
    private  int hora;
    private int minuto;
    private int dia ;

    public String getPathImagem() {
        return pathImagem;
    }

    public void setPathImagem(String pathImagem) {
        this.pathImagem = pathImagem;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Produto() {

    }

    public Produto(String id, String nome, String cat, String preco, String local, String descrição, String pathImagem, String imageUrl,String dataInicial,String horaInicial,int dia, int hora , int minuto) {
        this.id = id;
        this.nome = nome;
        this.cat = cat;
        this.preco = preco;
        this.local = local;
        this.descrição = descrição;
        this.pathImagem = pathImagem;
        this.imageUrl = imageUrl;
        this.dataInicial=dataInicial;
        this.horarioInicial=horaInicial;
        this.dia=dia;
        this.hora=hora;
        this.minuto=minuto;
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

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getHorarioInicial() {
        return horarioInicial;
    }

    public void setHorarioInicial(String horarioInicial) {
        this.horarioInicial = horarioInicial;
    }

    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getDia() {
        return dia;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

}
