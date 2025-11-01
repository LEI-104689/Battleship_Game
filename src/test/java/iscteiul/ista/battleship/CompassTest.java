package iscteiul.ista.battleship;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompassTest {

    @Test
    void getDirection() {
    }

    @Test
    void testToString() {
    }

    @Test
    void charToCompass() {
    }

    @Test
    void values() {
    }

    @Test
    void valueOf() {
    }


    @Test
    void testCharToCompass() {
        assertEquals(Compass.NORTH, Compass.charToCompass('n'));
        assertEquals(Compass.SOUTH, Compass.charToCompass('s'));
        assertEquals(Compass.EAST, Compass.charToCompass('e'));
        assertEquals(Compass.WEST, Compass.charToCompass('o'));
        assertEquals(Compass.UNKNOWN, Compass.charToCompass('x'));
    }

    @Test
    void testToStringReturnsChar() {
        assertEquals("n", Compass.NORTH.toString());
        assertEquals("s", Compass.SOUTH.toString());
    }

    @Test
    void testGetDirection() {
        assertEquals('n', Compass.NORTH.getDirection());
    }


    @Nested
    @DisplayName("Testes de conversão de char para Compass")
    class CharConversionTests {

        @Test
        void testValidDirections() {
            assertEquals(Compass.NORTH, Compass.charToCompass('n'));
            assertEquals(Compass.SOUTH, Compass.charToCompass('s'));
        }

        @Test
        void testInvalidDirection() {
            assertEquals(Compass.UNKNOWN, Compass.charToCompass('x'));
        }
    }

    @Disabled("Verificar comportamento de toString() mais tarde")
    @Test
    void testToStringTemporarilyOff() {
        assertEquals("n", Compass.NORTH.toString());
    }

}