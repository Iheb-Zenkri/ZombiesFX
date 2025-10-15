package fx.zombiesfx.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TankZombie extends Zombie {
    public TankZombie(double x, double y) {
        super(x, y, 200, 15, 20);
    }

    @Override
    public void update(double delta) {
        super.update(delta);
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
        gc.setFill(Color.FIREBRICK);
        gc.fillRect(x, y, width, height);
        renderHealth(gc);
        gc.restore();
    }
}