package com.example.gachagame;

import java.util.Random;

public class Collection {
    private CharacterCopy[] OneStarList;
    private CharacterCopy[] TwoStarList;
    private CharacterCopy[] ThreeStarList;
    private int badLuck = 0;
    Random rand = new Random();

    public Collection(CharacterCopy[] OneStarList, CharacterCopy[] TwoStarList, CharacterCopy[] ThreeStarList) {
        this.OneStarList = OneStarList;
        this.TwoStarList = TwoStarList;
        this.ThreeStarList = ThreeStarList;
    }

    public CharacterCopy rollCharacter(){
        if (badLuck > 2) {
            return ThreeStarList[rand.nextInt(ThreeStarList.length)];
        }
        
        int starRoll = rand.nextInt(100);
        if (starRoll > 95) {
            badLuck = 0;
            return ThreeStarList[rand.nextInt(ThreeStarList.length)];
        } else if (starRoll > 70) {
            badLuck += 1;
            return TwoStarList[rand.nextInt(TwoStarList.length)];
        } else {
            badLuck += 1;
            return OneStarList[rand.nextInt(OneStarList.length)];
        }
    }
}
