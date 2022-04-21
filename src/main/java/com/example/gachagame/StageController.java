package com.example.gachagame;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StageController {

    public GachaGameApplication gameSettings = new GachaGameApplication();


    @FXML
    private ImageView playerStickman;
    @FXML
    private ImageView enemyStickman;
    @FXML
    private ImageView rightPOW;
    @FXML
    private ImageView leftPOW;
    @FXML
    private ImageView rightMISS;
    @FXML
    private ImageView leftMISS;
    @FXML
    private Button attackButton;
    @FXML
    private Label playerHP;
    @FXML
    private Label enemyHP;
    @FXML
    private ProgressBar playerHPBar;
    @FXML
    private ProgressBar enemyHPBar;
    @FXML
    private SplitPane winScreen;
    @FXML
    private SplitPane loseScreen;

    public CharacterCopy redStickman = new CharacterCopy(8, 3, 20, 95, 25,
            25, "RedStickmanNoBackground.png");
    public CharacterCopy blueStickman = new CharacterCopy(5, 2, 30, 110, 20,
            20, "transparentblue.png");
    public CharacterCopy goblinStickman = new CharacterCopy(11, 1, 20, 70, 16,
            16, "goblin.png");

    public CharacterCopy playerCharacter;
    public CharacterCopy enemyCharacter;
    public boolean win;
    public String chosenColor = "";

    public void setCharacters(String colorChoice){
        chosenColor = colorChoice;
        if (chosenColor.equals("blue")){
            playerStickman.setImage(new Image(blueStickman.getCharacterPortrait()));
            playerCharacter = blueStickman;
            System.out.println("BLUE");
        }
        else if (chosenColor.equals("red")){
            playerStickman.setImage(new Image(redStickman.getCharacterPortrait()));
            playerCharacter = redStickman;
            System.out.println("RED");
        }
        playerHP.setText(Integer.toString(playerCharacter.getHP()));
        playerHPBar.setProgress(1.0);
        playerHPBar.setStyle("-fx-accent: green");
        enemyStickman.setImage(new Image(goblinStickman.getCharacterPortrait()));
        enemyHPBar.setProgress(1.0);
        enemyHPBar.setStyle("-fx-accent: red");
        enemyCharacter = goblinStickman;
        enemyStickman.setScaleX(-1);
        enemyHP.setText(Integer.toString(enemyCharacter.getHP()));
    }


    public void attackButton (ActionEvent e) {
        combatSequence combat = new combatSequence();
        combat.timing = 600;
        combat.start();
    }

    public void combatEnded() throws IOException {
        if (win){
            winScreen.setDisable(false);
            winScreen.setOpacity(1);
            File file = new File("coinAmount.txt");
            Scanner input = new Scanner(file);
            int total = Integer.parseInt(input.next());
            FileWriter fileWriter = new FileWriter("coinAmount.txt");
            fileWriter.write(Integer.toString(total + 1));
            fileWriter.close();
        }
        else {
            loseScreen.setDisable(false);
            loseScreen.setOpacity(1);
        }
    }

    public void backToMenu(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("titleScreen.fxml"));
        Parent root = loader.load();
        ScreenController screenController = loader.getController();
        screenController.initializeAssets();
        gameSettings.switchScenes(root);

    }

    public int RNG(){
        return (int) (Math.random() * 101);
    }

    public boolean battleCalculations(CharacterCopy char1, CharacterCopy char2){
        int accuracy = char1.getAccuracy() - char2.getSpeed();
        return accuracy >= RNG();
    }

    private class combatSequence extends AnimationTimer{
        public int timing = 0;
        public ImageView selectedImage;
        @Override
        public void handle(long l) {
            if (timing == 600){
                if (playerCharacter.getHP() > 0){
                    attackRight(playerStickman);
                    timing -= 1;
                }
                else {
                    stop();
                    win = false;
                    try {
                        combatEnded();
                    } catch (IOException e) {
                        System.out.println("TABIO GOT AN ERROR");
                    }
                }

            }
            else if (timing == 300){
                if (enemyCharacter.getHP() > 0) {
                    attackLeft(enemyStickman);
                    timing -= 1;
                }
                else {
                    stop();
                    win = true;
                    try {
                        combatEnded();
                    } catch (IOException e) {
                        System.out.println("TABIO GOT AN ERROR");
                    }
                }
            }
            else if (timing == 0){
                timing = 600;
            }
            else {
                timing -= 1;
            }
        }
    }

    private class Timer extends AnimationTimer{
        public int timing = 0;
        public ImageView selectedImage;
        public int damageDealt = 0;
        public CharacterCopy currentChar;
        public Label charHP;
        public ProgressBar charProgressBar;
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

            else if (timing == 70){
                selectedImage.setOpacity(1);
                currentChar.setHP(currentChar.getHP() - damageDealt);
                if (currentChar.getHP() < 0){
                    currentChar.setHP(0);
                }
                charHP.setText(Integer.toString(currentChar.getHP()));
                charProgressBar.setProgress((double) currentChar.getHP() / currentChar.getMHP());
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
        boolean HIT = battleCalculations(playerCharacter, enemyCharacter);
        transition.setNode(image1);
        transition.setDuration(Duration.seconds(.5));
        transition.setByX(350);
        rotateRight(image1);
        transition.setCycleCount(2);
        transition.setAutoReverse(true);
        transition.play();
        attackButton.setDisable(true);
        myTime.timing = 150;
        int damage = playerCharacter.getAttack() - enemyCharacter.getDefense();
        myTime.currentChar = enemyCharacter;
        myTime.charHP = enemyHP;
        myTime.charProgressBar = enemyHPBar;
        if (HIT){
            myTime.damageDealt = damage;
            myTime.selectedImage = rightPOW;
        }
        else {
            myTime.damageDealt = 0;
            myTime.selectedImage = rightMISS;
        }
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
        boolean HIT = battleCalculations(enemyCharacter, playerCharacter);
        transition.setNode(image1);
        transition.setDuration(Duration.seconds(.5));
        transition.setByX(-350);
        rotateLeft(image1);
        transition.setCycleCount(2);
        transition.setAutoReverse(true);
        transition.play();
        attackButton.setDisable(true);
        myTime.timing = 150;
        int damage = enemyCharacter.getAttack() - playerCharacter.getDefense();
        myTime.currentChar = playerCharacter;
        myTime.charHP = playerHP;
        myTime.charProgressBar = playerHPBar;
        if (HIT){
            myTime.damageDealt = damage;
            myTime.selectedImage = leftPOW;
        }
        else {
            myTime.damageDealt = 0;
            myTime.selectedImage = leftMISS;
        }
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
