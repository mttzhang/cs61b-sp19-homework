package creatures;

import huglife.Action;
import huglife.Direction;
import huglife.Empty;
import huglife.Impassible;
import huglife.Occupant;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestClorus {
    @Test
    public void testAttack() {
        Plip p = new Plip(1);
        Clorus c = new Clorus(2);
        c.attack(p);
        double delta = 0.001;
        assertEquals(3, c.energy(), delta);
    }

    @Test
    public void testReplicate() {
        double delta = 0.001;
        Clorus c = new Clorus(2);
        Clorus pJunior = c.replicate();
        assertNotEquals(c, pJunior);
        assertEquals(1, c.energy(), delta);
        assertEquals(1, pJunior.energy(), delta);
    }

    @Test
    public void testChooseAction() {
        // No empty adjacent spaces; stay.
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        //If any Plips are seen, the Clorus will ATTACK one of them randomly.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> topPlip = new HashMap<Direction, Occupant>();
        topPlip.put(Direction.TOP, new Plip());
        topPlip.put(Direction.BOTTOM, new Empty());
        topPlip.put(Direction.LEFT, new Impassible());
        topPlip.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topPlip);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);

        assertEquals(expected, actual);


        //Energy >= 1, REPLICATE to a random empty square
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topEmpty);
        Action unexpected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(unexpected, actual);


        // Energy < 1; move.
        c = new Clorus(.99);

        actual = c.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.MOVE,  Direction.TOP);

        assertEquals(expected, actual);
    }
}
