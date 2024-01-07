package org.OneStepAtATime.gameplay;

import java.util.Scanner;

public class GameStartupInfo {

    private GameStartupInfo() {
    }

    public static void showStartupInfo() throws InterruptedException {
        System.out.println("Your 'character' will start in the top-left corner of the maze.\n" +
                "Navigate through the maze openings using WASD to succeed.\n" +
                "... However, this game is console-based, so you'll have to hit 'enter' after each key entry. Sorry.\n\n" +
                "The maze will only appear on screen for a few seconds -- you must try to memorize the correct route.\n" +
                "If you collide into one of the walls, the game will end.\n" +
                "Press and enter 'y' to commence the game.\n");

        Scanner acceptGameInput = new Scanner(System.in);
        String accept = acceptGameInput.nextLine();
        if (accept.toLowerCase().contains("y")) {
            System.out.println("Good luck!");
            Thread.sleep(2000);
        }
    }
}
