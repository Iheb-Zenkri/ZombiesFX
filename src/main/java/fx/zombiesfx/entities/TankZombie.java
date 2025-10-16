package fx.zombiesfx.entities;

import fx.zombiesfx.assets.Assets;
import javafx.scene.canvas.GraphicsContext;

public class TankZombie extends Zombie {
    public TankZombie(double x, double y) {
        super(x, y, 200, 15, 20);
        this.setWidth(180);
        zombieGif = Assets.get("/fx/zombiesfx/assets/tank-zombie-walking.gif");
    }

    @Override
    public void update(double delta) {
        super.update(delta);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(zombieGif, x, y, width, height);
        renderHealth(gc);
    }


    @Override
    public void attackingOn() {
        attacking = true;
        zombieGif = Assets.get("/fx/zombiesfx/assets/tank-zombie-attacking.gif");
    }

    @Override
    public void attackingOff() {
        attacking = false;
        zombieGif = Assets.get("/fx/zombiesfx/assets/tank-zombie-walking.gif");
    }
}