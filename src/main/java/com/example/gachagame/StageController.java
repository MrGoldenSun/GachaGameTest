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
    private ImageView rightPOW;
    @FXML
    private ImageView leftPOW;
    @FXML
    private Button attackButton;

    public String chosenColor = "";

    public void setCharacters(String colorChoice){
        chosenColor = colorChoice;
        if (chosenColor.equals("blue")){
            playerStickman.setImage(new Image("transparentblue.png"));
            enemyStickman.setImage(new Image("RedStickmanNoBackground.png"));
            System.out.println("BLUE");
        }
        else if (chosenColor.equals("red")){
            playerStickman.setImage(new Image("RedStickmanNoBackground.png"));
            enemyStickman.setImage(new Image("transparentblue.png"));
            System.out.println("RED");
        }
        else {
            System.out.println("Chosen color not picked");
        }
    }


    public void attackButton (ActionEvent e) {
        combatSequence combat = new combatSequence();
        combat.timing = 300;
        combat.start();

    }

    private class combatSequence extends AnimationTimer{
        public int timing = 0;
        public ImageView selectedImage;
        @Override
        public void handle(long l) {
            if (timing == 300){
                attackRight(playerStickman);
                timing -= 1;
            }
            else if (timing == 150){
                attackLeft(enemyStickman);
                timing -= 1;
            }
            else if (timing == 0){
                timing = 300;
            }
            else {
                timing -= 1;
            }
        }
    }

    private class Timer extends AnimationTimer{
        public int timing = 0;
        public ImageView selectedImage;
        @Override
        public void handle(long l) {
            if (timing == 0){
                stop();
            }

            else if (timing == 30){
                selectedImage.setOpacity(0);
                timing -= 1;
                System.out.println("OP IS NOW 0");
            }

            else if (timing == 90){
                selectedImage.setOpacity(1);
                timing -= 1;
            }
            else {
                timing -= 1;
            }
        }

    }

    public void attackRight(ImageView image1) {
        TranslateTransition transition = new TranslateTransition();
        Timer myTime = new Timer();
        transition.setNode(image1);
        transition.setDuration(Duration.seconds(.5));
        transition.setByX(350);
        rotateRight(image1);
        transition.setCycleCount(2);
        transition.setAutoReverse(true);
        transition.play();
        attackButton.setDisable(true);
        myTime.timing = 150;
        myTime.selectedImage = rightPOW;
        myTime.start();

    }

    public void rotateRight(ImageView image1){
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(image1);
        rotate.setDuration(Duration.seconds(.5));
        rotate.setByAngle(45);
        rotate.setCycleCount(2);
        rotate.setAutoReverse(true);
        rotate.play();
    }

    public void attackLeft(ImageView image1) {
        TranslateTransition transition = new TranslateTransition();
        Timer myTime = new Timer();
        transition.setNode(image1);
        transition.setDuration(Duration.seconds(.5));
        transition.setByX(-350);
        rotateLeft(image1);
        transition.setCycleCount(2);
        transition.setAutoReverse(true);
        transition.play();
        attackButton.setDisable(true);
        myTime.timing = 150;
        myTime.selectedImage = leftPOW;
        myTime.start();

    }

    public void rotateLeft(ImageView image1){
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(image1);
        rotate.setDuration(Duration.seconds(.5));
        rotate.setByAngle(-45);
        rotate.setCycleCount(2);
        rotate.setAutoReverse(true);
        rotate.play();
    }


}
