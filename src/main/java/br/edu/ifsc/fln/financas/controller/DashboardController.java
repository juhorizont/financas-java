package br.edu.ifsc.fln.financas.controller;

import br.edu.ifsc.fln.financas.HelloApplication;
import br.edu.ifsc.fln.financas.model.Grupo;
import br.edu.ifsc.fln.financas.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML
    private Label lblRendaTotal;
    @FXML
    private Label lbValorNecessidades;
    @FXML
    private Label lbValorDesejos;
    @FXML
    private Label lbValorEconomias;

    @FXML
    public void initialize() {
        Usuario usuario = HelloApplication.usuarioAtual;

        lblRendaTotal.setText("Renda total: R$ " + usuario.calcularTotalReceitas());

        double limiteNec = usuario.calcularLimiteDoGrupo(Grupo.NECESSIDADE);
        double gastoNec = usuario.calcularGastoDoGrupo(Grupo.NECESSIDADE);
        lbValorNecessidades.setText("Necessidades: gastou R$ " + gastoNec + " de R$ " + limiteNec);
        if (gastoNec > limiteNec) {
            lbValorNecessidades.setStyle("-fx-text-fill: red;");
        }

        double limiteDes = usuario.calcularLimiteDoGrupo(Grupo.DESEJO);
        double gastoDes = usuario.calcularGastoDoGrupo(Grupo.DESEJO);
        lbValorDesejos.setText("Desejos: gastou R$ " + gastoDes + " de R$ " + limiteDes);
        if (gastoDes > limiteDes) {
            lbValorDesejos.setStyle("-fx-text-fill: red;");
        }

        double limiteEco = usuario.calcularLimiteDoGrupo(Grupo.ECONOMIA);
        double sobra = usuario.calcularSobra();
        lbValorEconomias.setText("Economia: sobrou R$ " + sobra + " de R$ " + limiteEco);
        if (sobra < limiteEco) {
            lbValorEconomias.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void onLancarDespesa(ActionEvent event) {
        HelloApplication.trocarTela((Node) event.getSource(), "despesa-view.fxml");
    }

    @FXML
    public void onAdicionarReceita(ActionEvent event) {
        HelloApplication.trocarTela((Node) event.getSource(), "receita-view.fxml");
    }

    @FXML
    public void onVoltar(ActionEvent event) {
        HelloApplication.trocarTela((Node) event.getSource(), "home-view.fxml");
    }
}