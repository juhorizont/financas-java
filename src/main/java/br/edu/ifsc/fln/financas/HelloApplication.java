package br.edu.ifsc.fln.financas;

import br.edu.ifsc.fln.financas.model.Usuario;
import javafx.application.Application;
import br.edu.ifsc.fln.financas.dao.CategoriaDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    // usuario cadastrado, compartilhado entre as telas
    public static Usuario usuarioAtual;
    public static CategoriaDAO categoriaDAO = new CategoriaDAO();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("entrada-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Finanças Pessoais");
        stage.setScene(scene);
        stage.show();
    }

    // troca a tela atual por outra (navegacao)
    public static void trocarTela(Node origem, String fxml) {
        try {
            Parent tela = FXMLLoader.load(HelloApplication.class.getResource("/br/edu/ifsc/fln/financas/" + fxml));
            Stage janela = (Stage) origem.getScene().getWindow();
            janela.setScene(new Scene(tela));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}