package com.example.gachagame;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    public static Image redStickman = new Image("RedStickman.png");
    public static Image blueStickman = new Image("BlueStickman.png");


    public void attackButton (ActionEvent e) throws InterruptedException {
        moveImage(playerStickman);

    }

    private class Timer extends AnimationTimer{
        @Override
        public void handle(long l) {
            POW.setOpacity(POW.getOpacity() - .005);
            if (POW.getOpacity() == 0){
                stop();
                System.out.println("OP IS NOW 0");
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
        POW.setOpacity(1);
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
