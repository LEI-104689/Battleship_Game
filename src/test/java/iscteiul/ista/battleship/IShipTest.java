package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}