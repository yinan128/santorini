package edu.cmu.cs214.hw3.playground;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FieldTest {

    private Field field;

    @Before
    public void setup() {
        field = new Field();
    }

    @Test
    public void testMoveStartOneLvlLower() {
        Field dest = new Field();
        dest.build();
        assertTrue(Field.isMovable(field, dest));
    }

    @Test
    public void testMoveStartTwoLvlLower() {
        Field dest = new Field();
        dest.build();
        dest.build();
        assertFalse(Field.isMovable(field, dest));
    }

    @Test
    public void testMoveDestOccupied() {
        Field dest = new Field();
        dest.occupy();
        assertFalse(Field.isMovable(field, dest));
    }

    @Test
    public void testMoveStartOneLvlHigher() {
        Field dest = new Field();
        field.build();
        assertTrue(Field.isMovable(field, dest));
    }

    @Test
    public void testMoveStartTwoLvlHigher() {
        Field dest = new Field();
        field.build();
        field.build();
        assertTrue(Field.isMovable(field, dest));
    }

    @Test
    public void testMoveStartTwoLvlHigherDestOccupied() {
        Field dest = new Field();
        dest.occupy();
        field.build();
        field.build();
        assertFalse(Field.isMovable(field, dest));
    }

    @Test
    public void testBuildOneFloor() {
        assertTrue(field.build());
    }

    @Test
    public void testBuildTwoFloors() {
        for (int i = 0; i < 2; i++) {
            assertTrue(field.build());
        }
    }

    @Test
    public void testBuildThreeFloors() {
        for (int i = 0; i < 3; i++) {
            assertTrue(field.build());
        }
    }

    @Test
    public void testBuildFiveFloors() {
        for (int i = 0; i < 4; i++) {
            assertTrue(field.build());
        }
        assertFalse(field.build());
    }

    @Test
    public void testBuildWhenOccupied() {
        field.build();
        field.occupy();
        assertFalse(field.build());
    }


}