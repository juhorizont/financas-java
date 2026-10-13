package br.edu.ifsc.fln.financas.controller;

import br.edu.ifsc.fln.financas.HelloApplication;
import br.edu.ifsc.fln.financas.model.Grupo;
import br.edu.ifsc.fln.financas.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class DashboardController {

    @FXML
    private Label lblRendaTotal;
    @FXML
    private Label lblTotalGasto;
    @FXML
    private Label lbValorNecessidades;
    @FXML
    private Label lbValorDesejos;
    @FXML
    private Label lbValorEconomias;
    @FXML
    private ProgressBar pbNecessidades;
    @FXML
    private ProgressBar pbDesejos;
    @FXML
    private ProgressBar pbEconomia;
    @FXML
    private PieChart pizza50;

    @FXML
    public void initialize() {
        Usuario usuario = HelloApplication.usuarioAtual;

        lblRendaTotal.setText("Renda total: R$ " + usuario.calcularTotalReceitas());

        // total gasto x renda: diz se sobrou ou se passou
        double sobraTotal = usuario.calcularSobra();
        if (sobraTotal >= 0) {
            lblTotalGasto.setText("Total gasto: R$ " + usuario.calcularTotalDespesas() + " (sobrou R$ " + sobraTotal + ")");
        } else {
            lblTotalGasto.setText("Total gasto: R$ " + usuario.calcularTotalDespesas() + " (passou R$ " + (-sobraTotal) + ")");
            lblTotalGasto.setStyle("-fx-text-fill: red;");
        }

        // ----- necessidades -----
        double limiteNec = usuario.calcularLimiteDoGrupo(Grupo.NECESSIDADE); // quanto pode gastar
        double gastoNec = usuario.calcularGastoDoGrupo(Grupo.NECESSIDADE); // quanto ja gastou
        lbValorNecessidades.setText("Necessidades: gastou R$ " + gastoNec + " de R$ " + limiteNec);
        pbNecessidades.setProgress(gastoNec / limiteNec);    // enche a barra (gasto dividido pelo limite)
        if (gastoNec > limiteNec) {
            lbValorNecessidades.setStyle("-fx-text-fill: red;");   // passou do limite: pinta de vermelho
            pbNecessidades.setStyle("-fx-accent: red;");
        }


        // ----- desejos -----
        double limiteDes = usuario.calcularLimiteDoGrupo(Grupo.DESEJO); // quanto pode gastar
        double gastoDes = usuario.calcularGastoDoGrupo(Grupo.DESEJO); // quanto ja gastou
        lbValorDesejos.setText("Desejos: gastou R$ " + gastoDes + " de R$ " + limiteDes);
        pbDesejos.setProgress(gastoDes / limiteDes);    // enche a barra (gasto dividido pelo limite)
        if (gastoDes > limiteDes) {
            lbValorDesejos.setStyle("-fx-text-fill: red;");   // passou do limite: pinta de vermelho
            pbDesejos.setStyle("-fx-accent: red;");
        }


        // ----- economia (aqui olha o que sobrou, nao o gasto) -----
        double limiteEco = usuario.calcularLimiteDoGrupo(Grupo.ECONOMIA);
        double sobra = usuario.calcularSobra();
        lbValorEconomias.setText("Economia: sobrou R$ " + sobra + " de R$ " + limiteEco);
        pbEconomia.setProgress(Math.max(0, sobra / limiteEco));
        if (sobra < limiteEco) {
            lbValorEconomias.setStyle("-fx-text-fill: red;");    // guardou menos que a meta: vermelho
            pbEconomia.setStyle("-fx-accent: red;");
        }

        pizza50.getData().add(new PieChart.Data("Necessidades", limiteNec));
        pizza50.getData().add(new PieChart.Data("Desejos", limiteDes));
        pizza50.getData().add(new PieChart.Data("Economia", limiteEco));
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