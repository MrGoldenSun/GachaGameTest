package com.example.gachagame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;

import java.io.IOException;

public class CharacterInventoryController {

    public GachaGameApplication gameSettings = new GachaGameApplication();

    @FXML
    private Button Stickman1;
    @FXML
    private Button Stickman2;
    @FXML
    private Button Stickman3;
    @FXML
    private Button Stickman4;
    @FXML
    private Button Stickman5;
    @FXML
    private Button Stickman6;
    @FXML
    private SplitPane Stickman1Info;


    @FXML
    void openTitleScreen() throws IOException {
        gameSettings.switchScenes("titleScreen.fxml");
    }

    @FXML
    void pickStickman1(ActionEvent event) {
        System.out.println(1);
        Stickman1Info.setOpacity(1);
        Stickman1Info.setDisable(false);
    }

    @FXML
    void pickStickman2(ActionEvent event) {
        System.out.println(2);
    }

    @FXML
    void pickStickman3(ActionEvent event) {
        System.out.println(3);
    }

    @FXML
    void pickStickman4(ActionEvent event) {
        System.out.println(4);
    }

    @FXML
    void pickStickman5(ActionEvent event) {
        System.out.println(5);
    }

    @FXML
    void pickStickman6(ActionEvent event) {
        System.out.println(6);
    }

    @FXML
    void closeMenu(ActionEvent event) {
        System.out.println("Closing Menu");
        Stickman1Info.setOpacity(0);
        Stickman1Info.setDisable(true);
    }

}
