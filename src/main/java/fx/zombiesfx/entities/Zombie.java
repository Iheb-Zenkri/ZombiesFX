package fx.zombiesfx.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Zombie extends Entity {
    protected double speed;
    protected double damage;
    protected Image zombieGif;
    protected boolean attacking = false;
    private final long duration = 3000;
    private final double slowFactor = 0.90;
    protected boolean isSlowed = false;
    private long startTime;

    public Zombie(double x, double y, double health, double speed, double damage) {
        this.x = x;
        this.y = y;
        this.width = 150;
        this.height = 150;
        this.health = this.maxHealth = health;
        this.speed = speed;
        this.damage = damage;
    }

    @Override
    public void update(double delta) {

    }

    public void renderHealth(GraphicsContext gc) {
        double barX = x + width * 0.15;
        double barY = y - 10;
        double barWidth = width * 0.7;
        double barHeight = 8;
        double arcRadius = 12; // how round the corners are

        gc.setFill(Color.RED);
        gc.fillRoundRect(barX, barY, barWidth, barHeight, arcRadius, arcRadius);

        gc.setFill(Color.DARKSLATEGRAY);
        gc.fillRoundRect(barX, barY, barWidth * (health / maxHealth), barHeight, arcRadius, arcRadius);
    }

    public void attack(Plant p, double delta) {
        attackingOn();
        p.takeDamage(damage * delta);
    }

    public void move(double delta) {
        attackingOff();
        x -= speed * delta;
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

    public void attackingOn() {
    }

    public void attackingOff() {
    }

    public boolean slowEffectExpired() {
        long now = System.currentTimeMillis();
        return now - startTime > duration;
    }

    public void applySlow() {
        if (slowEffectExpired()) {
            startTime = System.currentTimeMillis();
        }
        this.speed = speed * slowFactor;
    }

}


