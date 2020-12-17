import gameoflife.Cell;
import gameoflife.GolBoard;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GameOfLifeTest {

    @Test
    public void createEmptyBoard(){
        GolBoard golBoard = new GolBoard();
        assertTrue(golBoard.isAllDead());
    }

    @Test
    public void createSizeOfTheBoard(){
        GolBoard golBoard = new GolBoard(3, 3);
        assertEquals(3,golBoard.boardWidth());
        assertEquals(3, golBoard.boardHeight());
    }

    @Test
    public void putOneCellAliveOnBoard(){
        GolBoard golBoard = new GolBoard(3, 3);
        golBoard.cellAlive(Cell.of(1,1));
        assertFalse(golBoard.isAllDead());
        //nedan kan köras i en egen separat test
        assertTrue(golBoard.isCellAlive(1,1));
    }

    @Test
    public void putOneCellAliveAndRestShouldBeDead(){
        GolBoard golBoard = new GolBoard(3, 3);
        golBoard.cellAlive(Cell.of(1,1));
        assertFalse(golBoard.isCellAlive(0,0));
        assertFalse(golBoard.isCellAlive(0,1));
        assertFalse(golBoard.isCellAlive(1,2));
    }

    @Test
    public void simSecondGenerationWithEmptyBoard(){
        GolBoard golBoard = new GolBoard(3, 3);
        GolBoard nextBoard = golBoard.createNextGeneration();
        assertTrue(nextBoard.isAllDead());
        assertEquals(3, nextBoard.boardWidth());
        assertEquals(3, nextBoard.boardHeight());
    }

    @Test
    public void liveCellWithFewerThanTwoLiveNeighborsShouldDie(){
        GolBoard golBoard = new GolBoard(3, 3);
        golBoard.cellAlive(Cell.of(1,1));
        golBoard.cellAlive(Cell.of(2,1));
        var nextBoard = golBoard.createNextGeneration();
        assertTrue(nextBoard.isAllDead());
    }

    @Test
    public void liveCellWithTwoNeighboursShouldLive(){
        GolBoard golBoard = new GolBoard(3, 3);
        golBoard.cellAlive(Cell.of(1,1));
        golBoard.cellAlive(Cell.of(2,1));
        golBoard.cellAlive(Cell.of(0,1));
        var nextBoard = golBoard.createNextGeneration();
        assertTrue(nextBoard.isCellAlive(1,1));
    }

    @Test
    public void liveCellWithThreeNeighboursShouldLive(){
        GolBoard golBoard = new GolBoard(3, 3);
        golBoard.cellAlive(Cell.of(1,1));
        golBoard.cellAlive(Cell.of(2,1));
        golBoard.cellAlive(Cell.of(0,1));
        golBoard.cellAlive(Cell.of(1,0));
        var nextBoard = golBoard.createNextGeneration();
        assertTrue(nextBoard.isCellAlive(1,1));
    }

    @Test
    public void liveCellWithFourNeighboursShouldDie(){
        GolBoard golBoard = new GolBoard(3, 3);
        golBoard.cellAlive(Cell.of(1,1));
        golBoard.cellAlive(Cell.of(2,1));
        golBoard.cellAlive(Cell.of(0,1));
        golBoard.cellAlive(Cell.of(1,0));
        golBoard.cellAlive(Cell.of(1,2));
        var nextBoard = golBoard.createNextGeneration();
        assertFalse(nextBoard.isCellAlive(1,1));
    }

    @Test
    public void notAliveCellWithExactlyThreeNeighboursShouldBecomeAlive(){
        GolBoard golBoard = new GolBoard(5, 5);
        golBoard.cellAlive(Cell.of(3,1));
        golBoard.cellAlive(Cell.of(2,2));
        golBoard.cellAlive(Cell.of(4,3));
        var nextBoard = golBoard.createNextGeneration();
        assertTrue(nextBoard.isCellAlive(3,2));
    }

    @Test
    public void countNeighboursAlive(){
        GolBoard golBoard = new GolBoard(3, 3);
        golBoard.cellAlive(Cell.of(1,1));
        assertEquals(0, golBoard.countNeighboursAlive(Cell.of(1,1)));
    }

    @Test
    public void countUpperLeftNeighbourAliveShouldReturnOne(){
        GolBoard golBoard = new GolBoard(3, 3);
        golBoard.cellAlive(Cell.of(1,1)); //det behövs inte att sättas ALIVE, man räknar bara antal grannar till denna cell
        golBoard.cellAlive(Cell.of(0,0));
        assertEquals(1, golBoard.countNeighboursAlive(Cell.of(1,1)));
    }

    @Test
    public void countNeighboursAliveShouldReturnThree(){
        GolBoard golBoard = new GolBoard(5, 5);
        golBoard.cellAlive(Cell.of(3,2)); //det behövs inte att sättas ALIVE, man räknar bara antal grannar till denna cell
        golBoard.cellAlive(Cell.of(3,1));
        golBoard.cellAlive(Cell.of(2,2));
        golBoard.cellAlive(Cell.of(4,3));
        assertEquals(3, golBoard.countNeighboursAlive(Cell.of(3,2)));
    }

    @Test
    public void countNeighboursAliveShouldReturnFour(){
        GolBoard golBoard = new GolBoard(3, 3);
        golBoard.cellAlive(Cell.of(1, 1));
        golBoard.cellAlive(Cell.of(2, 1));
        golBoard.cellAlive(Cell.of(0, 1));
        golBoard.cellAlive(Cell.of(1,0));
        golBoard.cellAlive(Cell.of(1,2));
        assertEquals(4, golBoard.countNeighboursAlive(Cell.of(1,1)));
    }

    @Test
    public void setOfAllNeighbours(){
        var cell = Cell.of(1,1);
        Set<Cell> neighbours = cell.neighbours();
        Set<Cell> expected = Set.of(
                Cell.of(0,0),Cell.of(1,0),Cell.of(2,0),
                Cell.of(0,1),Cell.of(2,1),
                Cell.of(0,2),Cell.of(1,2),Cell.of(2,2));
        assertEquals(expected,neighbours);
    }
}
