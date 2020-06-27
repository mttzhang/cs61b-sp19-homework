package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates clorus with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * creates a clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }



    /**
     * Should return a color with red = 34, blue = 231, and green = 0.
     */
    public Color color() {
        r = 34;
        b = 231;
        g = 0;
        return color(r, g, b);
    }

    /**
     * get the creature's whole energy.
     */
    public void attack(Creature c) {
        energy += c.energy();
    }

    /**
     * Clorus should lose 0.03 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        energy -= 0.03;
        if (energy < 0) {
            energy = 0;
        }
    }


    /**
     * Clorus lose 0.01 energy when staying.
     */
    public void stay() {
        energy -= 0.01;
    }

    /**
     * Clorus and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby clorus.
     */
    public Clorus replicate() {
        energy = 0.5 * energy;
        return new Clorus(energy);
    }

    /**
     * Clorus take exactly the following actions based on NEIGHBORS:
     * 1. If there are no empty squares, the Clorus will STAY
     * 2. Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     * 3. Otherwise, if the Clorus has energy >= 1, it will REPLICATE to a random empty square.
     * 4. Otherwise, the Clorus will MOVE to a random empty square.
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {

        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipD = new ArrayDeque<>();
        boolean anyPlip = false;

        for (Direction d : neighbors.keySet()) {
            if (neighbors.get(d).name().equals("empty")) {
                emptyNeighbors.addLast(d);
            }
            if (neighbors.get(d).name().equals("plip")) {
                plipD.addLast(d);
                anyPlip = true;
            }
        }

        // Rule 1
        if (emptyNeighbors.size() == 0) {
            stay();
            return new Action(Action.ActionType.STAY);
        }
        // Rule 2
        if (anyPlip) {
            Direction d = randomEntry(plipD);
            attack((Creature) neighbors.get(d));
            return new Action(Action.ActionType.ATTACK, d);
        }

        // Rule 3
        if (energy >= 1) {
            replicate();
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        }

        // Rule 4
        move();
        return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
    }
}
