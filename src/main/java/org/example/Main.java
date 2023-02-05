package org.example;

import IslandStructure.Island;
import IslandStructure.Location;
import IslandStructure.Statistics;
import entities.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.CreatorLocations;
import util.FabricOfAnimals;
import work.Eater;
import work.GrassGrowth;
import work.Lifer;
import work.Mover;

import java.util.List;
import java.util.concurrent.*;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) throws InterruptedException {

        logger.debug("Записать в лог");
        Constants constants = new Constants();
        Statistics statistics = new Statistics(constants);
        Island island = new Island("Terra", constants);
        FabricOfAnimals fabricOfAnimals = new FabricOfAnimals(constants, island, statistics);
        CreatorLocations creatorLocations = new CreatorLocations(fabricOfAnimals, island, constants, statistics);

        ScheduledExecutorService statisticsView = Executors.newSingleThreadScheduledExecutor();

        statisticsView.scheduleWithFixedDelay(statistics,5,2,TimeUnit.SECONDS);


        creatorLocations.run();
        Thread.sleep(3000);


        Eater eater = new Eater(island,fabricOfAnimals,statistics);
        ScheduledExecutorService eatThread = Executors.newSingleThreadScheduledExecutor();
        eatThread.scheduleWithFixedDelay(eater,4000,1000,TimeUnit.MILLISECONDS);

        GrassGrowth grassGrowth = new GrassGrowth(island,fabricOfAnimals,statistics);
        ScheduledExecutorService grassGrowthThread = Executors.newSingleThreadScheduledExecutor();
        grassGrowthThread.scheduleWithFixedDelay(grassGrowth,4500,4000,TimeUnit.MILLISECONDS);

        Mover mover = new Mover(island);
        ScheduledExecutorService moverThread = Executors.newSingleThreadScheduledExecutor();
        moverThread.scheduleWithFixedDelay(mover,4200,3000,TimeUnit.MILLISECONDS);

        Lifer lifer = new Lifer(island,fabricOfAnimals,statistics);
        ScheduledExecutorService liferWork = Executors.newSingleThreadScheduledExecutor();
        liferWork.scheduleWithFixedDelay(lifer,6,3,TimeUnit.SECONDS);






    }
}