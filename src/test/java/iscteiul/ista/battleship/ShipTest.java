package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da classe Ship")
class ShipTest {

    private Ship ship;  // Uma instância concreta de Ship (ex: Barge)
    private Position startPos;


    @BeforeAll
    static void setupAll() {
        System.out.println("Antes de todos os testes");
        // Pode inicializar recursos globais, como um tabuleiro compartilhado
    }

    // ------------------ EXECUTA ANTES DE CADA TESTE ------------------
    @BeforeEach
    void setupEach() {
        startPos = new Position(3, 3);
        ship = new Barge(Compass.NORTH, startPos);
        System.out.println("Antes de cada teste: nova instância de Ship");
    }


    // ------------------ EXECUTA DEPOIS DE CADA TESTE ------------------
    @AfterEach
    void teardownEach() {
        System.out.println("Depois de cada teste");
        // Limpar recursos específicos do teste, se necessário
    }

    // ------------------ EXECUTA DEPOIS DE TODOS OS TESTES ------------------
    @AfterAll
    static void teardownAll() {
        System.out.println("Depois de todos os testes");
        // Fechar recursos globais, se houver
    }




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
        assertEquals("galeao", galeao.getCategory().toLowerCase());
    }

    @Test
    @DisplayName("getCategory deve devolver o tipo correto")
    void getCategory() {
        assertEquals("barca", ship.getCategory().toLowerCase());
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


    // ------------------ NESTED TESTS ------------------
    @Nested
    @DisplayName("Testes de comportamento de tiro e flutuação")
    class ShootingTests {

        @Test
        @DisplayName("Navio deve flutuar antes de ser atingido")
        void stillFloatingBeforeHit() {
            assertTrue(ship.stillFloating());
        }

        @Test
        @DisplayName("Navio não deve flutuar após ser atingido")
        void stillFloatingAfterHit() {
            ship.shoot(ship.getPositions().get(0));
            assertFalse(ship.stillFloating());
        }
    }


    @Nested
    @DisplayName("Testes de posição e adjacência")
    class PositionTests {

        @Test
        @DisplayName("tooCloseTo deve detectar navio adjacente")
        void testTooCloseTo() {
            Ship other = new Barge(Compass.EAST, new Position(3, 4));
            assertTrue(ship.tooCloseTo(other));
        }

        @Test
        @DisplayName("occupies deve detectar posição correta")
        void testOccupies() {
            IPosition pos = ship.getPositions().get(0);
            assertTrue(ship.occupies(pos));
            assertFalse(ship.occupies(new Position(9, 9)));
        }
    }




    @Test
    @DisplayName("toString deve devolver uma string formatada corretamente")
    void testToString() {
        String result = ship.toString().toLowerCase();
        System.out.println("DEBUG Ship.toString() = " + result);

        assertAll(
                () -> assertTrue(result.contains("barca"), "Deve conter o nome do navio"),
                () -> assertTrue(result.contains("n") || result.contains("s")
                                || result.contains("e") || result.contains("o"),
                        "Deve conter a direção abreviada (n/s/e/o)"),
                () -> assertTrue(result.contains("linha") && result.contains("coluna"),
                        "Deve conter as palavras 'linha' e 'coluna' indicando a posição")
        );
    }

    @Test
    @DisplayName("getTopMostPos e getBottomMostPos funcionam nos limites")
    void testTopBottomEdges() {
        Ship s = new Barge(Compass.NORTH, new Position(0, 0));
        assertEquals(0, s.getTopMostPos());
        assertEquals(0, s.getBottomMostPos());
    }

    @Test
    @DisplayName("getLeftMostPos e getRightMostPos com múltiplas posições")
    void testLeftRightMultiPos() {
        Ship caravel = new Caravel(Compass.EAST, new Position(1,1));
        assertEquals(1, caravel.getLeftMostPos());
        assertEquals(2, caravel.getRightMostPos());
    }

    @Test
    @DisplayName("stillFloating deve refletir hits parciais e totais")
    void testStillFloatingMultiHit() {
        Ship caravel = new Caravel(Compass.SOUTH, new Position(2,2));
        // hit first cell
        caravel.shoot(caravel.getPositions().get(0));
        assertTrue(caravel.stillFloating());
        // hit second cell
        caravel.shoot(caravel.getPositions().get(1));
        assertFalse(caravel.stillFloating());
    }


    @Test
    @DisplayName("tooCloseTo detecta todas posições adjacentes")
    void testTooCloseToAllDirections() {
        Ship s = new Barge(Compass.NORTH, new Position(5,5));
        List<Position> neighbors = List.of(
                new Position(4,4), new Position(4,5), new Position(4,6),
                new Position(5,4), /*self*/ new Position(5,6),
                new Position(6,4), new Position(6,5), new Position(6,6)
        );
        for (Position p : neighbors) {
            if (!p.equals(s.getPosition()))
                assertTrue(s.tooCloseTo(p));
        }
    }


    @Test
    void testToStringFormat() {
        String str = ship.toString().toLowerCase();
        assertAll(
                () -> assertTrue(str.contains("barca")),
                () -> assertTrue(str.contains("n")), // direction
                () -> assertTrue(str.contains("linha") && str.contains("coluna"),
                        "Deve conter 'linha' e 'coluna' na posição")
        );
    }



    @Test
    @DisplayName("getTopMostPos e getBottomMostPos devem funcionar corretamente")
    void testVerticalBoundaries() {
        Ship caravel = new Caravel(Compass.SOUTH, new Position(1, 1));
        assertEquals(1, caravel.getTopMostPos());
        assertEquals(2, caravel.getBottomMostPos());
    }

    @Test
    @DisplayName("buildShip deve devolver null para tipo inválido")
    void testInvalidShipType() {
        Ship unknown = Ship.buildShip("submarino", Compass.EAST, new Position(2, 2));
        assertNull(unknown);
    }

    @Test
    @DisplayName("shoot fora do navio não deve marcar hit")
    void testShootOutside() {
        IPosition target = new Position(9, 9);
        ship.shoot(target);
        assertFalse(ship.getPositions().get(0).isHit());
    }

    @Test
    @DisplayName("tooCloseTo deve retornar falso para posições distantes")
    void testTooCloseToFarPosition() {
        assertFalse(ship.tooCloseTo(new Position(10, 10)));
    }

    @Test
    @DisplayName("Construir navio com parâmetros nulos deve falhar (assert)")
    void testConstructorWithNulls() {
        assertThrows(AssertionError.class, () -> new Barge(null, null));
    }


    @Test
    @DisplayName("Deve construir um navio corretamente")
    void testShipCreation() {
        assertNotNull(ship);
        assertEquals(3, ship.getPositions().get(0).getRow());
    }

    @AfterEach
    void tearDown() {
        System.out.println("🧹 Teste concluído.");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("✅ Todos os testes de Ship finalizados!");
    }

    @Nested
    @DisplayName("Testes de comportamento do método shoot()")
    class ShootTests {

        @Test
        @DisplayName("Deve marcar posição como atingida")
        void testShootHit() {
            IPosition target = ship.getPositions().get(0);
            ship.shoot(target);
            assertTrue(target.isHit());
        }

        @Test
        @DisplayName("Não deve marcar hit se posição for fora do navio")
        void testShootMiss() {
            Position target = new Position(10, 10);
            ship.shoot(target);
            assertFalse(ship.getPositions().get(0).isHit());
        }
    }


}
