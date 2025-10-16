package fx.zombiesfx.game;

public class GameGrid {
    public static final int CELL_WIDTH = 200;
    public static final int CELL_HEIGHT = 200;
    public static final int ROWS = 4;
    public static final int COLS = 8;

    public static double getCellX(int col) {
        return col * CELL_WIDTH;
    }

    public static double getCellY(int row) {
        return row * CELL_HEIGHT + 50;
    }

    public static int getRowFromY(double y) {
        if (y < 50) return 0;
        if (y > CELL_HEIGHT * ROWS + 50) return ROWS;
        return (int) (y / CELL_HEIGHT);
    }

    public static int getColFromX(double x) {
        return (int) (x / CELL_WIDTH);
    }
}
