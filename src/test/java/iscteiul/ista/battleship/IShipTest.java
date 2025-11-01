package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da interface IShip através da implementação Barge")
class IShipTest {

    private IShip ship;

    @BeforeEach
    @DisplayName("Preparar navio para os testes da interface IShip")
    void setUp() {
        ship = new Barge(Compass.EAST, new Position(5, 5));
    }

    @Test
    @DisplayName("getSize deve devolver o tamanho correto do navio")
    void getSize() {
        assertEquals(1, ship.getSize()); // Exemplo para Barge
    }

    @Test
    @DisplayName("stillFloating deve retornar falso se todas as posições foram atingidas")
    void stillFloating() {
        IPosition pos = ship.getPositions().get(0);
        ship.shoot(pos);
        assertFalse(ship.stillFloating());
    }

    @Test
    @DisplayName("getTopMostPos e getBottomMostPos devem devolver valores corretos")
    void testPositionsExtremes() {
        int top = ship.getTopMostPos();
        int bottom = ship.getBottomMostPos();
        assertEquals(top, bottom);
    }

    @Test
    @DisplayName("tooCloseTo posição deve funcionar corretamente")
    void testTooCloseTo() {
        IPosition near = new Position(5, 6);
        assertTrue(ship.tooCloseTo(near));
    }


    @Test
    @DisplayName("getSize deve ser consistente com posições")
    void testSizeMatchesPositions() {
        assertEquals(ship.getSize(), ship.getPositions().size());
    }


    @Test
    @DisplayName("stillFloating reflete hits corretamente")
    void testFloatingConsistency() {
        for (IPosition p : ship.getPositions()) {
            ship.shoot(p);
        }
        assertFalse(ship.stillFloating());
    }


    @Test
    @DisplayName("shoot duas vezes na mesma posição")
    void testShootIdempotent() {
        IPosition pos = ship.getPositions().get(0);
        ship.shoot(pos);
        ship.shoot(pos);
        assertTrue(pos.isHit());
    }

    @ParameterizedTest
    @EnumSource(Compass.class)
    void testBargeAllDirections(Compass dir) {
        IShip b = new Barge(dir, new Position(3,3));
        assertEquals(dir, b.getBearing());
        assertEquals(1, b.getSize());
    }
}