package com.example.gachagame;
//DISABLED CLASS
public class Character {
    private boolean PM; // True = Physical, False = Magical
    private int strength;
    private int intelligence;
    private int fortitude;
    private int will;
    private int speed;
    private int accuracy;
    private int HP;
    private int mHP; // max HP
    private boolean owned = false;

    public Character(boolean PM, int strength, int intelligence, int fortitude, int will, int speed, int accuracy, int HP, int mHP) {
        this.PM = PM;
        this.strength = strength;
        this.intelligence = intelligence;
        this.fortitude = fortitude;
        this.will = will;
        this.speed = speed;
        this.accuracy = accuracy;
        this.HP = HP;
        this.mHP = mHP;
    }

    // Accessors:
    public boolean getPM() {
        return this.PM;
    }
    public int getStrength() {
        return this.strength;
    }
    public int getIntelligence() {
        return this.intelligence;
    }
    public int getFortitude() {
        return this.fortitude;
    }
    public int getWill() {
        return this.will;
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
    public boolean isOwned() {
        return this.owned;
    }

    // Mutators:
    public void setPM(boolean PM) {
        this.PM = PM;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
    public void setFortitude(int fortitude) {
        this.fortitude = fortitude;
    }
    public void setWill(int will) {
        this.will = will;
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
    public void owned() {
        this.owned = true;
    }

    public String toString(){
        return String.format("%s%b%n %s%d%n %s%d%n %s%d%n %s%d%n",
                "T for Physical F for Magical: ", this.getPM(),
                "Strength: ", this.getStrength(),
                "Fortitude: ", this.getFortitude(),
                "Intelligence: ", this.getIntelligence(),
                "Fortitude: ", this.getFortitude()
                );
    }
} // class end