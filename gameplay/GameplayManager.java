package org.OneStepAtATime.gameplay;

import java.util.List;

public class GameplayManager {

    private final String PLAYER = "*"; // The character doesn't matter since the player won't see this depicted
    private boolean gameActive = true;
    private List<List<String>> display;
    private PlayerLocation playerLocation;

    public GameplayManager(List<List<String>> display, PlayerLocation playerLocation) {
        this.display = display;
        this.playerLocation = playerLocation;
    }

    public boolean gameRunning() {
        return gameActive;
    }

    public PlayerLocation getPlayerLocation() {
        int displayComponentNum = 0;
        int i = 0;
        for (List<String> displayComponent : display) {

            if (displayComponent.contains(PLAYER)) {
                playerLocation.setArrayLocation(displayComponentNum);
                playerLocation.setIndexLocation(displayComponent.indexOf(PLAYER));
                break;
            }
            else if (i == display.size()) {
                playerLocation.setArrayLocation(-1);
                playerLocation.setIndexLocation(-1);
            }
            displayComponentNum++;
            i++;
        }
        return playerLocation;
    }

    public void setPlayerStartLocation() {
        for (List<String> displayComponent : display) {
            displayComponent.set(0, PLAYER);
            break;
        }
    }

    public void handleInput(String direction) {

        PlayerLocation playerLocation = getPlayerLocation();
        Integer currentHorizontalLocation = playerLocation.getIndexLocation();
        Integer currentVerticalLocation = playerLocation.getArrayLocation();

        switch (direction.toLowerCase()) {
            case "w": // "Up" movement
                if (!playerInTopRow()) {
                    Integer newVerticalLocation = currentVerticalLocation - 1;

                    clearLocation();
                    movePlayerVertically(newVerticalLocation, currentHorizontalLocation);
                }
                else {
                    System.out.println("You can't go any further this way.");
                }
                break;

            case "a": // "Left" movement
                if (!playerInLeftmostColumn()) {
                    Integer newHorizontalLocation = currentHorizontalLocation - 1;

                    clearLocation();
                    movePlayerHorizontally(newHorizontalLocation, currentVerticalLocation);
                    checkLocationValidity(currentHorizontalLocation);
                }
                else {
                    System.out.println("The only way out is to the right...");
                }
                break;

            case "s": // "Down" movement
                if (!playerInBottomRow()) {
                    Integer newVerticalLocation = currentVerticalLocation + 1;

                    clearLocation();
                    movePlayerVertically(newVerticalLocation, currentHorizontalLocation);
                }
                else {
                    System.out.println("You can't go any further this way.");
                }
                break;

            case "d": // "Right" movement
                if (!playerInRightmostColumn()) {
                    Integer newHorizontalLocation = currentHorizontalLocation + 1;

                    clearLocation();
                    movePlayerHorizontally(newHorizontalLocation, currentVerticalLocation);
                    checkLocationValidity(currentHorizontalLocation);
                }
                else {
                    System.out.println("Well done! You have completed the challenge.");
                    System.exit(0);
                }
                break;
        }
    }

    private void clearLocation() {
        for (List<String> displayComponent : display) {
            if (displayComponent.contains(PLAYER)) {
                int currentPlayerLocation = displayComponent.indexOf(PLAYER);
                displayComponent.set(currentPlayerLocation, "");
            }
        }
    }

    private void checkLocationValidity(Integer location) {
        if (location < 0) {
            gameOver();
        }
    }

    private void movePlayerVertically(Integer newVerticalLocation, Integer currentHorizontalLocation) {
        int i = 0;
        for (List<String> displayComponent : display) {
            if (i == newVerticalLocation) {
                displayComponent.set(currentHorizontalLocation, PLAYER);
                break;
            }
            i++;
        }
    }

    private void movePlayerHorizontally(Integer newHorizontalLocation, Integer currentHorizontalLocation) {
        int i = 0;
        for (List<String> displayComponent : display) {
            String landingLocation = displayComponent.get(newHorizontalLocation);

            if (i == currentHorizontalLocation && playerWillSurvive(landingLocation)) {
                displayComponent.set(newHorizontalLocation, PLAYER);
                break;
            }
            else if (i == display.size() - 1) {
                System.out.println("You hit a wall!");
                gameOver();
            }
            i++;
        }
    }

    private boolean playerWillSurvive(String endLocation) {
        boolean alive = true;
        if (endLocation.contains("|")) { // Player tried to move into a wall
            alive = false;
        }
        return alive;
    }

    private boolean playerInLeftmostColumn() {
        boolean inLeftColumn = false;
        PlayerLocation playerLocation = getPlayerLocation();
        if (playerLocation.getIndexLocation().equals(0)) {
            inLeftColumn = true;
        }
        return inLeftColumn;
    }

    private boolean playerInRightmostColumn() {
        boolean inRightColumn = false;
        PlayerLocation playerLocation = getPlayerLocation();
        if (playerLocation.getIndexLocation().equals(display.size() - 1)) {
            inRightColumn = true;
        }
        return inRightColumn;
    }

    private boolean playerInTopRow() {
        boolean inTopRow = false;
        PlayerLocation playerLocation = getPlayerLocation();
        if (playerLocation.getArrayLocation().equals(0)) {
            inTopRow = true;
        }
        return inTopRow;
    }

    private boolean playerInBottomRow() {
        boolean inBottomRow = false;
        Integer lastRow = display.size() - 1;
        PlayerLocation playerLocation = getPlayerLocation();
        if (playerLocation.getArrayLocation().equals(lastRow)) {
            inBottomRow = true;
        }
        return inBottomRow;
    }

    private void gameOver() {
        this.gameActive = false;
    }
}
