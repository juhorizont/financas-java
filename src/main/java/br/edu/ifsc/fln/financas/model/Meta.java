package br.edu.ifsc.fln.financas.model;

// Classe Meta: representa um objetivo de economia (ex: juntar 6000 para uma viagem).
// Guarda o valor-alvo e o prazo, e calcula quanto guardar por mes para atingir a meta.

public class Meta {
    private int id;
    private String descricao;
    private double valorAlvo;
    private int prazoMeses;

    public Meta() {
    }

    public Meta(int id, String descricao, double valorAlvo, int prazoMeses) {
        this.id = id;
        this.descricao = descricao;
        this.valorAlvo = valorAlvo;
        this.prazoMeses = prazoMeses;
    }

    // O quanto ele precisa economizar por mes para atender a meta.
    public double calcularValorMensal() {
        if (prazoMeses <= 0) {
            return 0;
        }
        return valorAlvo / prazoMeses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorAlvo() {
        return valorAlvo;
    }

    public void setValorAlvo(double valorAlvo) {
        this.valorAlvo = valorAlvo;
    }

    public int getPrazoMeses() {
        return prazoMeses;
    }

    public void setPrazoMeses(int prazoMeses) {
        this.prazoMeses = prazoMeses;
    }
}