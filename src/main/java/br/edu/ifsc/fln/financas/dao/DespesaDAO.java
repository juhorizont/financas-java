package br.edu.ifsc.fln.financas.dao;

import br.edu.ifsc.fln.financas.model.Despesa;

import java.util.ArrayList;
import java.util.List;

// DAO da Despesa: guarda as despesas em memoria

public class DespesaDAO {
    private List<Despesa> despesas = new ArrayList<>();
    private int proximoId = 1;

    public void inserir(Despesa despesa) {
        despesa.setId(proximoId);
        proximoId = proximoId + 1;
        despesas.add(despesa);
    }

    public List<Despesa> listar() {
        return despesas;
    }
}