package br.edu.ifsc.fln.financas.dao;

import br.edu.ifsc.fln.financas.model.Receita;

import java.util.ArrayList;
import java.util.List;

// DAO da Receita: guarda as receitas em memoria - listar e guardar

public class ReceitaDAO {
    private List<Receita> receitas = new ArrayList<>();
    private int proximoId = 1;

    public void inserir(Receita receita) {
        receita.setId(proximoId);
        proximoId = proximoId + 1;
        receitas.add(receita);
    }

    public List<Receita> listar() {
        return receitas;
    }
}