package com.example.gachagame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.File;
import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class CollectionController {

    GachaGameApplication gameSettings = new GachaGameApplication();

    @FXML
    private Label coinCounter, displayLabel, nameLabel;

    @FXML
    private AnchorPane characterPane;

    @FXML
    private ImageView characterUnlocked, starLevel;

    @FXML
    private Text textStats;

    private int badLuck = 0;
    private boolean gottenGoddess = false;
    private int coins;
    Random rand = new Random();

    public void backToMenu() throws IOException{
        FileWriter writeCoin = new FileWriter("coinAmount.txt");
        writeCoin.write(Integer.toString(coins));
        writeCoin.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("titleScreen.fxml"));
        Parent root = loader.load();
        ScreenController screenController = loader.getController();
        screenController.initializeAssets();
        gameSettings.switchScenes(root);
    }

    public void initializeMuney() throws IOException{
        Scanner coinFile = new Scanner(new File("coinAmount.txt"));
        coins = coinFile.nextInt();
        coinCounter.setText(Integer.toString(coins));
    }

    public void unlockCharacter(CharacterCopy chosenStick) {
        characterUnlocked.setImage(new Image(chosenStick.getCharacterPortrait()));
        textStats.setText(chosenStick.getStats());
        nameLabel.setText(chosenStick.getName());
        starLevel.setImage(new Image(chosenStick.getStars()));
    }

    public void rollCharacter() throws IOException{

        if (coins > 0) {
            String content = "";
            String chosen = "";

            int starRoll = rand.nextInt(100);
            // 3 star
            if (starRoll > 95 || badLuck == 4) {
                badLuck = 0;
                gottenGoddess = true;
                chosen = "stickman6";
                unlockCharacter(new StickmanGoddess());
            }
            // 2 star
            else if (starRoll > 70) {
                if(!gottenGoddess){
                badLuck += 1;}
                int roll = rand.nextInt(2);
                if (roll == 0) {
                    chosen = "stickman1";
                    unlockCharacter(new StickmanRed());
                } else if (roll == 1) {
                    chosen = "stickman2";
                    unlockCharacter(new StickmanBlue());
                }
            }
            // 1 star
            else {
                if(!gottenGoddess){
                badLuck += 1; }
                int roll = rand.nextInt(3);
                if (roll == 0) {
                    chosen = "stickman5";
                    unlockCharacter(new StickmanRogue());
                } else if (roll == 1) {
                    chosen = "stickman4";
                    unlockCharacter(new StickmanFarmer());
                } else if (roll == 2) {
                    chosen = "stickman3";
                    unlockCharacter(new StickmanGoblin());
                }
            }

            Scanner stickmanReader = new Scanner(new File("stickman.txt"));
            StringBuffer buffer = new StringBuffer();
            while (stickmanReader.hasNextLine()) {
                buffer.append(stickmanReader.nextLine() + System.lineSeparator());
            }
            content = buffer.toString();
            stickmanReader.close();

            if (content.contains(chosen+"-false")) {
                content = content.replace(chosen + "-false", chosen + "-true");
                displayLabel.setText("YOU UNLOCKED!!!");
            }
            else {
                displayLabel.setText("You already unlocked this character!");
            }

            FileWriter stickmanWriter = new FileWriter("stickman.txt");
            stickmanWriter.write(content);
            stickmanWriter.close();
            coins -= 1;
            coinCounter.setText(Integer.toString(coins));
            characterPane.setOpacity(1);
        }
        else {
            System.out.println("NO COINS BROKE ASS");
        }
    }
}
