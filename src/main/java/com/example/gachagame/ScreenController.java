package com.example.gachagame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;


public class ScreenController {

    public GachaGameApplication gameSettings = new GachaGameApplication();

    @FXML
    void openInventory(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("characterSelect.fxml"));
        Parent root = loader.load();
        gameSettings.switchScenes(root);
    }

    @FXML
    void openLevels(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("levelSelectScreen.fxml"));
        Parent root = loader.load();
        gameSettings.switchScenes(root);
    }

    @FXML
    void openGacha(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gachaScreen.fxml"));
        Parent root = loader.load();
        gameSettings.switchScenes(root);
    }

}
