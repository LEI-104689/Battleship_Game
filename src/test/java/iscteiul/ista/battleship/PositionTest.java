package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    private Position pos;


    @BeforeAll
    static void initAll() {
        System.out.println("🧭 Iniciando PositionTest...");
    }

    @BeforeEach
    void init() {
        pos = new Position(2, 3);
    }


    @Test
    void getRow() {
    }

    @Test
    void getColumn() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void isAdjacentTo() {
    }

    @Test
    void occupy() {
    }

    @Test
    void shoot() {
    }

    @Test
    void isOccupied() {
    }

    @Test
    void isHit() {
    }

    @Test
    void testToString() {
    }


    @Test
    void testEqualsAndHashCode() {
        Position p1 = new Position(3, 4);
        Position p2 = new Position(3, 4);
        Position p3 = new Position(4, 4);
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
        assertNotEquals(p1, p3);
        assertNotEquals(p1, null);
        assertNotEquals(p1, "String");
    }

    @Test
    void testAdjacency() {
        Position p = new Position(5, 5);
        assertTrue(p.isAdjacentTo(new Position(5, 6)));
        assertTrue(p.isAdjacentTo(new Position(6, 6)));
        assertFalse(p.isAdjacentTo(new Position(7, 7)));
    }

    @Test
    void testOccupyAndShoot() {
        Position p = new Position(2, 2);
        assertFalse(p.isOccupied());
        p.occupy();
        assertTrue(p.isOccupied());
        assertFalse(p.isHit());
        p.shoot();
        assertTrue(p.isHit());
    }

    @Test
    void testToStringFormat() {
        Position p = new Position(1, 2);
        String text = p.toString().toLowerCase();
        assertTrue(text.contains("linha"));
        assertTrue(text.contains("coluna"));
    }


    @AfterEach
    void clean() {
        System.out.println("✔️ Teste Position concluído");
    }

    @AfterAll
    static void cleanAll() {
        System.out.println("🏁 Todos os testes Position finalizados!");
    }

}