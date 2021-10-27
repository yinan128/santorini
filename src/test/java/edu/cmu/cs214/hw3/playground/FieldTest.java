package edu.cmu.cs214.hw3.playground;

import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.util.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FieldTest {

    private Field field;

    @Before
    public void setup() {
        field = new Field(Location.get(0, 0));
    }

    @Test
    public void testMoveStartOneLvlLower() {
        Field dest = new Field(Location.get(1, 1));
        dest.build();
        assertTrue(Field.isMovable(field, dest));
    }

    @Test
    public void testMoveStartTwoLvlLower() {
        Field dest = new Field(Location.get(1, 1));
        dest.build();
        dest.build();
        assertFalse(Field.isMovable(field, dest));
    }

    @Test
    public void testMoveStartOneLvlHigher() {
        Field dest = new Field(Location.get(1, 1));
        field.build();
        assertTrue(Field.isMovable(field, dest));
    }

    @Test
    public void testMoveStartTwoLvlHigher() {
        Field dest = new Field(Location.get(1, 1));
        field.build();
        field.build();
        assertTrue(Field.isMovable(field, dest));
    }


    @Test
    public void testAddworker() {
        field.setWorker(new Worker(Location.get(1, 1), new Player(0)));
        assertTrue(field.isOccupied());
    }

    @Test
    public void testRemoveWorker() {
        field.setWorker(new Worker(Location.get(1, 1), new Player(0)));
        field.removeWorker();
        assertFalse(field.isOccupied());
    }


}