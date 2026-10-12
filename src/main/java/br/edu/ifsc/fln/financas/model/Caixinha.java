package br.edu.ifsc.fln.financas.model;

public class Caixinha {
    private int id;
    private String nome;
    private double saldo;
    private Meta meta;

    public Caixinha() {
    }

    public Caixinha(int id, String nome, double saldo, Meta meta) {
        this.id = id;
        this.nome = nome;
        this.saldo = saldo;
        this.meta = meta;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo = saldo + valor;
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo = saldo - valor;
        }
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

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
