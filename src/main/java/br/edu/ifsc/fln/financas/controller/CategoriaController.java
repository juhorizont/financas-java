package br.edu.ifsc.fln.financas.controller;

import br.edu.ifsc.fln.financas.HelloApplication;
import br.edu.ifsc.fln.financas.model.Categoria;
import br.edu.ifsc.fln.financas.model.Grupo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CategoriaController {

    @FXML private TextField tfNomeCategoria;
    @FXML private ComboBox<Grupo> cbSelecionarGrupo;
    @FXML private Button btSalvar;
    @FXML private ListView<Categoria> lvListaCategoria;

    // roda quando a tela abre
    @FXML
    public void initialize() {
        // preenche o combo com os tres grupos
        cbSelecionarGrupo.getItems().addAll(Grupo.values());
        // mostra as categorias que ja existem
        atualizarLista();
    }

    @FXML
    public void onSalvar() {
        String nome = tfNomeCategoria.getText();
        Grupo grupo = cbSelecionarGrupo.getValue();

        if (nome.isBlank()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atenção", "Informe o nome da categoria.");
            return;
        }
        if (grupo == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atenção", "Selecione um grupo.");
            return;
        }

        // cria e guarda a categoria (o id vem do DAO)
        Categoria categoria = new Categoria(0, nome, grupo);
        HelloApplication.categoriaDAO.inserir(categoria);

        mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Categoria cadastrada!");

        tfNomeCategoria.clear();
        cbSelecionarGrupo.setValue(null);
        atualizarLista();
    }

    @FXML
    public void onVoltar() {
        try {
            HelloApplication.trocarTela(btSalvar, "home-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // recarrega a lista a partir do DAO
    private void atualizarLista() {
        lvListaCategoria.getItems().clear(); //limpa a lista pra nao repetir as categorias quando carrega
        for (Categoria categoria : HelloApplication.categoriaDAO.listar()) { // pega as categorias guardadas (as fixas + as criadas)
            lvListaCategoria.getItems().add(categoria); //guarda a Categoria; a tela mostra "Nome (GRUPO)" pelo toString dela
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