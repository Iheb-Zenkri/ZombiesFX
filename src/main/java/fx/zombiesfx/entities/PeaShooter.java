package fx.zombiesfx.entities;

import fx.zombiesfx.assets.Assets;
import javafx.scene.canvas.GraphicsContext;

public class PeaShooter extends Plant {
    public PeaShooter(double x, double y) {
        super(x, y, 100);
        animationOn();
        this.damage = 20;
        this.cooldown = 2.8;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(plantGif, x, y, width, height);
        renderHealth(gc);
    }

    public Projectile shoot() {
        if (canAct()) {
            resetCooldown();
            if (!isAnimationOn()) animationOn();
            return new Projectile(x + width, y + (height * 0.2), damage);
        }
        return null;
    }


    @Override
    public void animationOn() {
        plantGif = Assets.get("/fx/zombiesfx/assets/pea-shooter-shooting.gif");
    }

    @Override
    public void animationOff() {
        plantGif = Assets.get("/fx/zombiesfx/assets/pea-shooter.gif");
    }

}
