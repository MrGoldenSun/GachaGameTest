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

    /**
     * Creates window for stage and sets up the application
     * @param stage the Stage which is used
     * @throws IOException File not found error
     */
    public void start(Stage stage) throws IOException{
        // open a file to contain the data for how many coins the player has. This will be edited and referenced in various scenes.
        FileWriter coinWriter = new FileWriter("coinAmount.txt");
        coinWriter.write("4");
        coinWriter.close();
        // Similar situation here. The gacha algorithm uses this file to distinguish if each character is owned or not
        FileWriter stickmanWriter = new FileWriter("stickman.txt");
        stickmanWriter.write("stickman3-false\nstickman4-false\nstickman5-false\nstickman6-false\n");
        stickmanWriter.close();
        // Once again similar idea-- write a file that determines if levels 2 and 3 are unlocked.
        FileWriter levelWriter = new FileWriter("levels.txt");
        levelWriter.write("level2-false\nlevel3-false\n");
        levelWriter.close();
        // initialize the scene in a fixed size window
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

    /**
     * Used to switch between screens while keeping window size the same
     * @param newScene the scene which we are switching to
     */
    public void switchScenes(Parent newScene) {
        primaryStage.getScene().setRoot(newScene);
    }

    // launch the application
    public static void main(String[] args) {
        launch(args);
    }
}
