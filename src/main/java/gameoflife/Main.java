package gameoflife;

public class Main {

    public static void main(String[] args) {
        GolBoard board = new GolBoard(10, 10);
        board.cellAlive(Cell.of(3, 1));
        board.cellAlive(Cell.of(2, 2));
        board.cellAlive(Cell.of(4, 3));
        board.cellAlive(Cell.of(7, 6));
        board.cellAlive(Cell.of(8, 6));
        board.cellAlive(Cell.of(9, 6));
        for (int simulationSteps = 0; simulationSteps < 10; simulationSteps++) {
            for (int i = 0; i < board.boardHeight(); i++) {
                for (int j = 0; j < board.boardWidth(); j++) {
                    System.out.print(board.isCellAlive(j, i) ? 'X' : 'O');
                    System.out.print(" ");
                }
                System.out.println();
            }
            System.out.println("----------");
            board = board.createNextGeneration();
        }
    }
}
