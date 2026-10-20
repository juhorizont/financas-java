package br.edu.ifsc.fln.financas.controller;
import br.edu.ifsc.fln.financas.model.Grupo;
import br.edu.ifsc.fln.financas.HelloApplication;
import br.edu.ifsc.fln.financas.model.Categoria;
import br.edu.ifsc.fln.financas.model.Despesa;
import br.edu.ifsc.fln.financas.model.StatusDespesa;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.time.LocalDate;

public class DespesaController {

    @FXML private TextField tfDescricao;
    @FXML private TextField tfValorDespesa;
    @FXML private DatePicker dpDataDespesa;
    @FXML private ComboBox<Categoria> cbCategoria;
    @FXML private ComboBox<StatusDespesa> cbStatus;
    @FXML private Button btSalvarDespesa;
    @FXML private ListView<Despesa> lvDespesas;
    @FXML private Label lblTotalDespesas;

    @FXML
    public void initialize() {
        // enche o combobox com as categorias do sistema (as fixas + criadas)
        cbCategoria.getItems().addAll(HelloApplication.categoriaDAO.listar());
        // enche o combobox com os status
        cbStatus.getItems().addAll(StatusDespesa.values());
        atualizarLista();
    }

    @FXML
    public void onSalvarDespesa() {
        String descricao = tfDescricao.getText();
        String textoValor = tfValorDespesa.getText();
        LocalDate data = dpDataDespesa.getValue();
        Categoria categoria = cbCategoria.getValue();
        StatusDespesa status = cbStatus.getValue();

        if (descricao.isBlank() || textoValor.isBlank()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atencao", "Preencha descricao e valor.");
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
            mostrarAlerta(Alert.AlertType.WARNING, "Atencao", "O valor deve ser maior que zero.");
            return;
        }

        if (categoria == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atencao", "Selecione a categoria.");
            return;
        }

        if (status == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atencao", "Selecione o status.");
            return;
        }

        if (data == null) {
            data = LocalDate.now();
        }

        int proximoId = HelloApplication.usuarioAtual.getDespesas().size() + 1;
        Despesa despesa = new Despesa(proximoId, descricao, valor, data, categoria, status);
        HelloApplication.usuarioAtual.adicionarDespesa(despesa);

        Grupo grupo = categoria.getGrupo();
        double gastoGrupo = HelloApplication.usuarioAtual.calcularGastoDoGrupo(grupo);
        double limiteGrupo = HelloApplication.usuarioAtual.calcularLimiteDoGrupo(grupo);

        if (gastoGrupo > limiteGrupo) {
            mostrarAlerta(Alert.AlertType.WARNING, "Limite ultrapassado",
                    "Voce passou o limite de " + grupo +
                            ".\nLimite: R$ " + limiteGrupo +
                            "\nGasto ate agora: R$ " + gastoGrupo);
        } else {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Despesa salva",
                    "Limite de " + grupo + ": R$ " + limiteGrupo +
                            "\nGasto ate agora: R$ " + gastoGrupo);
        }

        tfDescricao.clear();
        tfValorDespesa.clear();
        dpDataDespesa.setValue(null);
        cbCategoria.setValue(null);
        cbStatus.setValue(null);
        atualizarLista();
    }

    @FXML
    public void onVoltar() {
        try {
            HelloApplication.trocarTela(btSalvarDespesa, "home-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void atualizarLista() {
        lvDespesas.getItems().clear();
        for (Despesa despesa : HelloApplication.usuarioAtual.getDespesas()) {
            lvDespesas.getItems().add(despesa);
        }
        lblTotalDespesas.setText("Total das despesas: R$ " + HelloApplication.usuarioAtual.calcularTotalDespesas());
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}