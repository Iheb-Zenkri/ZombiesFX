package fx.zombiesfx.entities;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
    protected double x, y;
    protected double health, maxHealth;
    protected double damage;
    protected boolean alive = true;

    public abstract void update(double delta);
    public abstract void render(GraphicsContext gc);

    public void takeDamage(double dmg) {
        health -= dmg;
        if (health <= 0) {
            health = 0;
            alive = false;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getHealth() {
        return health;
    }

    public double getDamage() {
        return this.damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}
