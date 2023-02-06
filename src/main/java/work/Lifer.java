package work;

import IslandStructure.Island;
import IslandStructure.Location;
import IslandStructure.Statistics;
import entities.Entity;
import entities.Organisms;
import util.FabricOfAnimals;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class Lifer implements Runnable {
    Island island;
    FabricOfAnimals fabricOfAnimals;
    Statistics statistics;

    public Lifer(Island island, FabricOfAnimals fabricOfAnimals, Statistics statistics) {
        this.island = island;
        this.fabricOfAnimals = fabricOfAnimals;
        this.statistics = statistics;
    }

    private void lifeCycle(Location location, Iterator<Entity> iterator) {
        while (iterator.hasNext()) {
            Entity entity = iterator.next();
            if (entity.getTYPE()!= Organisms.PLANT) {
                entity.setWeight(entity.getWeight() - (entity.getWEIGHT_MAX() * 0.1));
                if (entity.getWeight() < entity.getWEIGHT_MAX() / 2) {
                    iterator.remove();
                    fabricOfAnimals.getPoolAnimals().get(entity.getTYPE()).add(entity);
                    synchronized (statistics) {
                        statistics.getStatistics().replace(entity.getTYPE(), statistics.getStatistics().get(entity.getTYPE()) - 1);
                    }
                    location.getCountAnimalsMapOnLocation().put(entity.getTYPE(), location.getCountAnimalsMapOnLocation().get(entity.getTYPE()) - 1);
                }
            }
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < island.getXMax(); i++) {
            for (int j = 0; j < island.getYMax(); j++) {
                Location location = island.getLocations()[i][j];
                try {
                    if (island.getLocations()[i][j].getLock().tryLock(30, TimeUnit.MILLISECONDS)) {
/*                        Iterator<Entity> iterator = location.getAnimalsIn().iterator();
                        lifeCycle(location, iterator);*/
                        Iterator<Entity> iterator1 = location.getAnimalsOnLocation().iterator();
                        lifeCycle(location, iterator1);
/*                        Iterator<Entity> iterator2 = location.getAnimalsForMoving().iterator();
                        lifeCycle(location, iterator2);*/
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
