package com.jrlescovar.appsenha;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class EntrarController {

    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private Label erroUsuario;

    @FXML
    private Label erroSenha;

    @FXML
    private Button btnEntrar;

    @FXML
    private Button btnVoltar;

    @FXML
    private Label linkCriarConta;

    @FXML
    public void initialize() {
        // DEBUG: só pra testar se o clique funciona
        btnEntrar.setOnAction(e -> {
            System.out.println("Botão ENTRAR foi clicado!");
            fazerLogin();
        });
        btnVoltar.setOnAction(e -> {
            try {
                HelloApplication.mudarCena("login.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        linkCriarConta.setOnMouseClicked(e -> {
            try {
                HelloApplication.mudarCena("criar_usuario.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }

    private void fazerLogin() {
        String usuario = campoUsuario.getText().trim();
        String senha = campoSenha.getText().trim();

        System.out.println("Usuário digitado: " + usuario);
        System.out.println("Senha digitada: " + senha);

        Usuario u = UsuarioRepository.buscarUsuarioPorNome(usuario);

        if (u == null) {
            erroUsuario.setVisible(true);
            erroSenha.setVisible(false);
            System.out.println("Usuário não encontrado!");
        } else if (!CriptografiaUtil.hashSHA256(senha).equals(u.getSenhaCriptografada())) {
            erroUsuario.setVisible(false);
            erroSenha.setVisible(true);
            System.out.println("Senha incorreta!");
        } else {
            System.out.println("Login OK!");
            try {
                HelloApplication.usuarioLogado = usuario;
                HelloApplication.mudarCena("principal.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
