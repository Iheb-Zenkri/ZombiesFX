package fx.zombiesfx.entities;


import fx.zombiesfx.assets.Assets;
import javafx.scene.canvas.GraphicsContext;

public class FastZombie extends Zombie {
    public FastZombie(double x, double y) {
        super(x, y, 70, 45, 10);
        zombieGif = Assets.get("/fx/zombiesfx/assets/fast-zombie-walking.gif");
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
        zombieGif = Assets.get("/fx/zombiesfx/assets/fast-zombie-eating.gif");
    }

    @Override
    public void attackingOff() {
        attacking = false;
        zombieGif = Assets.get("/fx/zombiesfx/assets/fast-zombie-walking.gif");
    }
}
