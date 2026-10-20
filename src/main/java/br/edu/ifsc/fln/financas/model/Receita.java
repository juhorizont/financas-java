package br.edu.ifsc.fln.financas.model;
import java.time.LocalDate;


//Receita está separando minha receita em dois tipos: FIXA OU EXTRA
//Receita está dizendo para qual grupo vai minha receita EXTRA. Quando for receita FIXA, ele entra como null, ou seja
// não seleciona para qual grupo vai, pois irá pegar a renda fixa para distribuição de calculo 50/30/20, se dizermos para qual
// grupo, ele só calcula para aquele grupo a distribuição de renda

public class Receita {
    private int id;
    private String nome;
    private double valor;
    private LocalDate data;
    private ReceitaTipo tipo;
    private Grupo grupo;

    //ex: renda fixa (salário): tipo FIXA, grupo não importa (null)
    //Receita salario = new Receita(1, "Salário", 3000.0, LocalDate.now(), ReceitaTipo.FIXA, null);

    //ex: ganho extra que vai todo pra economia: tipo EXTRA, grupo ECONOMIA
    //Receita freela = new Receita(2, "Freela", 500.0, LocalDate.now(), ReceitaTipo.EXTRA, Grupo.ECONOMIA);

    public Receita() {
    }

    public Receita(int id, String nome, double valor, LocalDate data, ReceitaTipo tipo, Grupo grupo) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.grupo = grupo;
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

    public ReceitaTipo getTipo() {
        return tipo;
    }

    public void setTipo(ReceitaTipo tipo) {
        this.tipo = tipo;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return nome + " - R$ " + valor + " (" + tipo + ")";
    }
}
