package br.edu.ifsc.fln.financas.controller;

import br.edu.ifsc.fln.financas.HelloApplication;
import br.edu.ifsc.fln.financas.model.Receita;
import br.edu.ifsc.fln.financas.model.ReceitaTipo;
import br.edu.ifsc.fln.financas.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class CadastroUsuarioController {

    @FXML private TextField tfNome;
    @FXML private TextField tfCpf;
    @FXML private TextField tfRenda;
    @FXML private Button btAdicionarRenda;
    @FXML private ListView<String> lvRendas;
    @FXML private Label lblTotal;
    @FXML private Button btCadastrar;

    // usuario que esta sendo montado; vai guardando as rendas fixas
    private Usuario usuario = new Usuario();
    private int proximoIdRenda = 1;

    // botao "Adicionar renda": soma mais uma renda fixa na lista
    @FXML
    public void onAdicionarRenda() {
        String textoRenda = tfRenda.getText();

        if (textoRenda.isBlank()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atenção", "Informe o valor da renda.");
            return;
        }

        double valor;
        try {
            valor = Double.parseDouble(textoRenda);
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Informe um valor valido (numero).");
            return;
        }

        if (valor <= 0) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atenção", "O valor deve ser maior que zero.");
            return;
        }

        // cria a renda fixa e guarda no usuario
        Receita renda = new Receita(proximoIdRenda, "Renda fixa", valor, LocalDate.now(), ReceitaTipo.FIXA, null);
        usuario.adicionarReceita(renda);
        proximoIdRenda = proximoIdRenda + 1;

        // mostra na lista e atualiza o total (usa o metodo do model)
        lvRendas.getItems().add("Renda fixa: R$ " + valor);
        lblTotal.setText("Total das rendas: R$ " + usuario.calcularRendaFixa());

        tfRenda.clear();
    }

    // botao "Concluir cadastro": fecha o cadastro e vai pra home
    @FXML
    public void onCadastrar() {
        String nome = tfNome.getText();
        String cpf = tfCpf.getText();

        if (nome.isBlank() || cpf.isBlank()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atenção", "Preencha nome e CPF.");
            return;
        }

        if (usuario.getReceitas().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atenção", "Adicione pelo menos uma renda fixa.");
            return;
        }

        usuario.setId(1);
        usuario.setNome(nome);
        usuario.setCPF(cpf);

        HelloApplication.usuarioAtual = usuario;

        mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Usuario cadastrado com sucesso!");

        try {
            HelloApplication.trocarTela(btCadastrar, "home-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}