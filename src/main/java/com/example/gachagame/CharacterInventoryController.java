package com.example.gachagame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CharacterInventoryController {

    public GachaGameApplication gameSettings = new GachaGameApplication();

    @FXML
    private SplitPane stickmanInfoPane;
    @FXML
    private Text stickmanInfoStats, stickmanInfoStory;
    @FXML
    private Label stickmanInfoName, stickmanInfoHometown;
    @FXML
    private ImageView stickman3Block, stickman4Block, stickman5Block, stickman6Block, stickmanInfoImage, stickmanInfoStars;


    /**
     * Reads the stickman.txt and determines which characters to unlock
     * @throws FileNotFoundException File not found error
     */
    public void initializeCharacters() throws FileNotFoundException {
        Scanner stickmanFile = new Scanner(new File("stickman.txt"));
        // splits the line in the file into an array, and checks if there is a "true" in array
        while (stickmanFile.hasNext()) {
            String[] words = stickmanFile.nextLine().split("-");
            // if there is a true, it goes to unlock the character
            if (words[1].equals("true")) {
                unlockCharacter(words[0]);
            }
        }
    }

    /**
     * Uses a switch case in order to determine which character it will enable in the inventory screen
     * @param word stickman name
     */
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

    /**
     * Brings you back to the title screen
     * @throws IOException  File not found error
     */
    @FXML
    void openTitleScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("titleScreen.fxml"));
        Parent root = loader.load();
        ScreenController screenController = loader.getController();
        screenController.initializeAssets();  // used for the coin amount label
        gameSettings.switchScenes(root);
    }

    /**
     * Sets the stickman information on the pane
     * @param stick The stickman character whose information is being used for display
     */
    public void setStickmanPane(CharacterCopy stick) {
        stickmanInfoImage.setImage(new Image(stick.getCharacterPortrait()));
        stickmanInfoStats.setText(stick.getStats());
        stickmanInfoName.setText(stick.getName());
        stickmanInfoHometown.setText(stick.getHometown());
        stickmanInfoStory.setText(stick.getStory());
        stickmanInfoPane.setOpacity(1);
        stickmanInfoPane.setDisable(false);
        stickmanInfoStars.setImage(new Image(stick.getStars()));
    }

    /**
     * Button which sets the information for the red stickman
     * @param event NULL
     */
    @FXML
    void pickStickman1(ActionEvent event) {
        System.out.println("Opening stickman1 description screen.");
        StickmanRed stick = new StickmanRed();
        setStickmanPane(stick);
    }

    /**
     * Button which sets the information for the blue stickman
     * @param event NULL
     */
    @FXML
    void pickStickman2(ActionEvent event) {
        System.out.println("Opening stickman2 description screen.");
        StickmanBlue stick = new StickmanBlue();
        setStickmanPane(stick);
    }

    /**
     * Button which sets the information for the goblin stickman
     * @param event NULL
     */
    @FXML
    void pickStickman3(ActionEvent event) {
        System.out.println("Opening stickman3 description screen.");
        StickmanGoblin stick = new StickmanGoblin();
        setStickmanPane(stick);
    }

    /**
     * Button which sets the information for the farmer stickman
     * @param event NULL
     */
    @FXML
    void pickStickman4(ActionEvent event) {
        System.out.println("Opening stickman4 description screen.");
        StickmanFarmer stick = new StickmanFarmer();
        setStickmanPane(stick);
    }

    /**
     * Button which sets the information for rogue stickman
     * @param event NULL
     */
    @FXML
    void pickStickman5(ActionEvent event) {
        System.out.println("Opening stickman5 description screen.");
        StickmanRogue stick = new StickmanRogue();
        setStickmanPane(stick);
    }

    /**
     * Button which sets the information for the goddess stickman
     * @param event NULL
     */
    @FXML
    void pickStickman6(ActionEvent event) {
        System.out.println("Opening stickman6 description screen.");
        StickmanGoddess stick = new StickmanGoddess();
        setStickmanPane(stick);
    }

    /**
     * Button which closes the stickman information Pane
     * @param event NULL
     */
    @FXML
    void closeMenu(ActionEvent event) {
        System.out.println("Closing Menu");
        stickmanInfoPane.setOpacity(0);
        stickmanInfoPane.setDisable(true);
    }
}
