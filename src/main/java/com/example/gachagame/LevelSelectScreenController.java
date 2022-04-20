package com.example.gachagame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class LevelSelectScreenController {

    public GachaGameApplication gameSettings = new GachaGameApplication();
    public String color = "blue";

    @FXML
    void backToTitle(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("titleScreen.fxml"));
        Parent root = loader.load();
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
    void startLevel2(ActionEvent event) throws IOException {
        System.out.println("Level 2");
    }

    @FXML
    void startLevel3(ActionEvent event) throws IOException {
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
