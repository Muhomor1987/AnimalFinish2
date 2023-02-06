package IslandStructure;

import entities.Entity;
import entities.Organisms;
import lombok.Getter;
import lombok.Setter;
import util.FabricOfAnimals;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
public class Location implements Runnable {
    FabricOfAnimals fabricOfAnimals;
    Statistics statistics;
    private Lock lock = new ReentrantLock();

    private boolean isCreated = false;

    public Location(FabricOfAnimals fabricOfAnimals, Statistics statistics) {
        this.fabricOfAnimals = fabricOfAnimals;
        this.statistics = statistics;
    }

    private ArrayList<Entity> animalsOnLocation = new ArrayList<>();
    private ConcurrentLinkedQueue<Entity> animalsForMoving = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<Entity> animalsIn = new ConcurrentLinkedQueue<>();


    ConcurrentHashMap<Organisms, Integer> countAnimalsMapOnLocation = new ConcurrentHashMap<>() {{
        put(Organisms.WOLF, 0);
        put(Organisms.BOA, 0);
        put(Organisms.FOX, 0);
        put(Organisms.BEAR, 0);
        put(Organisms.EAGLE, 0);
        put(Organisms.HORSE, 0);
        put(Organisms.DEER, 0);
        put(Organisms.RABBIT, 0);
        put(Organisms.MOUSE, 0);
        put(Organisms.GOAT, 0);
        put(Organisms.SHEEP, 0);
        put(Organisms.BOAR, 0);
        put(Organisms.DUCK, 0);
        put(Organisms.BUFFALO, 0);
        put(Organisms.CATERPILLAR, 0);
        put(Organisms.PLANT, 0);
    }};


    public void liveLocation() {
        Entity animalFirst;
        Entity animalSecond;
        while (true) {
            try {
                if (lock.tryLock(300, TimeUnit.MILLISECONDS)) {
                    if (!animalsOnLocation.isEmpty()) {
                        animalFirst = animalsOnLocation.remove(ThreadLocalRandom.current().nextInt(animalsOnLocation.size() - 1));

                        if (!animalsOnLocation.isEmpty()) {
                            animalSecond = animalsOnLocation.remove(ThreadLocalRandom.current().nextInt(animalsOnLocation.size() - 1));
                            reproduction(animalFirst, animalSecond);
                            eat(animalFirst, animalSecond);
                            animalsOnLocation.addAll(animalsIn);
                            animalsIn.clear();


                        } else {
                            System.out.println("Location is empty");
                            lock.unlock();
                            Thread.sleep(1000);
                        }
                    } else {
                        System.out.println("Location is empty");
                        lock.unlock();
                        Thread.sleep(1000);
                    }

                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }

     private void eat(Entity animalFirst, Entity animalSecond) {
        Organisms animalFirstTYPE = animalFirst.getTYPE();
        Organisms animalSecondTYPE = animalSecond.getTYPE();
        if (animalFirst.getMAP_OF_FOOD().containsKey(animalSecondTYPE) || animalSecond.getMAP_OF_FOOD().containsKey(animalFirstTYPE)) {
            if (animalFirst.getMAP_OF_FOOD().containsKey(animalSecondTYPE)) {
                whoEating(animalSecond, animalFirst);
            }
            if (animalSecond.getMAP_OF_FOOD().containsKey(animalFirstTYPE)) {
                whoEating(animalFirst, animalSecond);
            }
        }
    }


     private void whoEating(Entity animalDead, Entity animalWhoEat) {
        Organisms animalDeadTYPE = animalDead.getTYPE();
        if (ThreadLocalRandom.current().nextInt(100) >= animalWhoEat.getMAP_OF_FOOD().get(animalDeadTYPE)) {
            if (animalDead.getWeight() >= animalWhoEat.getSATURATION()) {
                animalWhoEat.setWeight(animalWhoEat.getWEIGHT_MAX());
            } else {
                animalWhoEat.setWeight(animalWhoEat.getWeight() + animalDead.getWeight());
            }
            animalsForMoving.add(animalWhoEat);
            countAnimalsMapOnLocation.put(animalDeadTYPE, countAnimalsMapOnLocation.get(animalDeadTYPE) - 1);
            synchronized (statistics) {
                statistics.getStatistics().replace(animalDeadTYPE, statistics.getStatistics().get(animalDeadTYPE) - 1);
            }
            fabricOfAnimals.getPoolAnimals().get(animalDeadTYPE).add(animalDead);
        }
    }

    private void reproduction(Entity animalFirst, Entity animalSecond) {
        if (animalFirst.getTYPE() == animalSecond.getTYPE()) {
            lock.unlock();
            fabricOfAnimals.createNewAnimals(this, animalFirst.getTYPE(), statistics);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            animalsIn.add(animalFirst);
            animalsIn.add(animalSecond);
        }
    }


    @Override
    public void run() {
        liveLocation();
    }
}
