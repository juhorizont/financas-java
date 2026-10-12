package br.edu.ifsc.fln.financas.dao;

import br.edu.ifsc.fln.financas.model.Categoria;
import br.edu.ifsc.fln.financas.model.Grupo;

import java.util.ArrayList;
import java.util.List;

// DAO da Categoria: guarda as categorias em memoria e permite inserir e listar.
// Ja comeca com as categorias fixas do sistema
public class CategoriaDAO {
    private List<Categoria> categorias = new ArrayList<>();
    private int proximoId = 1;

    public CategoriaDAO() {
        // Necessidades
        inserir(new Categoria(0, "Moradia", Grupo.NECESSIDADE));
        inserir(new Categoria(0, "Alimentacao", Grupo.NECESSIDADE));
        inserir(new Categoria(0, "Transporte", Grupo.NECESSIDADE));
        inserir(new Categoria(0, "Saude", Grupo.NECESSIDADE));
        inserir(new Categoria(0, "Água", Grupo.NECESSIDADE));
        inserir(new Categoria(0, "Luz", Grupo.NECESSIDADE));
        inserir(new Categoria(0, "Internet", Grupo.NECESSIDADE));
        inserir(new Categoria(0, "Educacao", Grupo.NECESSIDADE));
        inserir(new Categoria(0, "Cartão de Crédito", Grupo.NECESSIDADE));
        inserir(new Categoria(0, "Boleto", Grupo.NECESSIDADE));
        inserir(new Categoria(0, "Academia", Grupo.NECESSIDADE));
        inserir(new Categoria(0, "Assinaturas", Grupo.NECESSIDADE));
        inserir(new Categoria(0, "Pets", Grupo.NECESSIDADE));

        // Desejos
        inserir(new Categoria(0, "Cosméticos", Grupo.DESEJO));
        inserir(new Categoria(0, "Bar", Grupo.DESEJO));
        inserir(new Categoria(0, "Ingressos", Grupo.DESEJO));
        inserir(new Categoria(0, "Restaurante", Grupo.DESEJO));
        inserir(new Categoria(0, "Vestuário", Grupo.DESEJO));
        inserir(new Categoria(0, "Viagens", Grupo.DESEJO));
        inserir(new Categoria(0, "Decoração", Grupo.DESEJO));
        inserir(new Categoria(0, "Móveis", Grupo.DESEJO));
        inserir(new Categoria(0, "Estética", Grupo.DESEJO));
        inserir(new Categoria(0, "Jogos", Grupo.DESEJO));
        inserir(new Categoria(0, "Presentes", Grupo.DESEJO));


        // Economia
        inserir(new Categoria(0, "Reserva de emergencia", Grupo.ECONOMIA));
        inserir(new Categoria(0, "Guardar para meta", Grupo.ECONOMIA));
    }

    public void inserir(Categoria categoria) {
        categoria.setId(proximoId);
        proximoId = proximoId + 1;
        categorias.add(categoria);
    }

    public List<Categoria> listar() {
        return categorias;
    }
}