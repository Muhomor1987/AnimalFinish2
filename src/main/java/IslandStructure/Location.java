package IslandStructure;

import entities.Entity;
import entities.Organisms;
import lombok.Getter;
import lombok.Setter;
import util.FabricOfAnimals;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
public class Location{
    FabricOfAnimals fabricOfAnimals;
    Statistics statistics;
    private  Lock lock = new ReentrantLock();

    private boolean isCreated = false;

    public Location(FabricOfAnimals fabricOfAnimals, Statistics statistics) {
        this.fabricOfAnimals = fabricOfAnimals;
        this.statistics = statistics;
    }

    private ArrayList<Entity> animalsOnLocation = new ArrayList<>();
    private ConcurrentLinkedQueue<Entity> animalsForMoving = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<Entity> animalsIn = new ConcurrentLinkedQueue<>();


    ConcurrentHashMap<Organisms, Integer> countAnimalsMapOnLocation;

}