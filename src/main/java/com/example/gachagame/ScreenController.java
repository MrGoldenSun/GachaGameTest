package com.example.gachagame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.io.IOException;


public class ScreenController {

    public GachaGameApplication gameSettings = new GachaGameApplication();

    @FXML
    ImageView goldenGrinner;
    @FXML
    ImageView greedyGrinner;

    @FXML
    Label

    @FXML
    void openInventory(ActionEvent event) throws IOException {
        gameSettings.switchScenes("characterSelect.fxml");
    }

    @FXML
    void openLevels(ActionEvent event) throws IOException {
        gameSettings.switchScenes("levelSelectScreen.fxml");
    }

    @FXML
    void openGacha(ActionEvent event) throws IOException {
        gameSettings.switchScenes("gachaScreen.fxml");
    }

    @FXML
    public void pickRedStickman(ActionEvent event) throws IOException {
        gameSettings.switchScenes("fightStage.fxml");
    }

    @FXML
    void pickBlueStickman(ActionEvent event){
        greedyGrinner.opacityProperty().setValue(1);
        goldenGrinner.opacityProperty().setValue(0);

    }

}
