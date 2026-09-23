package br.edu.ifsc.fln.financas.model;

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
}
