package fx.zombiesfx.entities;

import fx.zombiesfx.assets.Assets;
import javafx.scene.canvas.GraphicsContext;

public class SnowPeat extends Plant {
    public SnowPeat(double x, double y) {
        super(x, y, 80);
        animationOff();
        this.damage = 12;
        this.cooldown = 4.0;
    }
    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(plantGif, x, y, width, height);
        renderHealth(gc);
    }

    public IceProjectile shoot() {
        if (canAct()) {
            resetCooldown();
            if (!isAnimationOn()) animationOn();
            return new IceProjectile(x + 40, y + 30, damage);
        }
        return null;
    }

    @Override
    public void animationOn() {
        plantGif = Assets.get("/fx/zombiesfx/assets/snow-pea.gif");
    }

    @Override
    public void animationOff() {
        plantGif = Assets.get("/fx/zombiesfx/assets/snow-pea.gif");
    }

}
