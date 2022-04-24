package com.example.gachagame;

public class StickmanGoddess extends CharacterCopy {
    public StickmanGoddess(){
        super(20, 5, 25, 115, 30, 30, "goddess.png", "3 stars");
    }
    public String getName(){return "The Goddess";}
    public String getHometown(){ return "The Holy Kingdom";}
    public String getStory(){return "The Goddess comes from a world without 1-stars or 2-stars. While her true name is unknown, it is believed she has come down to deliver devine judgement on those she deems unworthy.";}
}
