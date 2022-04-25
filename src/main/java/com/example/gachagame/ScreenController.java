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

    /**
     * Used to read coin file and set coin amount to screen
     * @throws FileNotFoundException File not found exception
     */
    public void initializeAssets() throws FileNotFoundException {
        File file = new File("coinAmount.txt");
        Scanner input = new Scanner(file);
        coinCounter.setText(input.next());
    }

    /**
     * Button which takes you to the character inventory screen
     * @param event NULL
     * @throws IOException File not found exception
     */
    @FXML
    void openInventory(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("characterSelect.fxml"));
        Parent root = loader.load();
        CharacterInventoryController characterInventoryController = loader.getController();
        characterInventoryController.initializeCharacters();  // determines what characters have been unlocked
        gameSettings.switchScenes(root);
    }

    /**
     * Button which takes you to the level select screen
     * @param event NULL
     * @throws IOException File not found exception
     */
    @FXML
    void openLevels(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("levelSelectScreen.fxml"));
        Parent root = loader.load();
        LevelSelectScreenController levelSelectScreenController = loader.getController();
        levelSelectScreenController.initializeLevels();  // determines what levels and characters have been unlocked
        gameSettings.switchScenes(root);
    }

    /**
     * Button which takes you to the gacha roll screen
     * @param event NULL
     * @throws IOException File not found exception
     */
    @FXML
    void openGacha(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gachaScreen.fxml"));
        Parent root = loader.load();
        CollectionController collectionController = loader.getController();
        collectionController.initializeMuney();  // initializes money in the gacha screen
        gameSettings.switchScenes(root);
    }

}
