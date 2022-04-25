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

    @FXML
    void pickStickman1(ActionEvent event) {
        System.out.println("Opening stickman1 description screen.");
        StickmanRed stick = new StickmanRed();
        setStickmanPane(stick);
    }

    @FXML
    void pickStickman2(ActionEvent event) {
        System.out.println("Opening stickman2 description screen.");
        StickmanBlue stick = new StickmanBlue();
        setStickmanPane(stick);
    }

    @FXML
    void pickStickman3(ActionEvent event) {
        System.out.println("Opening stickman3 description screen.");
        StickmanGoblin stick = new StickmanGoblin();
        setStickmanPane(stick);
    }

    @FXML
    void pickStickman4(ActionEvent event) {
        System.out.println("Opening stickman4 description screen.");
        StickmanFarmer stick = new StickmanFarmer();
        setStickmanPane(stick);
    }

    @FXML
    void pickStickman5(ActionEvent event) {
        System.out.println("Opening stickman5 description screen.");
        StickmanRogue stick = new StickmanRogue();
        setStickmanPane(stick);
    }

    @FXML
    void pickStickman6(ActionEvent event) {
        System.out.println("Opening stickman6 description screen.");
        StickmanGoddess stick = new StickmanGoddess();
        setStickmanPane(stick);
    }

    @FXML
    void closeMenu(ActionEvent event) {
        System.out.println("Closing Menu");
        stickmanInfoPane.setOpacity(0);
        stickmanInfoPane.setDisable(true);
    }

}
