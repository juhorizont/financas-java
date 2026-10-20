package br.edu.ifsc.fln.financas.controller;
import javafx.scene.control.Label;
import br.edu.ifsc.fln.financas.HelloApplication;
import br.edu.ifsc.fln.financas.model.Grupo;
import br.edu.ifsc.fln.financas.model.Receita;
import br.edu.ifsc.fln.financas.model.ReceitaTipo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class ReceitaController {

    @FXML private TextField tfNomeRenda;
    @FXML private TextField tfValorRenda;
    @FXML private DatePicker dpDataReceita;
    @FXML private ComboBox<ReceitaTipo> cbTipo;
    @FXML private ComboBox<Grupo> cbGrupo;
    @FXML private Button btSalvarReceita;
    @FXML private ListView<Receita> lvReceitas;
    @FXML private Label lblTotalRendas;

    @FXML
    public void initialize() {
        // enche os combosbox
        cbTipo.getItems().addAll(ReceitaTipo.values());
        cbGrupo.getItems().addAll(Grupo.values());
        // mostra as receitas que o usuario ja tem (inclui as rendas fixas do cadastro)
        atualizarLista();
    }

    @FXML
    public void onSalvarReceita() {
        String nome = tfNomeRenda.getText();
        String textoValor = tfValorRenda.getText();
        ReceitaTipo tipo = cbTipo.getValue();
        Grupo grupo = cbGrupo.getValue();
        LocalDate data = dpDataReceita.getValue();

        if (nome.isBlank() || textoValor.isBlank()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atenção", "Preencha nome e valor.");
            return;
        }

        double valor;
        try {
            valor = Double.parseDouble(textoValor);
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Informe um valor valido (numero).");
            return;
        }

        if (valor <= 0) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atenção", "O valor deve ser maior que zero.");
            return;
        }

        if (tipo == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atenção", "Selecione o tipo (Fixa ou Extra).");
            return;
        }

        // regra: extra precisa de grupo; fixa nao usa grupo
        if (tipo == ReceitaTipo.EXTRA && grupo == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atenção", "Para renda extra, selecione o grupo.");
            return;
        }
        if (tipo == ReceitaTipo.FIXA) {
            grupo = null;
        }

        // se nao escolheu data, usa hoje
        if (data == null) {
            data = LocalDate.now();
        }

        int proximoId = HelloApplication.usuarioAtual.getReceitas().size() + 1;
        Receita receita = new Receita(proximoId, nome, valor, data, tipo, grupo);
        HelloApplication.usuarioAtual.adicionarReceita(receita);

        mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Receita adicionada!");

        tfNomeRenda.clear();
        tfValorRenda.clear();
        cbTipo.setValue(null);
        cbGrupo.setValue(null);
        dpDataReceita.setValue(null);
        atualizarLista();
    }

    @FXML
    public void onVoltar() {
        try {
            HelloApplication.trocarTela(btSalvarReceita, "home-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void atualizarLista() {
        lvReceitas.getItems().clear();
        for (Receita receita : HelloApplication.usuarioAtual.getReceitas()) {
            lvReceitas.getItems().add(receita);
        }
        lblTotalRendas.setText("Total das rendas: R$ " + HelloApplication.usuarioAtual.calcularTotalReceitas());
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}