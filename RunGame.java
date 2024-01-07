package org.OneStepAtATime;

import org.OneStepAtATime.gameplay.GameStartupInfo;
import org.OneStepAtATime.gameplay.GameplayManager;
import org.OneStepAtATime.gameplay.PlayerLocation;
import org.OneStepAtATime.setup.DisplaySetup;
import org.OneStepAtATime.setup.GameSetup;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class RunGame {
    public static void main(String[] args) throws IOException, InterruptedException {
        runGame();
    }

    public static void runGame() throws IOException, InterruptedException {
        DisplaySetup displayConfig = new DisplaySetup();
        List<List<String>> display = displayConfig.generateEasyDisplay();

        GameSetup gameConfig = new GameSetup(display);
        gameConfig.generateHazards();

        PlayerLocation playerLocation = new PlayerLocation();
        GameplayManager gamePlay = new GameplayManager(display, playerLocation);

        GameStartupInfo.showStartupInfo();
        displayConfig.showDisplay(display);

        gamePlay.setPlayerStartLocation();

        Thread.sleep(5000);
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // Clears screen on Windows
        System.out.println("Begin!");

        while (gamePlay.gameRunning()) {
            Scanner inputDetection = new Scanner(System.in);
            String input = inputDetection.nextLine();
            gamePlay.handleInput(input);
        }

        System.out.println("The game has ended. Would you like to play again? (y/n)");
        Scanner inputToContinue = new Scanner(System.in);
        String input = inputToContinue.nextLine();
        switch (input.toLowerCase()) {
            case "y":
                main(null);
                break;

            case "n":
                System.out.println("Thanks for playing!");
                System.exit(0);
                break;
            }
        }
    }