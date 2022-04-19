package com.example.gachagame;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class StageController {
    @FXML
    private ImageView playerStickman;
    @FXML
    private ImageView enemyStickman;

    public static Image redStickman = new Image("Red Stickman.png");
    public static Image blueStickman = new Image("Blue Stickman.png");




    public void attackButton (ActionEvent e){
        moveImage(playerStickman);

    }


    public void moveImage(ImageView image1) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(image1);
        transition.setDuration(Duration.seconds(.5));
        transition.setByX(350);
        rotateImage(image1);
        transition.setCycleCount(2);
        transition.setAutoReverse(true);
        transition.play();
    }

    public void rotateImage(ImageView image1){
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(image1);
        rotate.setDuration(Duration.seconds(.5));
        rotate.setByAngle(45);
        rotate.setCycleCount(2);
        rotate.setAutoReverse(true);
        rotate.play();
    }

}
