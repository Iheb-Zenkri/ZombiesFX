package fx.zombiesfx.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Plant extends Entity {
    protected double cooldown = 1.5;
    protected double timeSinceLastAction = 0;

    public Plant(double x, double y, double width, double height, double health) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
        gc.fillRect(x, y - 10, width, 5);
        gc.setFill(Color.GREEN);
        gc.fillRect(x, y - 10, width * (health / maxHealth), 5);
    }
}
