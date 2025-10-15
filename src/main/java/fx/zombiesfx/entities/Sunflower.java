package fx.zombiesfx.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Sunflower extends Plant {
    private final double sunCooldown = 5.0; // produce every 5s
    private double timeSinceLastSun = 0;

    public Sunflower(double x, double y) {
        super(x, y, 40, 40, 60);
    }

    @Override
    public void update(double delta) {
        super.update(delta);
        timeSinceLastSun += delta;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
        gc.fillOval(x, y, width, height);
        renderHealth(gc);
    }

    public boolean canProduceSun() {
        return timeSinceLastSun >= sunCooldown;
    }

    public void resetSunTimer() {
        timeSinceLastSun = 0;
    }
}
