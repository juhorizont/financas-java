package br.edu.ifsc.fln.financas.model;

// A categoria diz para qual grupo vai a despesa
// Categoria fixa = Categoria Mercado.

public class Categoria {

    private int id;
    private String nome;
    private Grupo grupo;

    public Categoria() {

    }

    public Categoria(int id, String nome, Grupo grupo) {
        this.id = id;
        this.nome = nome;
        this.grupo = grupo;
    }

    //getters e setter

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

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    //faz a categoria aparecer o nome, sem ele, o ComboBox mostraria algo tipo Categoria@1a2b3c
    @Override
    public String toString() {
        return nome + " (" + grupo + ")";
    }
}
