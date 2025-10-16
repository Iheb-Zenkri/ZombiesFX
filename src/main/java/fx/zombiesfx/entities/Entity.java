package fx.zombiesfx.entities;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
    protected double x, y, width, height;
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
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public double getDamage() {
        return this.damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

}
