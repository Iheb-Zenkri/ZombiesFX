package fx.zombiesfx.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Zombie extends Entity {
    private final double speed;
    private int health = 50;

    public Zombie(double x, double y, double speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    @Override
    public void update(double delta) {
        x -= speed * delta;
        if (x < 0) alive = false;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.DARKRED);
        gc.fillRect(x, y, 30, 30);
        gc.setFill(Color.WHITE);
        gc.fillText(String.valueOf(health), x + 5, y - 5);
    }

    public void damage(int amount) {
        health -= amount;
        if (health <= 0) alive = false;
    }
}
