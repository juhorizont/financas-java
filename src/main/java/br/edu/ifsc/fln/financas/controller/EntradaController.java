package br.edu.ifsc.fln.financas.controller;

import br.edu.ifsc.fln.financas.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EntradaController {

    @FXML
    private Button btCadastrar;

    @FXML
    public void onCadastrar() {
        try {
            HelloApplication.trocarTela(btCadastrar, "cadastro-usuario-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}