package com.jrlescovar.appsenha;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PrincipalController {

    @FXML
    private TableView<Senha> tabelaSenhas;

    @FXML
    private TableColumn<Senha, String> colunaServico;

    @FXML
    private TableColumn<Senha, String> colunaUsuario;

    @FXML
    private TableColumn<Senha, String> colunaSenha;

    @FXML
    private Button btnNovaSenha;

    @FXML
    private Button btnVoltar;

    @FXML
    public void initialize() {
        colunaServico.setCellValueFactory(new PropertyValueFactory<>("servico"));
        colunaUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colunaSenha.setCellValueFactory(new PropertyValueFactory<>("senha")); // campo criptografado

        colunaSenha.setCellFactory(col -> new TableCell<Senha, String>() {
            @Override
            protected void updateItem(String senhaCriptografada, boolean empty) {
                super.updateItem(senhaCriptografada, empty);

                if (empty || senhaCriptografada == null) {
                    setText(null);
                    setTooltip(null);
                } else {
                    String senhaReal = CriptografiaUtil.descriptografarAES(senhaCriptografada);
                    setText("********");
                    setTooltip(new Tooltip(senhaReal));
                }
            }
        });

        btnNovaSenha.setOnAction(e -> {
            try {
                HelloApplication.mudarCena("nova_senha.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btnVoltar.setOnAction(e -> {
            try {
                HelloApplication.mudarCena("login.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Exibe apenas senhas do usuário logado
        List<Senha> todasSenhas = ArquivoUtil.carregarSenhas();
        List<Senha> minhasSenhas = todasSenhas.stream()
                .filter(s -> HelloApplication.usuarioLogado != null &&
                        HelloApplication.usuarioLogado.equalsIgnoreCase(s.getUsuarioDono()))
                .collect(Collectors.toList());

        tabelaSenhas.setItems(FXCollections.observableArrayList(minhasSenhas));
    }
}
