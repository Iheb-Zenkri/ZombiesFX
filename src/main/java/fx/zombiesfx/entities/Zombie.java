package fx.zombiesfx.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Zombie extends Entity {
    protected double speed;
    protected double damage;
    protected boolean attacking = false;
    private final long duration = 3000;
    private final double slowFactor = 0.90;
    protected boolean isSlowed = false;
    private long startTime;

    public Zombie(double x, double y, double health, double speed, double damage) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 40;
        this.health = this.maxHealth = health;
        this.speed = speed;
        this.damage = damage;
    }

    @Override
    public void render(GraphicsContext gc) {
        if (!slowEffectExpired()) {

        }
    }

    @Override
    public void update(double delta) {

    }

    public void renderHealth(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillRect(x, y - 10, width, 5);
        gc.setFill(Color.GREEN);
        gc.fillRect(x, y - 10, width * (health / maxHealth), 5);
    }

    public void attack(Plant p, double delta) {
        attacking = true;
        p.takeDamage(damage * delta);
    }

    public void move(double delta) {
        x -= speed * delta;
    }

    public void applySlow() {
        if (slowEffectExpired()) {
            startTime = System.currentTimeMillis();
        }
        this.speed = speed * slowFactor;
    }


    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public double getDamage() {
        return damage;
    }

    @Override
    public void setDamage(double damage) {
        this.damage = damage;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean slowEffectExpired() {
        long now = System.currentTimeMillis();
        return now - startTime > duration;
    }

}


