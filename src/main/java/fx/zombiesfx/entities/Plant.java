package fx.zombiesfx.entities;

import fx.zombiesfx.patterns.strategy.AttackStrategy;
import fx.zombiesfx.patterns.strategy.SingleShotStrategy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Plant extends Entity {
    private final String name;
    private AttackStrategy attackStrategy;
    private final double cooldown = 1.5; // seconds between shots
    private double timeSinceLastShot = 0;

    public Plant(double x, double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.attackStrategy = new SingleShotStrategy();
    }

    public void setAttackStrategy(AttackStrategy strategy) {
        this.attackStrategy = strategy;
    }

    @Override
    public void update(double delta) {
        timeSinceLastShot += delta;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillRect(x, y, 30, 30);
        gc.setFill(Color.BLACK);
        gc.fillText(name, x - 5, y - 5);
    }

    public boolean canShoot() {
        return timeSinceLastShot >= cooldown;
    }

    public Projectile shoot() {
        if (canShoot()) {
            timeSinceLastShot = 0;
            return new Projectile(x + 30, y + 10, 100);
        }
        return null;
    }

    public void attack(Zombie target) {
        attackStrategy.attack(target);
    }

    public String getName() {
        return name;
    }
}
