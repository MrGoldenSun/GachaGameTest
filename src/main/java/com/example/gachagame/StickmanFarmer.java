package com.example.gachagame;

public class StickmanFarmer extends CharacterCopy{

    public StickmanFarmer(){
        super(7, 4, 10, 85, 21,
                21, "farmer.png", "1 star");
    }
    public String getName(){ return "Fred The Farmer";}
    public String getHometown(){ return "Idaho";}
    public String getStory(){ return "Frank enjoys the simple pleasures of life: waking up at 4:00am and tending to his crops. He doesn't like to fight, but years of training has made him handy with a pitchfork.";}
}
