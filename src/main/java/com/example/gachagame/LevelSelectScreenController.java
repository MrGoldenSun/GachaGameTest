package com.example.gachagame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.io.IOException;

public class LevelSelectScreenController {

    public GachaGameApplication gameSettings = new GachaGameApplication();
    public String color = "blue";

    // TESTING PURPOSES
    @FXML
    private Label testText;

    @FXML
    void changeText(ActionEvent event) {
        testText.setText("AWESOME");
    }
    // TESTING PURPOSES

    @FXML
    void backToTitle(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("titleScreen.fxml"));
        Parent root = loader.load();
        ScreenController screenController = loader.getController();
        screenController.initializeAssets();
        gameSettings.switchScenes(root);
    }

    @FXML
    void startLevel1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fightStage.fxml"));
        Parent root = loader.load();
        StageController fightStage = loader.getController();
        fightStage.setCharacters(color);
        gameSettings.switchScenes(root);
    }

    @FXML
    void startLevel2(ActionEvent event) {
        System.out.println("Level 2");
    }

    @FXML
    void startLevel3(ActionEvent event) {
        System.out.println("Level 3");
    }

    @FXML
    void pickBlue(ActionEvent event) {
        color = "blue";
    }

    @FXML
    void pickRed(ActionEvent event) {
        color = "red";
    }
}
