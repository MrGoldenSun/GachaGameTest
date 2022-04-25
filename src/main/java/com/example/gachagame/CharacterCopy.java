package com.example.gachagame;

/**
 * Used as outline for all player classes
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
    private String name;
    private String hometown;
    private String story;

    // Constructor which takes all parameters
    public CharacterCopy(int attack, int defense, int speed, int accuracy,
                         int HP, int mHP, String characterPortrait, String stars, String name, String hometown,
                         String story) {
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.accuracy = accuracy;
        this.HP = HP;
        this.mHP = mHP;
        this.characterPortrait = characterPortrait;
        this.stars = stars;
        this.name = name;
        this.hometown = hometown;
        this.story = story;
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
    public String getCharacterPortrait() { return this.characterPortrait; }
    public String getStars() { return this.stars; }
    public String getName() {return name;}
    public String getHometown() {return hometown;}
    public String getStory() {return story;}



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
    public void setCharacterPortrait(String characterPortrait) { this.characterPortrait = characterPortrait; }
    public void setStars(String stars) { this.stars = stars; }
    public void setName(String name) { this.name = name; }
    public void setHometown(String hometown) {this.hometown = hometown;}
    public void setStory(String story) {this.story = story;}

    // Used for displaying stats in multiple areas
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