package com.example.gachagame;

import javafx.scene.image.Image;

public class CharacterCopy {
    private int attack;
    private int defense;
    private int speed;
    private int accuracy;
    private int HP;
    private int mHP; // max HP
    private String characterPortrait;

    public CharacterCopy(int attack, int defense, int speed, int accuracy,
                         int HP, int mHP, String characterPortrait) {
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.accuracy = accuracy;
        this.HP = HP;
        this.mHP = mHP;
        this.characterPortrait = characterPortrait;
    }

    // Accessors:
    public int getAttack() {
        return this.attack;
    }
    public int getDefense() {
        return this.defense;
    }
    public int getSpeed() {
        return this.speed;
    }
    public int getAccuracy() {
        return this.accuracy;
    }
    public int getHP() {
        return this.HP;
    }
    public int getMHP() {
        return this.mHP;
    }
    public String getCharacterPortrait() { return this.characterPortrait;}

    // Mutators:
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setHP(int HP) {
        this.HP = HP;
    }
    public void setMHP(int mHP) {
        this.mHP = mHP;
    }
    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }
    public void setCharacterPortrait(String characterPortrait) { this.characterPortrait = characterPortrait;}

    public String toString(){
        return String.format("%s%b%n %s%d%n",
                "Strength: ", this.getAttack(),
                "Intelligence: ", this.getDefense()
                );
    }
} // class end