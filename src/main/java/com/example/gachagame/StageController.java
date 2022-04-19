package com.example.gachagame;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class StageController {
    @FXML
    private ImageView playerStickman;
    @FXML
    private ImageView enemyStickman;
    @FXML
    private ImageView POW;
    @FXML
    private Button attackButton;

    public static Image redStickman = new Image("RedStickman.png");
    public static Image blueStickman = new Image("BlueStickman.png");


    public void attackButton (ActionEvent e) throws InterruptedException {
        moveImage(playerStickman);

    }

    private class Timer extends AnimationTimer{
        public int timing = 0;
        @Override
        public void handle(long l) {
            if (timing == 0){
                stop();
                attackButton.setDisable(false);
            }

            else if (timing == 130){
                POW.setOpacity(0);
                timing -= 1;
                System.out.println("OP IS NOW 0");
            }

            else if (timing == 190){
                POW.setOpacity(1);
                timing -= 1;
            }
            else {
                timing -= 1;
            }
        }

    }

    public void moveImage(ImageView image1) throws InterruptedException {
        TranslateTransition transition = new TranslateTransition();
        Timer myTime = new Timer();
        transition.setNode(image1);
        transition.setDuration(Duration.seconds(.5));
        transition.setByX(350);
        rotateImage(image1);
        transition.setCycleCount(2);
        transition.setAutoReverse(true);
        transition.play();
        attackButton.setDisable(true);
        myTime.timing = 250;
        myTime.start();

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
