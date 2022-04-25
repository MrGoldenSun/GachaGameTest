package com.example.gachagame;
public class StickmanGoblin extends CharacterCopy{
    // Uninteresting constructor for StickmanGoblin
    public StickmanGoblin(){
        super(13, 1, 20, 85, 16,
                16, "goblin.png", "1 star");
    }
    public String getName() { return "Gorb Da Goblin";}
    public String getHometown() { return "Your mom's house";}
    public String getStory() {return "Gorb has a love for one thing and one thing only: smacking people with his club. Many unsuspecting citizens have met their demise at Gorb's hand. They all hear the same thing right before the end: \"Ya got Gorbbed!\"";}

}
