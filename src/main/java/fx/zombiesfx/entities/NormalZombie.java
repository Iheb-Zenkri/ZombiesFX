package fx.zombiesfx.entities;

import fx.zombiesfx.assets.Assets;
import javafx.scene.canvas.GraphicsContext;

public class NormalZombie extends Zombie {
    public NormalZombie(double x, double y) {
        super(x, y, 100, 25, 15);
        zombieGif = Assets.get("/fx/zombiesfx/assets/normal-zombie-walking.gif");
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
        zombieGif = Assets.get("/fx/zombiesfx/assets/normal-zombie-eating.gif");
    }

    @Override
    public void attackingOff() {
        attacking = false;
        zombieGif = Assets.get("/fx/zombiesfx/assets/normal-zombie-walking.gif");
    }
}

