package work;

import IslandStructure.Island;
import IslandStructure.Location;
import IslandStructure.Statistics;
import entities.Entity;
import entities.Organisms;
import util.FabricOfAnimals;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Eater implements Runnable {
    Island island;
    FabricOfAnimals fabricOfAnimals;
    Statistics statistics;

    public Eater(Island island, FabricOfAnimals fabricOfAnimals, Statistics statistics) {
        this.island = island;
        this.fabricOfAnimals = fabricOfAnimals;
        this.statistics = statistics;
    }

    private void eat(Location location) {
        Entity animalFirst = location.getAnimalsOnLocation().get(ThreadLocalRandom.current().nextInt(location.getAnimalsOnLocation().size()));
        Entity animalSecond = location.getAnimalsOnLocation().get(ThreadLocalRandom.current().nextInt(location.getAnimalsOnLocation().size()));
        Organisms animalFirstTYPE = animalFirst.getTYPE();
        Organisms animalSecondTYPE = animalSecond.getTYPE();
        if (animalFirst.getMAP_OF_FOOD().containsKey(animalSecondTYPE) || animalSecond.getMAP_OF_FOOD().containsKey(animalFirstTYPE)) {
            if (animalFirst.getMAP_OF_FOOD().containsKey(animalSecondTYPE)) {
                whoEating(animalSecond, animalFirst,location);
            }
            if (animalSecond.getMAP_OF_FOOD().containsKey(animalFirstTYPE)) {
                whoEating(animalFirst, animalSecond,location);
            }
        }
    }


    private void whoEating(Entity animalDead, Entity animalWhoEat,Location location) {
        Organisms animalDeadTYPE = animalDead.getTYPE();
        if (ThreadLocalRandom.current().nextInt(100) <= animalWhoEat.getMAP_OF_FOOD().get(animalDeadTYPE)) {
            if (animalDead.getWeight() >= animalWhoEat.getSATURATION()) {
                animalWhoEat.setWeight(animalWhoEat.getWEIGHT_MAX());
            } else {
                animalWhoEat.setWeight(animalWhoEat.getWeight() + animalDead.getWeight());
            }
            location.getAnimalsForMoving().add(animalWhoEat);
            location.getCountAnimalsMapOnLocation().replace(animalDeadTYPE, location.getCountAnimalsMapOnLocation().get(animalDeadTYPE) - 1);
            synchronized (statistics) {
                statistics.getStatistics().replace(animalDeadTYPE, statistics.getStatistics().get(animalDeadTYPE) - 1);
            }
            fabricOfAnimals.getPoolAnimals().get(animalDeadTYPE).add(animalDead);
        }
    }


    @Override
    public void run() {
        for (int i = 0; i < island.getXMax(); i++) {
            for (int j = 0; j < island.getYMax(); j++) {
                Location location = island.getLocations()[i][j];
                try {
                    if (island.getLocations()[i][j].getLock().tryLock(30, TimeUnit.MILLISECONDS)) {
                        eat(location);
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
