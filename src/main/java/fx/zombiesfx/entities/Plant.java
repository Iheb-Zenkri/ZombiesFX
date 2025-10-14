package fx.zombiesfx.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Plant extends Entity {
    protected double cooldown = 1.5;
    protected double timeSinceLastAction = 0;

    public Plant(double x, double y, double health) {
        this.x = x;
        this.y = y;
        this.health = this.maxHealth = health;
    }

    @Override
    public void update(double delta) {
        timeSinceLastAction += delta;
    }

    public boolean canAct() {
        return timeSinceLastAction >= cooldown;
    }

    public void resetCooldown() {
        timeSinceLastAction = 0;
    }

    public void renderHealth(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillRect(x, y - 10, 30, 5);
        gc.setFill(Color.LIME);
        gc.fillRect(x, y - 10, 30 * (health / maxHealth), 5);
    }
}
