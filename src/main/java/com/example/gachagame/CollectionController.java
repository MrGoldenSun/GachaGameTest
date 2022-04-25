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

    /**
     * Writes the current coin amount to the coinAmount.txt and then loads the title screen
     * @throws IOException File not found error
     */
    public void backToMenu() throws IOException{
        FileWriter writeCoin = new FileWriter("coinAmount.txt");
        writeCoin.write(Integer.toString(coins));
        writeCoin.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("titleScreen.fxml"));
        Parent root = loader.load();
        ScreenController screenController = loader.getController();
        screenController.initializeAssets();  // used to display coin amount to label
        gameSettings.switchScenes(root);
    }

    /**
     * Reads the coinAmount.txt and sets that value to the label and the coins instance variable
     * @throws IOException File not found error
     */
    public void initializeMuney() throws IOException{
        Scanner coinFile = new Scanner(new File("coinAmount.txt"));
        coins = coinFile.nextInt();
        coinCounter.setText(Integer.toString(coins));
    }

    /**
     * Displays the unlocked character to the screen
     * @param chosenStick The character which was unlocked
     */
    public void unlockCharacter(CharacterCopy chosenStick) {
        characterUnlocked.setImage(new Image(chosenStick.getCharacterPortrait()));
        textStats.setText(chosenStick.getStats());
        nameLabel.setText(chosenStick.getName());
        starLevel.setImage(new Image(chosenStick.getStars()));
    }

    /**
     * When button is pressed, this method picks a character to unlock and then writes that newly unlocked character to
     * the stickman.txt file. The roll also contains bad luck protection if the user has rolled 5 consecutive times
     * @throws IOException File not found error
     */
    public void rollCharacter() throws IOException{
        // allows that user to roll if they have coins > 0
        if (coins > 0) {

            // both these variables are used for the writing of the stickman.txt
            String content = "";
            String chosen = "";

            int starRoll = rand.nextInt(100);  // rolls a random number between 0 - 100
            // 3 star unlock, uses bad luck protection if user has consecutively rolled 5 times
            if (starRoll > 95 || badLuck == 4) {
                badLuck = 0;
                gottenGoddess = true;
                chosen = "stickman6";
                unlockCharacter(new StickmanGoddess());
            }
            // 2 star unlock
            else if (starRoll > 70) {
                // if the 3 star has been obtains, then you don't add to badLuck counter
                if(!gottenGoddess){
                badLuck += 1;}
                int roll = rand.nextInt(2);  // roll to determine which 2 star to unlock
                if (roll == 0) {
                    chosen = "stickman1";
                    unlockCharacter(new StickmanRed());
                } else if (roll == 1) {
                    chosen = "stickman2";
                    unlockCharacter(new StickmanBlue());
                }
            }
            // 1 star unlock
            else {
                // if the 3 star has been obtains, then you don't add to badLuck counter
                if(!gottenGoddess){
                badLuck += 1; }
                int roll = rand.nextInt(3);  // roll to determine which 1 star to unlock
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
            // puts content of stickman.txt to a string
            while (stickmanReader.hasNextLine()) {
                buffer.append(stickmanReader.nextLine() + System.lineSeparator());
            }
            // puts content of stickman.txt to a string
            content = buffer.toString();
            stickmanReader.close();

            // if character hasn't been obtained before, modify string to replace "false" with "true"
            if (content.contains(chosen+"-false")) {
                content = content.replace(chosen + "-false", chosen + "-true");
                displayLabel.setText("YOU UNLOCKED!!!");
            }
            // if character has been obtained before, display message letting user know they own the character
            else {
                displayLabel.setText("You already unlocked this character!");
            }

            // write content of character unlocked to stickman.txt
            FileWriter stickmanWriter = new FileWriter("stickman.txt");
            stickmanWriter.write(content);
            stickmanWriter.close();

            // decrement coins and set text to display change
            coins -= 1;
            coinCounter.setText(Integer.toString(coins));
            characterPane.setOpacity(1);
        }
        // Console message that user has no coins
        else {
            System.out.println("NO COINS!");
        }
    }
}
