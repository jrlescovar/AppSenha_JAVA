package com.jrlescovar.appsenha;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class CriarUsuarioController {

    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private PasswordField campoConfirmar;

    @FXML
    private Label erroUsuario;

    @FXML
    private Label erroSenha;

    @FXML
    private Label erroConfirmar;

    @FXML
    private Button btnCriar;
    @FXML
    private Button btnVoltar;

    @FXML
    public void initialize() {
        btnCriar.setOnAction(this::validarCampos);
        btnVoltar.setOnAction(e -> voltarParaLogin());
    }

    private void validarCampos(ActionEvent event) {
        boolean valido = true;

        // Limpar erros
        erroUsuario.setVisible(false);
        erroSenha.setVisible(false);
        erroConfirmar.setVisible(false);

        String usuario = campoUsuario.getText().trim();
        String senha = campoSenha.getText().trim();
        String confirmar = campoConfirmar.getText().trim();

        // Validar usuário
        if (usuario.length() <= 6 || usuario.length() >= 15) {
            erroUsuario.setText("USUARIO DEVE SER MAIOR QUE 6 E MENOR QUE 15 DIGITOS");
            erroUsuario.setVisible(true);
            valido = false;
        } else if (UsuarioRepository.usuarioExiste(usuario)) {
            erroUsuario.setText("USUÁRIO JÁ EXISTE");
            erroUsuario.setVisible(true);
            valido = false;
        }

        // Validar senha
        if (senha.length() <= 4 || senha.length() >= 15) {
            erroSenha.setText("SUA SENHA DEVE SER MAIOR QUE 4 E MENOR QUE 15 DIGITOS");
            erroSenha.setVisible(true);
            valido = false;
        }

        // Confirmar senha
        if (!senha.equals(confirmar)) {
            erroConfirmar.setText("SUAS SENHAS ESTÃO DIFERENTE!!");
            erroConfirmar.setVisible(true);
            valido = false;
        }

        if (valido) {
            String senhaCripto = CriptografiaUtil.hashSHA256(senha);
            Usuario novoUsuario = new Usuario(usuario, senhaCripto);
            UsuarioRepository.adicionarUsuario(novoUsuario);

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Conta Criada");
            alerta.setHeaderText(null);
            alerta.setContentText("CONTA CRIADA COM SUCESSO!");
            alerta.showAndWait();

            voltarParaLogin();
        }
    }

    private void voltarParaLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnCriar.getScene().getWindow();
            stage.setScene(new Scene(root, 900, 500));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
