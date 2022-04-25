package com.example.gachagame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LevelSelectScreenController {
    public GachaGameApplication gameSettings = new GachaGameApplication();
    public String color = "blue";
    @FXML
    private RadioButton blueButton, redButton, stickman3Button, stickman4Button, stickman5Button, stickman6Button;
    @FXML
    private ImageView stickman3Picture, stickman4Picture, stickman5Picture, stickman6Picture, currentOpponent;
    @FXML
    private Button level2Button, level3Button;

    public void initializeLevels() throws FileNotFoundException {
        Scanner stickmanFile = new Scanner(new File("stickman.txt"));
        while (stickmanFile.hasNext()){
            String[] words = stickmanFile.nextLine().split("-");
            if (words[1].equals("true")){
                unlockCharacter(words[0]);
            }
        }

        Scanner levelsFile = new Scanner(new File("levels.txt"));
        while (levelsFile.hasNext()){
            String[] words = levelsFile.nextLine().split("-");
            if (words[1].equals("true")) {
                unlockLevels(words[0]);
            }
        }
    }

    public void unlockCharacter(String word){
        switch (word) {
            case "stickman3" -> {
                stickman3Button.setDisable(false);
                stickman3Picture.setOpacity(1);
            }
            case "stickman4" -> {
                stickman4Button.setDisable(false);
                stickman4Picture.setOpacity(1);
            }
            case "stickman5" -> {
                stickman5Button.setDisable(false);
                stickman5Picture.setOpacity(1);
            }
            case "stickman6" -> {
                stickman6Button.setDisable(false);
                stickman6Picture.setOpacity(1);
            }
        }
    }

    public void unlockLevels(String word) {
        switch (word){
            case "level2" -> level2Button.setDisable(false);

            case "level3" -> level3Button.setDisable(false);

        }
    }

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
        fightStage.loadLevel1(color);
        gameSettings.switchScenes(root);
    }

    @FXML
    void startLevel2(ActionEvent event) throws IOException {
        System.out.println("Level 2");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fightStage.fxml"));
        Parent root = loader.load();
        StageController fightStage = loader.getController();
        fightStage.loadLevel2(color);
        gameSettings.switchScenes(root);
    }

    @FXML
    void startLevel3(ActionEvent event) throws IOException {
        System.out.println("Level 3");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fightStage.fxml"));
        Parent root = loader.load();
        StageController fightStage = loader.getController();
        fightStage.loadLevel3(color);
        gameSettings.switchScenes(root);
    }

    @FXML
    void pickColor(ActionEvent event) {
        if (redButton.isSelected()){
            color = "red";
        }
        else if (blueButton.isSelected()){
            color = "blue";
        }
        else if (stickman3Button.isSelected()){
            color = "goblin";
        }
        else if (stickman4Button.isSelected()) {
            color = "farmer";
        }
        else if (stickman5Button.isSelected()) {
            color = "rogue";
        }
        else if (stickman6Button.isSelected()) {
            color = "goddess";
        }
    }

    @FXML
    void level1ChangeOpponent() {
        currentOpponent.setImage(new Image("goblin.png"));
    }

    @FXML
    void level2ChangeOpponent() {
        currentOpponent.setImage(new Image("transparentblue.png"));
    }

    @FXML
    void level3ChangeOpponent() {
        currentOpponent.setImage(new Image("mysterybust.png"));
    }

}
