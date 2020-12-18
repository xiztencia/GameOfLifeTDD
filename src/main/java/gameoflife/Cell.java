package gameoflife;

import java.util.HashMap;
import java.util.Set;
import java.util.Map;

public class Cell {
    private final int row;
    private final int col;

    public Cell(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }

   // private static Map<String, Cell> cache = new HashMap<>();

    public static Cell of(int col, int row) {
//        String key = "" + col + "," + row;
////        if (cache.containsKey(key))
////            return cache.get(key);
////        else {
////            var cell = Cell.of(col, row);
////            cache.put(key, cell);
////            return cell;
////        }
//        return cache.computeIfAbsent(key, newKey -> Cell.of(col, row));
        return new Cell(col,row);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (row != cell.row) return false;
        return col == cell.col;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }

    @Override
    public String toString() {
        return "Cell{" + col + "," + row + '}';
    }

    public Set<Cell> neighbours() {
        return Set.of(
                Cell.of(col - 1, row - 1), Cell.of(col, row - 1), Cell.of(col + 1, row - 1),
                Cell.of(col - 1, row), Cell.of(col + 1, row),
                Cell.of(col - 1, row + 1), Cell.of(col, row + 1), Cell.of(col + 1, row + 1));
    }
}
