package fx.zombiesfx.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class NormalZombie extends Zombie {
    public NormalZombie(double x, double y) {
        super(x, y, 100, 25, 15); // health, speed, damage
    }

    @Override
    public void update(double delta) {
        super.update(delta);
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
        gc.setFill(Color.MEDIUMVIOLETRED);
        gc.fillRect(x, y, width, height);
        renderHealth(gc);
    }
}

