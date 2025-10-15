package fx.zombiesfx.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Projectile extends Entity {
    private final double speed = 200;

    public Projectile(double x, double y, double damage) {
        this.x = x;
        this.y = y;
        this.width = 8;
        this.height = 8;
        this.damage = damage;
    }

    @Override
    public void update(double delta) {
        x += speed * delta;
        if (x > 600) alive = false;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.LIME);
        gc.fillOval(x, y, width, height);
    }
}
