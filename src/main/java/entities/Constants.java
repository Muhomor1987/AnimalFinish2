package entities;


import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;


@Getter
public class Constants implements Serializable {

    int lifeCycleTime = 1;
    int xCoordinate = 20;
    int yCoordinate = 20;

    private final HashMap<Organisms, Integer> maxAnimalForKindOfLocations = new HashMap<Organisms, Integer>() {
        {
            put(Organisms.WOLF, 30);
            put(Organisms.BOA, 30);
            put(Organisms.FOX, 30);
            put(Organisms.BEAR, 5);
            put(Organisms.EAGLE, 20);
            put(Organisms.HORSE, 20);
            put(Organisms.DEER, 20);
            put(Organisms.RABBIT, 150);
            put(Organisms.MOUSE, 500);
            put(Organisms.GOAT, 140);
            put(Organisms.SHEEP, 140);
            put(Organisms.BOAR, 50);
            put(Organisms.BUFFALO, 200);
            put(Organisms.DUCK, 10);
            put(Organisms.CATERPILLAR, 1000);
            put(Organisms.PLANT, 200);
        }
    };

    private final HashMap<Organisms, Double> maxWeight = new HashMap<>() {
        {
            put(Organisms.WOLF, 50.);
            put(Organisms.BOA, 15.);
            put(Organisms.FOX, 8.);
            put(Organisms.BEAR, 500.);
            put(Organisms.EAGLE, 6.);
            put(Organisms.HORSE, 400.);
            put(Organisms.DEER, 300.);
            put(Organisms.RABBIT, 2.);
            put(Organisms.MOUSE, 0.05);
            put(Organisms.GOAT, 60.);
            put(Organisms.SHEEP, 70.);
            put(Organisms.BOAR, 400.);
            put(Organisms.BUFFALO, 700.);
            put(Organisms.DUCK, 1.);
            put(Organisms.CATERPILLAR, 0.1);
            put(Organisms.PLANT, 1.);
        }
    };

    private final HashMap<Organisms, Integer> SPEED = new HashMap<>() {
        {
            put(Organisms.WOLF, 3);
            put(Organisms.BOA, 1);
            put(Organisms.FOX, 2);
            put(Organisms.BEAR, 2);
            put(Organisms.EAGLE, 3);
            put(Organisms.HORSE, 4);
            put(Organisms.DEER, 4);
            put(Organisms.RABBIT, 2);
            put(Organisms.MOUSE, 1);
            put(Organisms.GOAT, 3);
            put(Organisms.SHEEP, 3);
            put(Organisms.BOAR, 4);
            put(Organisms.BUFFALO, 3);
            put(Organisms.DUCK, 4);
            put(Organisms.CATERPILLAR, 0);
            put(Organisms.PLANT, 0);
        }
    };

