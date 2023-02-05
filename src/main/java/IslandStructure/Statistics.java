package IslandStructure;

import entities.Constants;
import entities.Organisms;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class Statistics implements Runnable {
    Constants constants;

    public Statistics(Constants constants) {
        this.constants = constants;
    }

    private ConcurrentHashMap<Organisms, Integer> statistics = new ConcurrentHashMap<>() {{
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


    @Override
    public void run() {

        for (Organisms value : Organisms.values()
        ) {
            synchronized (statistics) {
                System.out.print(constants.getICON().get(value) + ": " + statistics.get(value) + "  ");
            }
        }
        System.out.println();


    }
}