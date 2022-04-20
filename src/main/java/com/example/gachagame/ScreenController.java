package com.example.gachagame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;


public class ScreenController {

    public GachaGameApplication gameSettings = new GachaGameApplication();

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

}
