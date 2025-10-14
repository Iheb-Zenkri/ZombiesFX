package fx.zombiesfx.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WallNut extends Plant {
    public WallNut(double x, double y) {
        super(x, y, 300);
        this.damage = 0;
        this.cooldown = 999; // no attack
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.SADDLEBROWN);
        gc.fillRect(x, y, 40, 40);
        renderHealth(gc);
    }
}
