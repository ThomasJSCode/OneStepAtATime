package org.OneStepAtATime.setup;

import java.util.ArrayList;
import java.util.List;

public class DisplaySetup {
    private final int EASY_DIFFICULTY_DISPLAY = 8;
    private final int MEDIUM_DIFFICULTY_DISPLAY = 12;
    private final int HARD_DIFFICULTY_DISPLAY = 16;

    public void showDisplay(List<List<String>> display) {
        for (List<String> displayComponent : display) {
            String formattedDisplay = displayComponent.toString()
                    .replace(",", " ")
                    .replace("[", "")
                    .replace("]", "");

            System.out.println(formattedDisplay);
        }
    }

    public List<List<String>> generateEasyDisplay() {
        return generateDisplay(EASY_DIFFICULTY_DISPLAY);
    }

    public List<List<String>> generateMediumDisplay() {
        return generateDisplay(MEDIUM_DIFFICULTY_DISPLAY);
    }

    public List<List<String>> generateHardDisplay() {
        return generateDisplay(HARD_DIFFICULTY_DISPLAY);
    }

    private List<List<String>> generateDisplay(int displayType) {
        List<List<String>> display = new ArrayList<>();
        for (int i = 0; i < displayType; i++) {
            List<String> displayComponent = new ArrayList<>();
            for (int j = 0; j < displayType; j++) {
                displayComponent.add(""); // "Open" spaces
            }
            display.add(displayComponent);
        }
        return display;
    }
}
