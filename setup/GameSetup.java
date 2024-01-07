package org.OneStepAtATime.setup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameSetup {

    private List<List<String>> display;

    public GameSetup(List<List<String>> display) {
        this.display = display;
    }

    public void generateHazards() {

        List<Integer> validLocations = calculateValidHazardLocations();
        List<Integer> potentialOpenings = generateRandomOpenings();
        int hazardLimits = display.size();

        int i = 0;
        for (Integer location : validLocations) {
            for (List<String> displayComponent : display) {
                if (i == potentialOpenings.get(0)) {
                    displayComponent.set(location, " ");
                    Collections.rotate(potentialOpenings, 1);
                }
                else {
                    displayComponent.set(location, "|");
                }
                i++; // This helps ensure that each "column" has at least one opening
                if (i > hazardLimits) {
                    i = 0;
                }
            }
        }
    }

    public List<Integer> calculateValidHazardLocations() {

        int hazardLimits = display.size();
        List<Integer> range = IntStream.rangeClosed(0, hazardLimits)
                .filter(i -> i % 2 != 0)
                .boxed()
                .collect(Collectors.toList());

        return range;
    }

    public List<Integer> generateRandomOpenings() {
        int hazardLimits = display.size();
        List<Integer> validLocations = calculateValidHazardLocations();
        List<Integer> randomOpenings = new ArrayList<>();

        for (int i = 0; i < validLocations.size(); i++) {
            Integer opening = ThreadLocalRandom.current().nextInt(0, hazardLimits);
            randomOpenings.add(opening);
        }

        return randomOpenings;
    }


}
