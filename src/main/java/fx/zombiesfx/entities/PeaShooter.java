package fx.zombiesfx.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PeaShooter extends Plant {
    public PeaShooter(double x, double y) {
        super(x, y, 100);
        this.damage = 20;
        this.cooldown = 1.2;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillOval(x, y, 40, 40);
        renderHealth(gc);
    }

    public Projectile shoot() {
        if (canAct()) {
            resetCooldown();
            return new Projectile(x + 40, y + 15, damage);
        }
        return null;
    }
}
