package com.example.gachagame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ScreenController {

    @FXML
    ImageView goldenGrinner;
    @FXML
    ImageView greedyGrinner;

    @FXML
    void startGameAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("characterSelect.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void pickRedStickman(ActionEvent event){
        goldenGrinner.opacityProperty().setValue(1);
        greedyGrinner.opacityProperty().setValue(0);

    }

    @FXML
    void pickBlueStickman(ActionEvent event){
        greedyGrinner.opacityProperty().setValue(1);
        goldenGrinner.opacityProperty().setValue(0);

    }

}
