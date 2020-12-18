package gameoflife;

import java.util.HashSet;
import java.util.Set;

public class GolBoard {

    private int width;
    private int height;
    private Set<Cell> aliveCells = new HashSet<>();

    public GolBoard() {
    }

    public GolBoard(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public GolBoard createNextGeneration() {
        var nextBoard = new GolBoard(boardWidth(), boardHeight());
        spawnNewCells(nextBoard);
        survivingCells(nextBoard);
        return nextBoard;
    }

    private void spawnNewCells(GolBoard nextBoard) {
        for (int i = 0; i < boardHeight(); i++)
            for (int j = 0; j < boardWidth(); j++) {
                if (countNeighboursAlive(Cell.of(j, i)) == 3)
                    nextBoard.cellAlive(Cell.of(j, i));
            }
    }

    private void survivingCells(GolBoard nextBoard) {
        for (Cell cell : aliveCells) {
            var count = countNeighboursAlive(cell);
            if (count == 2 || count == 3)
                nextBoard.cellAlive(cell);
        }
    }

    public int countNeighboursAlive(Cell cell) {
        Set<Cell> neighbours = cell.neighbours();
        return (int) neighbours.stream()
                .filter(neighbourCell -> aliveCells.contains(neighbourCell))
                .count();
    }

    public boolean isAllDead() {
        return aliveCells.isEmpty();
    }

    public int boardWidth() {
        return width;
    }

    public int boardHeight() {
        return height;
    }

    public void cellAlive(Cell cell) {
        aliveCells.add(cell);
    }

    public boolean isCellAlive(int col, int row) {
        var compareCell = new Cell(col, row);

        var oCell = aliveCells.stream()
                .filter(cell -> cell.equals(compareCell))
                .findAny();

        return oCell.isPresent();
    }
}
