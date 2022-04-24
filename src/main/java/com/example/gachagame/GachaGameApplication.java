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
        FileWriter coinWriter = new FileWriter("coinAmount.txt");
        coinWriter.write("4");
        coinWriter.close();
        FileWriter stickmanWriter = new FileWriter("stickman.txt");
        stickmanWriter.write("stickman3-false\nstickman4-false\nstickman5-false\nstickman6-false\n");
        stickmanWriter.close();
        FileWriter levelWriter = new FileWriter("levels.txt");
        levelWriter.write("level2-false\nlevel3-false\n");
        levelWriter.close();
        primaryStage = stage;
        primaryStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("titleScreen.fxml"));
        Parent root = loader.load();
        ScreenController screenController = loader.getController();
        screenController.initializeAssets();
        primaryStage.setScene(new Scene(root, 600, 400));
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
