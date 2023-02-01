package org.example;

import IslandStructure.Island;
import IslandStructure.Location;
import IslandStructure.Statistics;
import entities.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.CreatorLocations;
import util.FabricOfAnimals;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws InterruptedException {
        logger.debug("Записать в лог");

        Constants constants  = new Constants();
        Statistics statistics = new Statistics(constants);
        Island island = new Island("Terra", constants);
        FabricOfAnimals fabricOfAnimals = new FabricOfAnimals(constants,island,statistics);
        CreatorLocations creatorLocations = new CreatorLocations(fabricOfAnimals,island,constants,statistics);
//        Location location =new Location(fabricOfAnimals,statistics);
        Executor executor = Executors.newFixedThreadPool(8);
        ExecutorService executorService = Executors.newWorkStealingPool();

//        executorService.execute(creatorLocations);
 //       executor.execute(creatorLocations);
executor.execute(creatorLocations);
executor.execute(creatorLocations);
executor.execute(creatorLocations);


        executorService.awaitTermination(20, TimeUnit.SECONDS);
//        System.out.println(executorService.isShutdown());





    }
}