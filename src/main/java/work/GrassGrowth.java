package work;

import IslandStructure.Island;
import IslandStructure.Location;
import IslandStructure.Statistics;
import entities.Entity;
import entities.Organisms;
import util.FabricOfAnimals;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class GrassGrowth implements Runnable {
    Island island;
    FabricOfAnimals fabricOfAnimals;
    Statistics statistics;

    public GrassGrowth(Island island, FabricOfAnimals fabricOfAnimals, Statistics statistics) {
        this.island = island;
        this.fabricOfAnimals = fabricOfAnimals;
        this.statistics = statistics;
    }

    private void grassGrowing(Location location) {
        for (int i = 0; i < 10; i++) {
            fabricOfAnimals.createNewAnimals(location, Organisms.PLANT, statistics);
        }

    }

    @Override
    public void run() {
        for (int i = 0; i < island.getXMax(); i++) {
            for (int j = 0; j < island.getYMax(); j++) {
                Location location = island.getLocations()[i][j];
                try {
                    if (island.getLocations()[i][j].getLock().tryLock(30, TimeUnit.MILLISECONDS)) {
                        grassGrowing(location);
                    }
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                } finally {
                    location.getLock().unlock();
                }
            }
        }
    }
}
