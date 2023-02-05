package util;

import IslandStructure.Island;
import IslandStructure.Location;
import IslandStructure.Statistics;
import entities.Constants;
import entities.Organisms;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class CreatorLocations implements Runnable {
    FabricOfAnimals fabricOfAnimals;
    Island island;
    Constants constants;
    Statistics statistics;

    public CreatorLocations(FabricOfAnimals fabricOfAnimals, Island island, Constants constants, Statistics statistics) {
        this.fabricOfAnimals = fabricOfAnimals;
        this.island = island;
        this.constants = constants;
        this.statistics = statistics;
        for (int i = 0; i < island.getXMax(); i++) {
            for (int j = 0; j <island.getYMax(); j++) {
                island.getLocations()[i][j]=new Location(fabricOfAnimals,statistics);
            }
        }

    }
    private void createLocations() {
        for (int i = 0; i < island.getXMax(); i++) {
            for (int j = 0; j < island.getYMax(); j++) {
                try {
                    if (island.getLocations()[i][j].getLock().tryLock(30, TimeUnit.MILLISECONDS)) {
                        if (!island.getLocations()[i][j].isCreated()) {
                            island.getLocations()[i][j].setCreated(true);
/*                            ConcurrentHashMap<Organisms, Integer> countAnimalsMapOnLocationTMP = new ConcurrentHashMap<>();
                            for (Organisms k : Organisms.values()
                            ) {
                                countAnimalsMapOnLocationTMP.put(k, ThreadLocalRandom.current().nextInt(constants.getMaxAnimalForKindOfLocations().get(k)));
                            }
                            island.getLocations()[i][j].setCountAnimalsMapOnLocation(countAnimalsMapOnLocationTMP);*/
                            for (Organisms k : Organisms.values()
                            ) {
                                int countAnimalsOnLocationType = ThreadLocalRandom.current().nextInt(constants.getMaxAnimalForKindOfLocations().get(k));
                                for (int l = 0; l < countAnimalsOnLocationType ; l++) {
                                    fabricOfAnimals.createNewAnimals(island.getLocations()[i][j],k,statistics);
                                }
                            }
                            island.getLocations()[i][j].getAnimalsOnLocation().addAll(island.getLocations()[i][j].getAnimalsIn());
                            island.getLocations()[i][j].getAnimalsIn().clear();
                            System.out.println(Thread.currentThread().getName() +"___"+ island.getLocations()[i][j].getAnimalsOnLocation().size() +"___"+i+"___" + j);
  //                        island.getLocations()[i][j].getLock().unlock();

                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                finally {
                    island.getLocations()[i][j].getLock().unlock();
                }
            }
        }
    }

    @Override
    public void run() {
        createLocations();
    }


}