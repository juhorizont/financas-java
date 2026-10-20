package br.edu.ifsc.fln.financas.model;

import java.time.LocalDate;

// Classe Despesa: um gasto individual, com valor, data, categoria e situacao (status)
public class Despesa {
    private int id;
    private String descricao;
    private double valor;
    private LocalDate data;
    private Categoria categoria;
    private StatusDespesa status;

    public Despesa() {
    }

    public Despesa(int id, String descricao, double valor, LocalDate data, Categoria categoria, StatusDespesa status) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
        this.status = status;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public StatusDespesa getStatus() {
        return status;
    }

    public void setStatus(StatusDespesa status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return descricao + " - R$ " + valor;
    }
}