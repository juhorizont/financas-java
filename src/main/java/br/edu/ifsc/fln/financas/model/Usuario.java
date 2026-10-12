package br.edu.ifsc.fln.financas.model;

import java.util.List;
import java.util.ArrayList;

// Como o usuario usa o app.
// Guarda os dados dele e as listas de receitas, despesas, metas e caixinhas.
// A partir das receitas, calcula os limites do metodo 50/30/20

public class Usuario {
    private int id;
    private String nome;
    private String CPF;
    private List<Receita> receitas;
    private List<Despesa> despesas;
    private List<Meta> metas;
    private List<Caixinha> caixinhas;

    public Usuario() {
        this.receitas = new ArrayList<>();
        this.despesas = new ArrayList<>();
        this.metas = new ArrayList<>();
        this.caixinhas = new ArrayList<>();
    }

    public Usuario(int id, String nome, String CPF) {
        this.id = id;          //o this serve para diferenciar o atributo da classe
        this.nome = nome;
        this.CPF = CPF;
        this.receitas = new ArrayList<>();
        this.despesas = new ArrayList<>();
        this.metas = new ArrayList<>();
        this.caixinhas = new ArrayList<>();
    }

    // Soma o valor de todas as receitas do tipo FIXA (o salario).
    public double calcularRendaFixa() {
        double total = 0;
        for (Receita receita : receitas) {
            if (receita.getTipo() == ReceitaTipo.FIXA) {
                total = total + receita.getValor();
            }
        }
        return total;
    }

    // soma o valor dos ganhos extras destinados a um grupo (ex: so economia).
    public double calcularExtraDoGrupo(Grupo grupo) {
        double total = 0;
        for (Receita receita : receitas) {
            if (receita.getTipo() == ReceitaTipo.EXTRA && receita.getGrupo() == grupo) {
                total = total + receita.getValor();
            }
        }
        return total;
    }

    // limite do grupo = parte da renda fixa + os extras daquele grupo.
    public double calcularLimiteNecessidades() {
        return calcularRendaFixa() * 0.50 + calcularExtraDoGrupo(Grupo.NECESSIDADE);
    }

    public double calcularLimiteDesejos() {
        return calcularRendaFixa() * 0.30 + calcularExtraDoGrupo(Grupo.DESEJO);
    }

    public double calcularLimitePoupanca() {
        return calcularRendaFixa() * 0.20 + calcularExtraDoGrupo(Grupo.ECONOMIA);
    }

    // adicionam um item na lista do usuario.
    public void adicionarReceita(Receita receita) {
        receitas.add(receita);
    }

    public void adicionarDespesa(Despesa despesa) {
        despesas.add(despesa);
    }

    public void adicionarMeta(Meta meta) {
        metas.add(meta);
    }

    public void adicionarCaixinha(Caixinha caixinha) {
        caixinhas.add(caixinha);
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

    public List<Receita> getReceitas() {
        return receitas;
    }

    public List<Despesa> getDespesas() {
        return despesas;
    }

    public List<Meta> getMetas() {
        return metas;
    }

    public List<Caixinha> getCaixinhas() {
        return caixinhas;
    }
}