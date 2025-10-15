package fx.zombiesfx.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SnowPeat extends Plant {
    public SnowPeat(double x, double y) {
        super(x, y, 30, 30, 80);
        this.damage = 12;
        this.cooldown = 2.0;
    }
    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.DEEPSKYBLUE);
        gc.fillOval(x, y, 40, 40);
        renderHealth(gc);
    }

    public IceProjectile shoot() {
        if (canAct()) {
            resetCooldown();
            return new IceProjectile(x + 40, y + 15, damage);
        }
        return null;
    }
}
