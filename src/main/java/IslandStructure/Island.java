package IslandStructure;

import entities.Constants;
import lombok.Getter;

import java.util.concurrent.locks.Lock;

@Getter
public class Island {
    Lock islandLock;
    Constants constants;
    private String name;

    private int xMax;

    private int yMax;


    private final int totalLocations;

    private final Location[][] locations;

    public Island(String name, Constants constants) {

        this.name = name;
        this.xMax = constants.getXCoordinate();
        this.yMax = constants.getYCoordinate();
        totalLocations = xMax * yMax;
        locations = new Location[xMax][yMax];
    }
}
