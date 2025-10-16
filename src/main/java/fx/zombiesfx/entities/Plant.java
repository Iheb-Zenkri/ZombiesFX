package fx.zombiesfx.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Plant extends Entity {
    protected Image plantGif;
    protected boolean animationOn = false;

    protected double cooldown = 1.5;
    protected double timeSinceLastAction = 0;

    public Plant(double x, double y, double health) {
        this.x = x;
        this.y = y;
        this.width = 80;
        this.height = 80;
        this.health = this.maxHealth = health;
    }

    @Override
    public void update(double delta) {
        timeSinceLastAction += delta;
    }

    public void renderHealth(GraphicsContext gc) {
        double barX = x + width * 0.15;
        double barY = y - 20;
        double barWidth = width * 0.7;
        double barHeight = 8;
        double arcRadius = 12; // how round the corners are

        // Draw background (red)
        gc.setFill(Color.RED);
        gc.fillRoundRect(barX, barY, barWidth, barHeight, arcRadius, arcRadius);

        // Draw foreground (green), proportional to health
        gc.setFill(Color.GREEN);
        gc.fillRoundRect(barX, barY, barWidth * (health / maxHealth), barHeight, arcRadius, arcRadius);
    }

    public boolean canAct() {
        return timeSinceLastAction >= cooldown;
    }

    public void resetCooldown() {
        timeSinceLastAction = 0;
    }

    public boolean isAnimationOn() {
        return animationOn;
    }

    public void animationOn() {
    }

    public void animationOff() {
    }
}
