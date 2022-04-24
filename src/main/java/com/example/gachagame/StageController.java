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
import java.util.ArrayList;
import java.util.Scanner;

public class StageController {

    public GachaGameApplication gameSettings = new GachaGameApplication();

    @FXML
    private ImageView playerStickmanImage, enemyStickmanImage, rightPOW, leftPOW, rightMISS, leftMISS;
    @FXML
    private Button attackButton;
    @FXML
    private Label playerHP, enemyHP;
    @FXML
    private ProgressBar playerHPBar, enemyHPBar;
    @FXML
    private SplitPane winScreen, loseScreen;

    public CharacterCopy playerCharacter, enemyCharacter;
    public boolean win;
    public String chosenColor = "", currentLevel;

    public void characterSelect(String colorChoice){
        chosenColor = colorChoice;
        switch (chosenColor) {
            case "blue" -> {
                playerCharacter = new StickmanBlue();
                playerStickmanImage.setImage(new Image(playerCharacter.getCharacterPortrait()));
                System.out.println("BLUE");
            }
            case "red" -> {
                playerCharacter = new StickmanRed();
                playerStickmanImage.setImage(new Image(playerCharacter.getCharacterPortrait()));
                System.out.println("RED");
            }
            case "goblin" -> {
                playerCharacter = new StickmanGoblin();
                playerStickmanImage.setImage(new Image(playerCharacter.getCharacterPortrait()));
                System.out.println("GOBLIN");
            }
            case "farmer" -> {
                playerCharacter = new StickmanFarmer();
                playerStickmanImage.setImage(new Image(playerCharacter.getCharacterPortrait()));
                System.out.println("FARMER");
            }
            case "rogue" -> {
                playerCharacter = new StickmanGoblin();
                playerStickmanImage.setImage(new Image(playerCharacter.getCharacterPortrait()));
                System.out.println("GOBLIN");
            }
            case "goddess" -> {
                playerCharacter = new StickmanGoblin();
                playerStickmanImage.setImage(new Image(playerCharacter.getCharacterPortrait()));
                System.out.println("GOBLIN");
            }
        }
    }

    public void loadLevel1(String colorChoice){
        characterSelect(colorChoice);
        playerHP.setText(Integer.toString(playerCharacter.getHP()));
        playerHPBar.setProgress(1.0);
        playerHPBar.setStyle("-fx-accent: green");
        enemyCharacter = new StickmanGoblin();
        enemyStickmanImage.setImage(new Image(enemyCharacter.getCharacterPortrait()));
        enemyHP.setText(Integer.toString(enemyCharacter.getHP()));
        enemyHPBar.setProgress(1.0);
        enemyHPBar.setStyle("-fx-accent: red");
        enemyStickmanImage.setScaleX(-1);
        currentLevel = "level1";
    }

    public void loadLevel2(String colorChoice){
        characterSelect(colorChoice);
        playerHP.setText(Integer.toString(playerCharacter.getHP()));
        playerHPBar.setProgress(1.0);
        playerHPBar.setStyle("-fx-accent: green");
        enemyCharacter = new StickmanBlue();
        enemyStickmanImage.setImage(new Image(enemyCharacter.getCharacterPortrait()));
        enemyHP.setText(Integer.toString(enemyCharacter.getHP()));
        enemyHPBar.setProgress(1.0);
        enemyHPBar.setStyle("-fx-accent: red");
        enemyStickmanImage.setScaleX(-1);
        currentLevel = "level2";
    }

    public void loadLevel3(String colorChoice){
        // WILL PUT 3 STAR HERE
        characterSelect(colorChoice);
        playerHP.setText(Integer.toString(playerCharacter.getHP()));
        playerHPBar.setProgress(1.0);
        playerHPBar.setStyle("-fx-accent: green");

        // CREATE ENEMYSTICKMAN TO EQUAL LAST 3 STAR CHARACTER
        enemyCharacter = new StickmanRed();

        enemyStickmanImage.setImage(new Image(enemyCharacter.getCharacterPortrait()));
        enemyHP.setText(Integer.toString(enemyCharacter.getHP()));
        enemyHPBar.setProgress(1.0);
        enemyHPBar.setStyle("-fx-accent: red");
        enemyStickmanImage.setScaleX(-1);
        currentLevel = "level3";
    }

    public void attackButton (ActionEvent e) {
        combatSequence combat = new combatSequence();
        combat.timing = 600;
        combat.start();
    }

    public void unlockLevels() throws IOException {
        Scanner input = new Scanner(new File("levels.txt"));
        ArrayList<String> levelStatus = new ArrayList<>();
        while (input.hasNext()){
            String word = input.nextLine();
            levelStatus.add(word);
            System.out.println(word);
        }
        FileWriter levelWriter = new FileWriter("levels.txt");
        if (currentLevel.equals("level1")) {
            levelWriter.write("level2-true\n" + levelStatus.get(1));
        }
        else if (currentLevel.equals("level2")) {
            levelWriter.write("level2-true\nlevel3-true\n");
        }
        else {
            levelWriter.write("level2-true\nlevel3-true\n");
        }
        levelWriter.close();
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
            unlockLevels();
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
        @Override
        public void handle(long l) {
            if (timing == 600){
                if (playerCharacter.getHP() > 0){
                    attackRight(playerStickmanImage);
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
                    attackLeft(enemyStickmanImage);
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
