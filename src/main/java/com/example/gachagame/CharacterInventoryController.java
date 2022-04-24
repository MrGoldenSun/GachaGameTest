package com.example.gachagame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CharacterInventoryController {

    public GachaGameApplication gameSettings = new GachaGameApplication();

    @FXML
    private Button stickman1;
    @FXML
    private Button stickman2;
    @FXML
    private Button stickman3;
    @FXML
    private Button stickman4;
    @FXML
    private Button stickman5;
    @FXML
    private Button stickman6;
    @FXML
    private SplitPane stickman1Info;
    @FXML
    private SplitPane stickman2Info;
    @FXML
    private SplitPane stickman3Info;
    @FXML
    private SplitPane stickman4Info;
    @FXML
    private SplitPane stickman5Info;
    @FXML
    private SplitPane stickman6Info;
    @FXML
    private ImageView stickman3Block, stickman4Block, stickman5Block, stickman6Block;

    public void initializeCharacters() throws FileNotFoundException {
        Scanner stickmanFile = new Scanner(new File("stickman.txt"));
        while (stickmanFile.hasNext()) {
            String[] words = stickmanFile.nextLine().split("-");
            if (words[1].equals("true")) {
                unlockCharacter(words[0]);
            }
        }
    }
    public void unlockCharacter(String word){
        switch (word) {
            case "stickman3" -> {
                stickman3Block.setDisable(true);
                stickman3Block.setOpacity(0);
            }
            case "stickman4" -> {
                stickman4Block.setDisable(true);
                stickman4Block.setOpacity(0);
            }
            case "stickman5" -> {
                stickman5Block.setDisable(true);
                stickman5Block.setOpacity(0);
            }
            case "stickman6" -> {
                stickman6Block.setDisable(true);
                stickman6Block.setOpacity(0);
            }
        }
    }

    @FXML
    void openTitleScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("titleScreen.fxml"));
        Parent root = loader.load();
        ScreenController screenController = loader.getController();
        screenController.initializeAssets();
        gameSettings.switchScenes(root);
    }

    @FXML
    void pickStickman1(ActionEvent event) {
        System.out.println("Opening stickman1 description screen.");
        stickman1Info.setOpacity(1);
        stickman1Info.setDisable(false);
    }

    @FXML
    void pickStickman2(ActionEvent event) {
        System.out.println("Opening stickman2 description screen.");
        stickman2Info.setOpacity(1);
        stickman2Info.setDisable(false);
    }

    @FXML
    void pickStickman3(ActionEvent event) {
        System.out.println("Opening stickman3 description screen.");
        stickman3Info.setOpacity(1);
        stickman3Info.setDisable(false);
    }

    @FXML
    void pickStickman4(ActionEvent event) {
        System.out.println("Opening stickman4 description screen.");
        stickman4Info.setOpacity(1);
        stickman4Info.setDisable(false);
    }

    @FXML
    void pickStickman5(ActionEvent event) {
        System.out.println("Opening stickman5 description screen.");
        stickman5Info.setOpacity(1);
        stickman5Info.setDisable(false);
    }

    @FXML
    void pickStickman6(ActionEvent event) {
        System.out.println("Opening stickman6 description screen.");
        stickman6Info.setOpacity(1);
        stickman6Info.setDisable(false);
    }

    @FXML
    void closeMenu(ActionEvent event) {
        System.out.println("Closing Menu");
        stickman1Info.setOpacity(0);
        stickman1Info.setDisable(true);
        stickman2Info.setOpacity(0);
        stickman2Info.setDisable(true);
        stickman3Info.setOpacity(0);
        stickman3Info.setDisable(true);
        stickman4Info.setOpacity(0);
        stickman4Info.setDisable(true);
        stickman5Info.setOpacity(0);
        stickman5Info.setDisable(true);
        stickman6Info.setOpacity(0);
        stickman6Info.setDisable(true);


    }

}
