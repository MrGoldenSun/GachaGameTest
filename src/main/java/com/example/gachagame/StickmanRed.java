package com.example.gachagame;

public class StickmanRed extends CharacterCopy{

    private final String name = "Gorforbius The Chosen";
    private final String hometown = "Hometown: Austin, Texas";
    private final String story = "Gorforbius is a simple stick man with a simple stick plan. Keep on swinging at " +
            "things until they stop moving. His favorite things include big swords, the color red, and stale bread. " +
            "He is noteable for having an IQ of 120 and placing 4th in Science Olympiad.";

    public StickmanRed() {
        super(8, 1, 20, 95, 25,
                25, "RedStickmanNoBackground.png");
    }

    public String getName() {
        return "Gorforbius The Chosen";
    }

    public String getHometown() {
        return "Hometown: Austin, Texas";
    }

    public String getStory() {
        return "Gorforbius is a simple stick man with a simple stick plan. Keep on swinging at " +
                "things until they stop moving. His favorite things include big swords, the color red, and stale bread. " +
                "He is noteable for having an IQ of 120 and placing 4th in Science Olympiad.";
    }
}
