package br.edu.ifsc.fln.financas.dao;

import br.edu.ifsc.fln.financas.model.Caixinha;

import java.util.ArrayList;
import java.util.List;

// DAO da Caixinha: guardar e listar

public class CaixinhaDAO {
    private List<Caixinha> caixinhas = new ArrayList<>();
    private int proximoId = 1;

    public void inserir(Caixinha caixinha) {
        caixinha.setId(proximoId);
        proximoId = proximoId + 1;
        caixinhas.add(caixinha);
    }

    public List<Caixinha> listar() {
        return caixinhas;
    }
}