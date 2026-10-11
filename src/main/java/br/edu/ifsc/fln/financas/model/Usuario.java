package br.edu.ifsc.fln.financas.model;

public class Usuario {
    private int id;
    private String nome;
    private String CPF;
    private double rendaMedia;

    public Usuario(int id, String nome, String CPF, double rendaMedia) {
        this.id = id;          //o this serve para diferenciar o atributo da classe
        this.nome = nome;
        this.CPF = CPF;
        this.rendaMedia = rendaMedia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public double getRendaMedia() {
        return rendaMedia;
    }

    public void setRendaMedia(double rendaMedia) {
        this.rendaMedia = rendaMedia;
    }

    //para calcular a divisão 50/30/20 a partir da renda inserida pelo usuário
    public double calcularLimiteNecessidades() {
        return rendaMedia * 0.50;
    }

    public double calcularLimiteDesejos() {
        return rendaMedia * 0.30;
    }

    public double calcularLimitePoupanca() {
        return rendaMedia * 0.20;
    }
}