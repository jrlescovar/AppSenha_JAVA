package com.jrlescovar.appsenha;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoginController {

    @FXML
    private Button btnEntrar;

    @FXML
    private Button btnCriarUsuario;

    @FXML
    public void initialize() {
        btnEntrar.setOnAction(e -> abrirTelaEntrar());
        btnCriarUsuario.setOnAction(e -> abrirTelaCriarUsuario());
    }

    private void abrirTelaEntrar() {
        try {
            HelloApplication.mudarCena("entrar.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void abrirTelaCriarUsuario() {
        try {
            HelloApplication.mudarCena("criar_usuario.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
