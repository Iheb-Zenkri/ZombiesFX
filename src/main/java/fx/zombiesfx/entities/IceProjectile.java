package fx.zombiesfx.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class IceProjectile extends Projectile {
    public IceProjectile(double x, double y, double damage) {
        super(x, y, damage);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.DEEPSKYBLUE);
        gc.fillOval(x, y, 8, 8);
    }
}
