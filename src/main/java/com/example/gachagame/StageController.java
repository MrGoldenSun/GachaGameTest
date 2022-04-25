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

    /**
     * Depending on the color choice, it will set the player character as the player choice
     * @param colorChoice This is based on the color choice from the previous level select screen
     */
    public void characterSelect(String colorChoice){
        chosenColor = colorChoice;
        switch (chosenColor) {
            case "red" -> {
                playerCharacter = new StickmanRed();  // set the character stats
                playerStickmanImage.setImage(new Image(playerCharacter.getCharacterPortrait()));  // set the character image
                System.out.println("RED");  // console message for confirmation
            }
            case "blue" -> {
                playerCharacter = new StickmanBlue();
                playerStickmanImage.setImage(new Image(playerCharacter.getCharacterPortrait()));
                System.out.println("BLUE");
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
                playerCharacter = new StickmanRogue();
                playerStickmanImage.setImage(new Image(playerCharacter.getCharacterPortrait()));
                System.out.println("ROGUE");
            }
            case "goddess" -> {
                playerCharacter = new StickmanGoddess();
                playerStickmanImage.setImage(new Image(playerCharacter.getCharacterPortrait()));
                System.out.println("GODDESS");
            }
        }
    }

    /**
     * this will load the assets for the enemy character and health bars for both characters
     */
    public void initializeLevel() {
        playerHP.setText(Integer.toString(playerCharacter.getHP()));
        playerHPBar.setProgress(1.0);
        playerHPBar.setStyle("-fx-accent: green");
        enemyStickmanImage.setImage(new Image(enemyCharacter.getCharacterPortrait()));
        enemyHP.setText(Integer.toString(enemyCharacter.getHP()));
        enemyHPBar.setProgress(1.0);
        enemyHPBar.setStyle("-fx-accent: red");
        enemyStickmanImage.setScaleX(-1);  // reverse the image for the enemy character
    }


    // SET LEVEL

    /**
     * Loads the level against the goblin
     * @param colorChoice the player's character choice
     */
    public void loadLevel1(String colorChoice){
        characterSelect(colorChoice);
        enemyCharacter = new StickmanGoblin();
        initializeLevel();
        currentLevel = "level1";
    }

    /**
     * Loads the level against the blue stickman
     * @param colorChoice the player's character choice
     */
    public void loadLevel2(String colorChoice){
        characterSelect(colorChoice);
        enemyCharacter = new StickmanBlue();
        initializeLevel();
        currentLevel = "level2";
    }

    /**
     * Loads the level against the secret character
     * @param colorChoice the player's character choice
     */
    public void loadLevel3(String colorChoice){
        characterSelect(colorChoice);
        enemyCharacter = new StickmanGoddess();
        initializeLevel();
        enemyStickmanImage.setImage(new Image("sammysecretboss.png"));  // we used the image for sammy seahawk
        currentLevel = "level3";
    }

    /**
     * when player uses attack button, it initiates the combat sequence
     * @param e NULL
     */
    public void attackButton (ActionEvent e) {
        combatSequence combat = new combatSequence();
        combat.timing = 600;  // IMPORTANT NOTE: Combat speed is based on monitor refresh rate, with these numbers being
        // based on a 240hz monitor. The animations will likely look slower and worse if your monitor is not 240hz.
        combat.start();
    }

    /**
     * If the player wins, this will write on the levels.txt file with new information to allow new levels to be played
     * @throws IOException If file isn't found
     */
    public void unlockLevels() throws IOException {
        Scanner input = new Scanner(new File("levels.txt"));
        ArrayList<String> levelStatus = new ArrayList<>();
        while (input.hasNext()){
            String word = input.nextLine();
            levelStatus.add(word);
            System.out.println(word);
        }
        FileWriter levelWriter = new FileWriter("levels.txt");
        // if player won level 1, unlock level 2
        if (currentLevel.equals("level1")) {
            levelWriter.write("level2-true\n" + levelStatus.get(1));
        }
        // if player won level 2, unlock level 3
        else if (currentLevel.equals("level2")) {
            levelWriter.write("level2-true\nlevel3-true\n");
        }
        // if player won level 3, keep all levels unlocked
        else {
            levelWriter.write("level2-true\nlevel3-true\n");
        }
        levelWriter.close();
    }

    /**
     * Once combat has ended, determine if the player won or loss and display appropriate messages
     * @throws IOException File not found error
     */
    public void combatEnded() throws IOException {
        // message to display if player won
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
        // message to display if player loss
        else {
            loseScreen.setDisable(false);
            loseScreen.setOpacity(1);
        }
    }

    /**
     * Button which sends the player back to the main menu
     * @param e NULL
     * @throws IOException File not found error
     */
    public void backToMenu(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("titleScreen.fxml"));
        Parent root = loader.load();
        ScreenController screenController = loader.getController();  // get controller to initialize coins
        screenController.initializeAssets();  // initialize coins
        gameSettings.switchScenes(root);

    }

    /**
     * Used to determine hit chance
     * @return int value used to determine hit or miss
     */
    public int RNG(){
        return (int) (Math.random() * 100);  // getting random number from 0 to 100
    }

    /**
     * Used to determine if the attack hit the defender
     * @param char1 character attacking
     * @param char2 character defending
     * @return whether they hit (true) or miss (false)
     */
    public boolean battleCalculations(CharacterCopy char1, CharacterCopy char2){
        int hitChance = char1.getAccuracy() - char2.getSpeed();  // formula for hit chance: accuracy - speed
        return hitChance >= RNG();  // if hitChance is greater than or equal to RNG, they will hit (return true)
    }

    /**
     * Combat sequence which is based on Animation Timer. In the Animation Timer class, the handle is called every frame
     */
    private class combatSequence extends AnimationTimer{
        public int timing = 0;

        /**
         * This method is called every frame and stops with the stop() method
         * @param l NULL
         */
        @Override
        public void handle(long l) {
            // player starts attacking
            if (timing == 600){
                // check to make sure player max HP is greater than 0
                if (playerCharacter.getHP() > 0){
                    attackRight(playerStickmanImage);
                    timing -= 1;
                }
                // otherwise, end player combat sequence
                else {
                    stop();  // stop combat animation
                    win = false;
                    // necessary try catch block because of IOException in combatEnded()
                    try {
                        combatEnded();
                    } catch (IOException e) {
                        System.out.println("TABIO GOT AN ERROR");
                    }
                }

            }
            // enemy starts attacking
            else if (timing == 300){
                // check to make sure enemy max HP is greater than 0
                if (enemyCharacter.getHP() > 0) {
                    attackLeft(enemyStickmanImage);
                    timing -= 1;
                }
                // otherwise, end player combat sequence
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
            // Once timing hits 0, reset to continue combat
            else if (timing == 0){
                timing = 600;
            }
            // otherwise, decrement timing
            else {
                timing -= 1;
            }
        }
    }

    /**
     * This Timer is used to determine the HIT or MISS blocks, and is also how the HP bars and text are updated
     */
    private class Timer extends AnimationTimer{
        public int timing = 0;
        public ImageView selectedImage;
        public int damageDealt = 0;
        public CharacterCopy currentChar;
        public Label charHP;
        public ProgressBar charProgressBar;
        @Override
        public void handle(long l) {
            // stop when timing hits 0
            if (timing == 0){
                stop();
            }
            // make HIT/MISS image disappear
            else if (timing == 30){
                selectedImage.setOpacity(0);
                timing -= 1;
                System.out.println("OP IS NOW 0");
            }
            // make HIT/MISS image appear
            else if (timing == 70){
                selectedImage.setOpacity(1);
                currentChar.setHP(currentChar.getHP() - damageDealt);
                if (currentChar.getHP() < 0){  // if HP less than 0, set HP to 0
                    currentChar.setHP(0);
                }
                charHP.setText(Integer.toString(currentChar.getHP()));
                charProgressBar.setProgress((double) currentChar.getHP() / currentChar.getMHP());  // set the health bar
                timing -= 1;
            }
            // otherwise decrement timing
            else {
                timing -= 1;
            }
        }

    }

    /**
     * Used for animation to make player attack enemy
     * @param image1 player image
     */
    public void attackRight(ImageView image1) {
        TranslateTransition transition = new TranslateTransition();  // Used to make character slide
        Timer myTime = new Timer();
        boolean HIT = battleCalculations(playerCharacter, enemyCharacter);
        transition.setNode(image1);  // set sliding image
        transition.setDuration(Duration.seconds(.5));  // how long the animation should take
        transition.setByX(350);  // how far they will travel
        rotateRight(image1);  // used to rotate slightly
        transition.setCycleCount(2);  // make them move backwards
        transition.setAutoReverse(true);  // return to original position
        transition.play();  // begin animation
        attackButton.setDisable(true);  // disable attack button
        myTime.timing = 150;
        int damage = playerCharacter.getAttack() - enemyCharacter.getDefense();
        myTime.currentChar = enemyCharacter;  // choose character to be attacked
        myTime.charHP = enemyHP;
        myTime.charProgressBar = enemyHPBar;
        // setting image to HIT if hit is successful
        if (HIT){
            myTime.damageDealt = damage;
            myTime.selectedImage = rightPOW;
        }
        // setting image to MISS if hit unsuccessful
        else {
            myTime.damageDealt = 0;
            myTime.selectedImage = rightMISS;
        }
        myTime.start();

    }

    /**
     * Used to slightly tilt player image
     * @param image1 player image
     */
    public void rotateRight(ImageView image1){
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(image1);  // set rotated image
        rotate.setDuration(Duration.seconds(.5));
        rotate.setByAngle(45);  // angle of rotation
        rotate.setCycleCount(2);
        rotate.setAutoReverse(true);
        rotate.play();  // play animation
    }

    /**
     * Animation to make enemy attack player, code is identical to attackRight but flipped some values for left side
     * @param image1 enemy image
     */
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

    /**
     * Animation used to slightly tilt enemy image, coding is nearly identical to rotateRight except for some value changes
     * @param image1 enemy image
     */
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
