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

    //declare instance variables 

    GachaGameApplication gameSettings = new GachaGameApplication();

    @FXML
    private Label coinCounter, displayLabel, nameLabel;

    @FXML
    private AnchorPane characterPane;

    @FXML
    private ImageView characterUnlocked;

    @FXML
    private Text textStats;

    private int badLuck = 0;
    private boolean gottenGoddess = false;
    private int coins;
    Random rand = new Random();

    //back button
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

    //coins for rolling for new characters
    public void initializeMuney() throws IOException{
        Scanner coinFile = new Scanner(new File("coinAmount.txt"));
        coins = coinFile.nextInt();
        coinCounter.setText(Integer.toString(coins));
    }

    //roll for new characters
    public void rollCharacter() throws IOException{

        //if there are coins
        if (coins > 0) {
            String content = "";
            String chosen = "";

            //random number between 0-99
            //0-69 is a 1-star, 70-94 is a 2-star, 95-99 is a 3-star
            int starRoll = rand.nextInt(100);
            // 3 star
            if (starRoll >= 95 || badLuck == 4) {
                //reset badluck counter, unlock stickman6 character/goddess
                badLuck = 0;
                gottenGoddess = true;
                chosen = "stickman6";
                characterUnlocked.setImage(new Image(new StickmanGoddess().getCharacterPortrait()));
                textStats.setText(new StickmanGoddess().getStats());
                nameLabel.setText(new StickmanGoddess().getName());
            }
            // 2 star
            else if (starRoll >= 70) {
                //if goddess is not unlucked, increase badluck counter and roll for random 2-star
                if(!gottenGoddess){
                badLuck += 1;}
                int roll = rand.nextInt(2);
                if (roll == 0) {
                    chosen = "stickman1";
                    characterUnlocked.setImage(new Image(new StickmanRed().getCharacterPortrait()));
                    textStats.setText(new StickmanRed().getStats());
                    nameLabel.setText(new StickmanRed().getName());
                } else if (roll == 1) {
                    chosen = "stickman2";
                    characterUnlocked.setImage(new Image(new StickmanBlue().getCharacterPortrait()));
                    textStats.setText(new StickmanBlue().getStats());
                    nameLabel.setText(new StickmanBlue().getName());
                }
            }
            // 1 star
            else {
                //if goddess is not unlucked, increase badluck counter and roll for random 1-star
                if(!gottenGoddess){
                badLuck += 1; }
                int roll = rand.nextInt(3);
                if (roll == 0) {
                    chosen = "stickman5";
                    characterUnlocked.setImage(new Image(new StickmanRogue().getCharacterPortrait()));
                    textStats.setText(new StickmanRogue().getStats());
                    nameLabel.setText(new StickmanRogue().getName());
                } else if (roll == 1) {
                    chosen = "stickman4";
                    characterUnlocked.setImage(new Image(new StickmanFarmer().getCharacterPortrait()));
                    textStats.setText(new StickmanFarmer().getStats());
                    nameLabel.setText(new StickmanFarmer().getName());
                } else if (roll == 2) {
                    chosen = "stickman3";
                    characterUnlocked.setImage(new Image(new StickmanGoblin().getCharacterPortrait()));
                    textStats.setText(new StickmanGoblin().getStats());
                    nameLabel.setText(new StickmanGoblin().getName());
                }
            }

            //read txt file for what has been unlucked and put into content string
            Scanner stickmanReader = new Scanner(new File("stickman.txt"));
            StringBuffer buffer = new StringBuffer();
            while (stickmanReader.hasNextLine()) {
                buffer.append(stickmanReader.nextLine() + System.lineSeparator());
            }
            content = buffer.toString();
            stickmanReader.close();

            //if content contains unlocked character as false, set to true
            if (content.contains(chosen+"-false")) {
                content = content.replace(chosen + "-false", chosen + "-true");
                displayLabel.setText("YOU UNLOCKED!!!");
            }
            else {
            //if content contains unlokced character as true, tell user character was already unlocked
                displayLabel.setText("You already unlocked this character!");
            }

            //write content into stickman.txt and substract one coin from coin counter
            FileWriter stickmanWriter = new FileWriter("stickman.txt");
            stickmanWriter.write(content);
            stickmanWriter.close();
            coins -= 1;
            coinCounter.setText(Integer.toString(coins));
            characterPane.setOpacity(1);
        }
        else {
            //if no coins, do not roll
            System.out.println("NO COINS BROKE ASS");
        }
    }
}
