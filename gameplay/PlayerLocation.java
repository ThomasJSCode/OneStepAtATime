package org.OneStepAtATime.gameplay;

// Very basic data structure to record where the player is
public class PlayerLocation {

    private Integer arrayLocation;
    private Integer indexLocation;

    public void setArrayLocation(Integer arrayLocation) {
        this.arrayLocation = arrayLocation;
    }

    public Integer getArrayLocation() {
        return arrayLocation;
    }

    public void setIndexLocation(Integer indexLocation) {
        this.indexLocation = indexLocation;
    }

    public Integer getIndexLocation() {
        return indexLocation;
    }

}
