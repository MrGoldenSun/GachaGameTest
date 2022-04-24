package com.example.gachagame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    private SplitPane Stickman2Info;
    @FXML
    private SplitPane Stickman3Info;
    @FXML
    private SplitPane Stickman4Info;
    @FXML
    private SplitPane Stickman5Info;
    @FXML
    private SplitPane Stickman6Info;

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
        System.out.println(1);
        Stickman1Info.setOpacity(1);
        Stickman1Info.setDisable(false);
    }

    @FXML
    void pickStickman2(ActionEvent event) {
        System.out.println(2);
        Stickman2Info.setOpacity(1);
        Stickman2Info.setDisable(false);
    }

    @FXML
    void pickStickman3(ActionEvent event) {
        System.out.println(3);
        Stickman3Info.setOpacity(1);
        Stickman3Info.setDisable(false);
    }

    @FXML
    void pickStickman4(ActionEvent event) {
        System.out.println(4);
        Stickman4Info.setOpacity(1);
        Stickman4Info.setDisable(false);
    }

    @FXML
    void pickStickman5(ActionEvent event) {
        System.out.println(5);
        Stickman5Info.setOpacity(1);
        Stickman5Info.setDisable(false);
    }

    @FXML
    void pickStickman6(ActionEvent event) {
        System.out.println(6);
        Stickman6Info.setOpacity(1);
        Stickman6Info.setDisable(false);
    }

    @FXML
    void closeMenu(ActionEvent event) {
        System.out.println("Closing Menu");
        Stickman1Info.setOpacity(0);
        Stickman1Info.setDisable(true);
        Stickman2Info.setOpacity(0);
        Stickman2Info.setDisable(true);
        Stickman3Info.setOpacity(0);
        Stickman3Info.setDisable(true);
        Stickman4Info.setOpacity(0);
        Stickman4Info.setDisable(true);
        Stickman5Info.setOpacity(0);
        Stickman5Info.setDisable(true);
        Stickman6Info.setOpacity(0);
        Stickman6Info.setDisable(true);


    }

}
