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
    public String color = "red";

    @FXML
    private RadioButton blueButton, redButton, stickman3Button, stickman4Button, stickman5Button, stickman6Button;
    @FXML
    private ImageView stickman3Picture, stickman4Picture, stickman5Picture, stickman6Picture, currentOpponent;
    @FXML
    private Button level2Button, level3Button;

    /**
     * reads the stickman.txt adn levels.txt, then unlocks assets accordingly
     * @throws FileNotFoundException File not found error
     */
    public void initializeLevels() throws FileNotFoundException {
        Scanner stickmanFile = new Scanner(new File("stickman.txt"));
        // splits the line in the file into an array, and checks if there is a "true" in array
        while (stickmanFile.hasNext()){
            String[] words = stickmanFile.nextLine().split("-");
            // if there is a true, it goes to unlock the character
            if (words[1].equals("true")){
                unlockCharacter(words[0]);
            }
        }

        Scanner levelsFile = new Scanner(new File("levels.txt"));
        // splits the line in the file into an array, and checks if there is a "true" in array
        while (levelsFile.hasNext()){
            String[] words = levelsFile.nextLine().split("-");
            // if there is a true, it goes to unlock the level
            if (words[1].equals("true")) {
                unlockLevels(words[0]);
            }
        }
    }

    /**
     * Uses a switch case in order to determine which character it will enable in the button screen
     * @param word stickman name
     */
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

    /**
     * Uses a switch case in order to determine which level it will enable in the button screen
     * @param word level name
     */
    public void unlockLevels(String word) {
        switch (word){
            case "level2" -> level2Button.setDisable(false);

            case "level3" -> level3Button.setDisable(false);

        }
    }

    /**
     * Button which takes you back to the title screen
     * @param event NULL
     * @throws IOException File not found error
     */
    @FXML
    void backToTitle(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("titleScreen.fxml"));
        Parent root = loader.load();
        ScreenController screenController = loader.getController();
        screenController.initializeAssets();  // used to load coins on title screen
        gameSettings.switchScenes(root);
    }

    /**
     * Loads the fight stage for level 1
     * @param event NULL
     * @throws IOException File not found error
     */
    @FXML
    void startLevel1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fightStage.fxml"));
        Parent root = loader.load();
        StageController fightStage = loader.getController();
        fightStage.loadLevel1(color);  // load level 1 stage
        gameSettings.switchScenes(root);
    }

    /**
     * Loads the fight stage for level 2
     * @param event NULL
     * @throws IOException File not found error
     */
    @FXML
    void startLevel2(ActionEvent event) throws IOException {
        System.out.println("Level 2");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fightStage.fxml"));
        Parent root = loader.load();
        StageController fightStage = loader.getController();
        fightStage.loadLevel2(color); // load level 2 stage
        gameSettings.switchScenes(root);
    }

    /**
     * Loads the fight stage for level 3
     * @param event NULL
     * @throws IOException File not found error
     */
    @FXML
    void startLevel3(ActionEvent event) throws IOException {
        System.out.println("Level 3");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fightStage.fxml"));
        Parent root = loader.load();
        StageController fightStage = loader.getController();
        fightStage.loadLevel3(color);  // load level 3 stage
        gameSettings.switchScenes(root);
    }

    /**
     * Switch case which determines which color choice is chosen and uses it for the level select
     * @param event NULL
     */
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

// All three of these functions are used to display the next opponent based on whether the mouse has hovered over the
// button for level 1, level 2, or level 3
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
