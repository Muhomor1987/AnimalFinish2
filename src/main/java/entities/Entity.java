package entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public  class Entity implements Cloneable{
    public String name;

    public double weight;

    //final
    private final double WEIGHT_MAX;
    private final int SPEED;
    private final double SATURATION;
    private final Organisms TYPE;
    private final String icon;
    private final HashMap<Organisms, Integer> MAP_OF_FOOD;

    private boolean isLife = true;

    public Entity(double WEIGHT_MAX, int SPEED, double SATURATION, Organisms TYPE, String icon, HashMap<Organisms, Integer> MAP_OF_FOOD) {
        this.WEIGHT_MAX = WEIGHT_MAX;
        this.SPEED = SPEED;
        this.SATURATION = SATURATION;
        this.TYPE = TYPE;
        this.icon = icon;
        this.MAP_OF_FOOD = MAP_OF_FOOD;
    }

    @Override
    public Entity clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (Entity) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}