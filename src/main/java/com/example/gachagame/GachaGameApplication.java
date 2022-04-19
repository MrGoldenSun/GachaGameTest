package com.example.gachagame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GachaGameApplication extends Application {
    public void start(Stage stage) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("titleScreen.fxml")));
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setTitle("Gacha Game");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
