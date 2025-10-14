package fx.zombiesfx.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Zombie extends Entity {
    protected double speed;
    protected double damage;
    protected boolean attacking = false;

    public Zombie(double x, double y, double health, double speed, double damage) {
        this.x = x;
        this.y = y;
        this.health = this.maxHealth = health;
        this.speed = speed;
        this.damage = damage;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.DARKRED);
        gc.fillRect(x, y, 40, 40);
        gc.setFill(Color.WHITE);
        gc.fillText(String.valueOf((int) health), x, y - 5);
    }

    public void attack(Plant p, double delta) {
        attacking = true;
        p.takeDamage(damage * delta);
    }

    public void move(double delta) {
        x -= speed * delta;
    }
}

