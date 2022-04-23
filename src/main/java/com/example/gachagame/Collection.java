package com.example.gachagame;

import java.util.Random;

public class Collection {
    private int badLuck = 0;
    Random rand = new Random();

    public CharacterCopy rollCharacter(){
        if (badLuck > 2) {
            //To be added

        }
        int starRoll = rand.nextInt(100);
        if (starRoll > 95) {
            badLuck = 0;
            //To be added
        } else if (starRoll > 70) {
            badLuck += 1;
            //randomly select a 2 star char (rand 0-1) -> switch case/if else statement to select the character
            //read file and set a 2 star character to true (replace text after the dash, even if true, stays true);
        } else {
            badLuck += 1;
            //randomly select a 1 star char (rand 0-2) -> switch case/if else statement to select the character
            //read file and set a 1 star character to true (replace text after the dash, even if true, stays true);
        }
    }
}
