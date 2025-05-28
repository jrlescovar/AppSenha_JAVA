package com.jrlescovar.appsenha;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class HelloApplication extends Application {
    public static String usuarioLogado;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        mudarCena("login.fxml");
        stage.setTitle("Login - App de Senhas");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void mudarCena(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        primaryStage.setScene(scene);
    }
}
