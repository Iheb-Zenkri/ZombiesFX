package fx.zombiesfx.entities;

import fx.zombiesfx.assets.Assets;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class Sunflower extends Plant {
    private final double sunCooldown = 15.0; // produce every 5s
    private double timeSinceLastSun = 0;

    public Sunflower(double x, double y) {
        super(x, y, 60);
        plantGif = Assets.get("/fx/zombiesfx/assets/sunflower.gif");
    }

    @Override
    public void update(double delta) {
        super.update(delta);
        timeSinceLastSun += delta;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(plantGif, x, y, width, height);
        renderHealth(gc);
    }

    public boolean canProduceSun() {
        return timeSinceLastSun >= sunCooldown;
    }

    public void resetSunTimer() {
        timeSinceLastSun = 0;
    }

    public void tryProduceSun(List<Sun> suns) {
        if (canProduceSun()) {
            suns.add(new Sun(x + width / 2 - 30, y, x - 50, y + height)); // appear above the flower
            resetSunTimer();
        }
    }
}
