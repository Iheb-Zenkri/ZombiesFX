package fx.zombiesfx.entities;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FastZombie extends Zombie {
    public FastZombie(double x, double y) {
        super(x, y, 70, 45, 10);
    }

    @Override
    public void update(double delta) {
        super.update(delta);
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
        gc.setFill(Color.DARKCYAN);
        gc.fillRect(x, y, width, height);
        renderHealth(gc);
        gc.restore();
    }
}
