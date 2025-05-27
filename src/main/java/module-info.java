module com.jrlescovar.appsenha {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;

    opens com.jrlescovar.appsenha to javafx.fxml;
    exports com.jrlescovar.appsenha;
}