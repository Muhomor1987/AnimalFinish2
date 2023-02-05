package work;

import IslandStructure.Island;
import IslandStructure.Location;
import IslandStructure.Statistics;
import entities.Entity;
import entities.Organisms;
import util.FabricOfAnimals;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Reproducer implements Runnable {
    Island island;
    FabricOfAnimals fabricOfAnimals;
    Statistics statistics;

    public Reproducer(Island island, FabricOfAnimals fabricOfAnimals, Statistics statistics) {
        this.island = island;
        this.fabricOfAnimals = fabricOfAnimals;
        this.statistics = statistics;
    }

    private void reproduce(Location location) {
        Entity animalFirst = location.getAnimalsOnLocation().get(ThreadLocalRandom.current().nextInt(location.getAnimalsOnLocation().size()));
        Entity animalSecond = location.getAnimalsOnLocation().get(ThreadLocalRandom.current().nextInt(location.getAnimalsOnLocation().size()));
        Organisms animalFirstTYPE = animalFirst.getTYPE();
        Organisms animalSecondTYPE = animalSecond.getTYPE();
        if (animalFirstTYPE==animalSecondTYPE) {
                fabricOfAnimals.createNewAnimals(location,animalFirstTYPE,statistics);
                location.getAnimalsOnLocation().add(animalFirst);
                location.getAnimalsOnLocation().add(animalSecond);
        }
    }





    @Override
    public void run() {
        for (int i = 0; i < island.getXMax(); i++) {
            for (int j = 0; j < island.getYMax(); j++) {
                Location location = island.getLocations()[i][j];
                try {
                    if (island.getLocations()[i][j].getLock().tryLock(30, TimeUnit.MILLISECONDS)) {
                        reproduce(location);
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
