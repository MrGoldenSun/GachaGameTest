package com.example.gachagame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class ScreenController {

    @FXML
    private Label coinCounter;

    public GachaGameApplication gameSettings = new GachaGameApplication();
    public int coinAmount;

    public void initializeAssets() throws FileNotFoundException {
        File file = new File("coinAmount.txt");
        Scanner input = new Scanner(file);
        coinCounter.setText(input.next());
    }

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
        LevelSelectScreenController levelSelectScreenController = loader.getController();
        levelSelectScreenController.initializeLevels();
        gameSettings.switchScenes(root);
    }

    @FXML
    void openGacha(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gachaScreen.fxml"));
        Parent root = loader.load();
        gameSettings.switchScenes(root);
    }

}
