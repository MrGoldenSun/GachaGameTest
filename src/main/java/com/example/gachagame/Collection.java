package com.example.gachagame;

import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Collection {
    private int badLuck = 0;
    Random rand = new Random();

    public void rollCharacter() throws IOException{
        String content = "";
        String chosen="";

        int starRoll = rand.nextInt(100);
        if (starRoll > 95 || badLuck > 2) {
            badLuck = 0;
            chosen = "stickman6";
        } else if (starRoll > 70) {
            badLuck += 1;
            int roll = rand.nextInt(2);
            if (roll == 0) {
                chosen = "stickman4";
            } else if (roll == 1) {
                chosen = "stickman5";
            }
        } else {
            badLuck += 1;
            int roll = rand.nextInt(3);
            if (roll == 0) {
                chosen = "stickman1";
            } else if (roll == 1) {
                chosen = "stickman2";
            } else if (roll == 2) {
                chosen = "stickman3";
            }
        }
        
        Scanner stickmanReader = new Scanner("stickman.txt");
        StringBuffer buffer = new StringBuffer();
        while (stickmanReader.hasNextLine()) {
            buffer.append(stickmanReader.nextLine()+System.lineSeparator());
        }
        content = buffer.toString();
        stickmanReader.close();

        content = content.replace(chosen+"-false", chosen+"-true");

        FileWriter stickmanWriter = new FileWriter("stickman.txt");
        stickmanWriter.write(content);
        stickmanWriter.close();
    }
}
