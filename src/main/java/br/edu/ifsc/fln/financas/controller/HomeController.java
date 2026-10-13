package br.edu.ifsc.fln.financas.controller;

import br.edu.ifsc.fln.financas.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class HomeController {

    @FXML private Label lblBemVindo;

    // roda assim que a tela abre
    @FXML
    public void initialize() {
        if (HelloApplication.usuarioAtual != null) {
            lblBemVindo.setText("Bem-vinda, " + HelloApplication.usuarioAtual.getNome() + "!");
        }
    }

    @FXML
    public void onPainel() {
        try {
            HelloApplication.trocarTela(lblBemVindo, "dashboard-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onDespesas() {
        try {
            HelloApplication.trocarTela(lblBemVindo, "despesa-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void onReceitas() {
        try {
            HelloApplication.trocarTela(lblBemVindo, "receita-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void onCategorias() {
        try {
            HelloApplication.trocarTela(lblBemVindo, "categoria-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML public void onMetas() { emConstrucao(); }
    @FXML public void onCaixinhas() { emConstrucao(); }

    private void emConstrucao() {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Em construcao");
        alerta.setHeaderText(null);
        alerta.setContentText("Essa tela sera ligada no proximo passo.");
        alerta.showAndWait();
    }
}