    private final HashMap<Organisms, String> ICON = new HashMap<>() {
        {
            put(Organisms.WOLF, "\uD83D\uDC3A");
            put(Organisms.BOA, "\ud83d\udc0d");
            put(Organisms.FOX, "\ud83e\udd8a");
            put(Organisms.BEAR, "\ud83d\udc3b");
            put(Organisms.EAGLE, "\ud83e\udd85");
            put(Organisms.HORSE, "\ud83d\udc0e");
            put(Organisms.DEER, "\ud83e\udd8c");
            put(Organisms.RABBIT, "\ud83d\udc07");
            put(Organisms.MOUSE, "\ud83d\udc01");
            put(Organisms.GOAT, "\ud83d\udc10");
            put(Organisms.SHEEP, "\ud83d\udc11");
            put(Organisms.BOAR, "\ud83d\udc17");
            put(Organisms.BUFFALO, "\ud83d\udc02");
            put(Organisms.DUCK, "\ud83e\udd86");
            put(Organisms.CATERPILLAR, "\ud83d\udc1b");
            put(Organisms.PLANT, "\ud83c\udf31");
        }
    };
    private final HashMap<Organisms, Double> SATURATION = new HashMap<>() {
        {
            put(Organisms.WOLF, 8.);
            put(Organisms.BOA, 3.);
            put(Organisms.FOX, 2.);
            put(Organisms.BEAR, 80.);
            put(Organisms.EAGLE, 1.);
            put(Organisms.HORSE, 60.);
            put(Organisms.DEER, 50.);
            put(Organisms.RABBIT, 0.45);
            put(Organisms.MOUSE, 0.1);
            put(Organisms.GOAT, 10.);
            put(Organisms.SHEEP, 15.);
            put(Organisms.BOAR, 50.);
            put(Organisms.BUFFALO, 100.);
            put(Organisms.DUCK, 0.15);
            put(Organisms.CATERPILLAR, 0.);
            put(Organisms.PLANT, 0.);
        }
    };
    private final HashMap<Organisms, HashMap<Organisms, Integer>> MapOfFood = new HashMap<>() {
        {
            put(Organisms.WOLF, new HashMap<>() {
                {
                    put(Organisms.HORSE, 10);
                    put(Organisms.DEER, 15);
                    put(Organisms.RABBIT, 60);
                    put(Organisms.MOUSE, 80);
                    put(Organisms.GOAT, 60);
                    put(Organisms.BOAR, 15);
                    put(Organisms.BUFFALO, 10);
                    put(Organisms.DUCK, 40);
                }
            });
            put(Organisms.BOA, new HashMap<>() {
                {
                    put(Organisms.FOX, 15);
                    put(Organisms.RABBIT, 20);
                    put(Organisms.MOUSE, 40);
                    put(Organisms.DUCK, 10);
                }
            });
            put(Organisms.FOX, new HashMap<>() {
                {
                    put(Organisms.RABBIT, 70);
                    put(Organisms.MOUSE, 90);
                    put(Organisms.DUCK, 60);
                    put(Organisms.CATERPILLAR, 40);
                }
            });
            put(Organisms.BEAR, new HashMap<>() {
                {
                    put(Organisms.BOA, 80);
                    put(Organisms.HORSE, 40);
                    put(Organisms.DEER, 80);
                    put(Organisms.RABBIT, 80);
                    put(Organisms.MOUSE, 90);
                    put(Organisms.GOAT, 70);
                    put(Organisms.BOAR, 50);
                    put(Organisms.BUFFALO, 20);
                    put(Organisms.DUCK, 10);
                }
            });
            put(Organisms.EAGLE, new HashMap<>() {
                {
                    put(Organisms.FOX, 10);
                    put(Organisms.RABBIT, 90);
                    put(Organisms.MOUSE, 90);
                    put(Organisms.DUCK, 80);
                }
            });
            put(Organisms.HORSE, new HashMap<>() {
                {
                    put(Organisms.PLANT, 100);
                }
            });
            put(Organisms.DEER, new HashMap<>() {
                {
                    put(Organisms.PLANT, 100);
                }
            });
            put(Organisms.RABBIT, new HashMap<>() {
                {
                    put(Organisms.PLANT, 100);
                }
            });
            put(Organisms.MOUSE, new HashMap<>() {
                {
                    put(Organisms.PLANT, 100);
                    put(Organisms.CATERPILLAR, 90);
                }
            });
            put(Organisms.GOAT, new HashMap<>() {
                {
                    put(Organisms.PLANT, 100);
                }
            });
            put(Organisms.SHEEP, new HashMap<>() {
                {
                    put(Organisms.PLANT, 100);
                }
            });
            put(Organisms.BOAR, new HashMap<>() {
                {
                    put(Organisms.PLANT, 100);
                    put(Organisms.MOUSE, 50);
                    put(Organisms.CATERPILLAR, 90);
                }
            });
            put(Organisms.BUFFALO, new HashMap<>() {
                {
                    put(Organisms.PLANT, 100);
                }
            });
            put(Organisms.DUCK, new HashMap<>() {
                {
                    put(Organisms.PLANT, 100);
                }
            });
            put(Organisms.CATERPILLAR, new HashMap<>() {
                {
                    put(Organisms.PLANT, 100);
                }
            });
            put(Organisms.PLANT, new HashMap<>());
        }
    };
}