package com.example.gachagame;

public class StickmanRogue extends CharacterCopy{
    public StickmanRogue(){
        super(5, 0, 45, 110, 17, 17, "rogue.png", "1 star");
    }
    public String getName(){ return "Simon The Pilferer";}
    public String getHometown() { return "Tilted Towers";}
    public String getStory() { return "Simon strikes when you least expect it-- paralyzing people with the poison on his blade before making off with their valuables. However, he has trouble with lockpicking, so as long as you lock your doors and valuables at night he won't be a threat to you at all.";}
}
