package com.jrlescovar.appsenha;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class NovaSenhaController {

    @FXML
    private TextField campoServico;

    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnCancelar;

    @FXML
    public void initialize() {
        btnSalvar.setOnAction(e -> salvarSenha());
        btnCancelar.setOnAction(e -> {
            try {
                HelloApplication.mudarCena("principal.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void salvarSenha() {
        String servico = campoServico.getText().trim();
        String usuario = campoUsuario.getText().trim();
        String senhaPura = campoSenha.getText().trim();

        if (servico.isEmpty() || usuario.isEmpty() || senhaPura.isEmpty()) {
            System.out.println("Todos os campos devem ser preenchidos.");
            return;
        }

        String senhaCriptografada = CriptografiaUtil.criptografarAES(senhaPura);

        Senha novaSenha = new Senha(servico, usuario, senhaCriptografada, HelloApplication.usuarioLogado);
        ArquivoUtil.salvarSenha(novaSenha);

        System.out.println("Nova senha adicionada com sucesso!");
        try {
            HelloApplication.mudarCena("principal.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
