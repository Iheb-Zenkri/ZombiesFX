package fx.zombiesfx.game;

public class GameGrid {
    public static final int CELL_WIDTH = 120;
    public static final int CELL_HEIGHT = 120;
    public static final int ROWS = 5;
    public static final int COLS = 9;

    public static double getCellX(int col) {
        return col * CELL_WIDTH + 60;
    }

    public static double getCellY(int row) {
        return row * CELL_HEIGHT + 60;
    }

    public static int getRowFromY(double y) {
        return (int) (y / CELL_HEIGHT);
    }

    public static int getColFromX(double x) {
        return (int) (x / CELL_WIDTH);
    }
}
