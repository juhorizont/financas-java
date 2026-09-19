module br.edu.ifsc.fln.financas {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.edu.ifsc.fln.financas to javafx.fxml;
    exports br.edu.ifsc.fln.financas;
}