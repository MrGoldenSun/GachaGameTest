package com.example.gachagame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;


public class GachaGameApplication extends Application {

    private static Stage primaryStage;

    public void start(Stage stage) throws IOException{
        FileWriter fileWriter = new FileWriter("coinAmount.txt");
        fileWriter.write("2");
        fileWriter.close();
        primaryStage = stage;
        primaryStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("titleScreen.fxml"));
        Parent root = loader.load();
        ScreenController screenController = loader.getController();
        screenController.initializeAssets();
        primaryStage.setScene(new Scene(root));
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Gacha Game");
        primaryStage.show();
    }

    public void switchScenes(Parent newScene) {
        primaryStage.getScene().setRoot(newScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
