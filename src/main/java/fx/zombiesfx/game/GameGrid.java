package fx.zombiesfx.game;

import fx.zombiesfx.assets.Assets;
import javafx.scene.image.Image;

public class GameGrid {
    public static final Image grassLightImage = Assets.get("/fx/zombiesfx/assets/grass-light.png");
    public static final Image grassDarkImage = Assets.get("/fx/zombiesfx/assets/grass-dark.png");

    public static final Image streetImage = Assets.get("/fx/zombiesfx/assets/street.png");

    public static final int gridSpacingX = 200;
    public static final int gridSpacingY = 50;
    public static final int CELL_WIDTH = 200;
    public static final int CELL_HEIGHT = 200;
    public static final int ROWS = 4;
    public static final int COLS = 7;

    public static double getCellX(int col) {
        return col * CELL_WIDTH + gridSpacingX;
    }

    public static double getCellY(int row) {
        return row * CELL_HEIGHT + gridSpacingY;
    }

    public static int getRowFromY(double y) {
        if (y < gridSpacingY) return -1;
        if (y > CELL_HEIGHT * ROWS + gridSpacingY) return -1;
        return (int) ((y - gridSpacingY) / CELL_HEIGHT);
    }

    public static int getColFromX(double x) {
        if (x < gridSpacingX) return -1;
        if (x > CELL_WIDTH * COLS + gridSpacingX) return -1;
        return (int) ((x - gridSpacingX) / CELL_WIDTH);
    }
}
