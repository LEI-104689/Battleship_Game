package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da classe Ship")
class ShipTest {

    private Ship ship;  // Uma instância concreta de Ship (ex: Barge)
    private Position startPos;

    @BeforeEach
    @DisplayName("Configuração inicial de um navio para os testes")
    void setUp() {
        startPos = new Position(3, 3);
        ship = new Barge(Compass.NORTH, startPos); // Supondo que Barge extende Ship
    }

    @Test
    @DisplayName("buildShip deve criar o tipo correto de navio")
    void buildShip() {
        Ship galeao = Ship.buildShip("galeao", Compass.EAST, new Position(1, 1));
        assertNotNull(galeao);
        assertEquals("galeao", galeao.getCategory());
    }

    @Test
    @DisplayName("getCategory deve devolver o tipo correto")
    void getCategory() {
        assertEquals("barca", ship.getCategory());
    }

    @Test
    @DisplayName("getPositions deve conter todas as posições do navio")
    void getPositions() {
        List<IPosition> positions = ship.getPositions();
        assertNotNull(positions);
        assertEquals(ship.getSize(), positions.size());
    }

    @Test
    @DisplayName("getPosition deve devolver a posição inicial")
    void getPosition() {
        assertEquals(startPos, ship.getPosition());
    }

    @Test
    @DisplayName("getBearing deve devolver a direção correta")
    void getBearing() {
        assertEquals(Compass.NORTH, ship.getBearing());
    }

    @Test
    @DisplayName("stillFloating deve ser verdadeiro antes de ser afundado")
    void stillFloating() {
        assertTrue(ship.stillFloating());
    }

    @Test
    @DisplayName("shoot deve marcar a posição como atingida")
    void shoot() {
        IPosition pos = ship.getPositions().get(0);
        ship.shoot(pos);
        assertTrue(pos.isHit());
    }

    @Test
    @DisplayName("occupies deve retornar verdadeiro se posição pertence ao navio")
    void occupies() {
        IPosition pos = ship.getPositions().get(0);
        assertTrue(ship.occupies(pos));
        assertFalse(ship.occupies(new Position(9, 9)));
    }

    @Test
    @DisplayName("tooCloseTo deve retornar verdadeiro se navios estiverem adjacentes")
    void tooCloseTo() {
        Ship other = new Barge(Compass.NORTH, new Position(3, 4));
        assertTrue(ship.tooCloseTo(other));
    }

    @Test
    @DisplayName("toString deve devolver uma string formatada corretamente")
    void testToString() {
        String result = ship.toString();
        assertTrue(result.contains("barca"));
        assertTrue(result.contains("NORTH"));
    }
}