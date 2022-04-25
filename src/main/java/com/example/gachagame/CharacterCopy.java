package com.example.gachagame;

/***
 * CharacterCopy class is a copy of the original Character class with less stats. It is the working class we use to define
 * character objects throughout the application. These stats control the character's information and their profile pictures
 * to be used in any screens that show characters.
 */
public class CharacterCopy {
    private int attack;
    private int defense;
    private int speed;
    private int accuracy;
    private int HP;
    private int mHP; // max HP
    private String characterPortrait;

    private String stars;

    // Constructor for CharacterCopy class
    public CharacterCopy(int attack, int defense, int speed, int accuracy,
                         int HP, int mHP, String characterPortrait, String stars) {
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.accuracy = accuracy;
        this.HP = HP;
        this.mHP = mHP;
        this.characterPortrait = characterPortrait;
        this.stars = stars;
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

    public String getStars() { return this.stars; }

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

    public String getStats(){
        return String.format("%s%s%n%s%s%n%s%s%n%s%s%n%s%s%n",
                "Attack: ", this.getAttack(),
                "Defense: ", this.getDefense(),
                "Speed: ", this.getSpeed(),
                "Accuracy: ", this.getAccuracy(),
                "Hit Points: ", this.getMHP()
        );
    }

    public String toString(){
        return getStats();
    }
} // class end