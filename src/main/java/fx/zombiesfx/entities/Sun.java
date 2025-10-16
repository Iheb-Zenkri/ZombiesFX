package fx.zombiesfx.entities;

import fx.zombiesfx.assets.Assets;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class Sun extends Entity {
    private final Image sunImage;

    // Motion
    private final double startX;
    private final double startY;
    private final double endX;
    private final double endY;
    private final double duration = 3;    // seconds for the full arc
    private double t = 0;             // time (0 to 1)
    private boolean collected = false;
    private boolean clickable = true;
    private double alpha = 1.0;

    public Sun(double startX, double startY, double endX, double endY) {
        this.x = startX;
        this.y = startY;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.width = 60;
        this.height = 60;
        this.sunImage = Assets.get("/fx/zombiesfx/assets/sun.jpg");
    }

    public void update(double delta) {
        if (collected) {
            // float upward and fade out when collected
            y -= 80 * delta;
            alpha -= 1.5 * delta;
            if (alpha < 0) alpha = 0;
            return;
        }

        // Parabolic motion from start → end
        t += delta / duration;
        if (t > 1) t = 1;

        // Quadratic Bezier curve control point (midpoint up for the arc)
        double controlX = (startX + endX) / 2;
        double controlY = startY - 100; // the height of the arc (adjustable)

        // Bezier interpolation
        x = Math.pow(1 - t, 2) * startX + 2 * (1 - t) * t * controlX + Math.pow(t, 2) * endX;
        y = Math.pow(1 - t, 2) * startY + 2 * (1 - t) * t * controlY + Math.pow(t, 2) * endY;
    }

    public void render(GraphicsContext gc) {
        gc.setGlobalAlpha(alpha);
        gc.drawImage(sunImage, x, y, width, height);
        gc.setGlobalAlpha(1.0);
    }

    public boolean isCollected() {
        return collected;
    }

    public void onClick(MouseEvent e) {
        if (!clickable) return;
        if (e.getX() >= x && e.getX() <= x + width &&
                e.getY() >= y && e.getY() <= y + height) {
            collected = true;
            clickable = false;
            // TODO: add +25 sun points to player or play sound
        }
    }

    public boolean shouldBeRemoved() {
        return collected && alpha <= 0;
    }
}
