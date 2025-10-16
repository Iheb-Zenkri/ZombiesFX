package fx.zombiesfx.entities;

import fx.zombiesfx.assets.Assets;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Projectile extends Entity {
    protected Image projectImage;
    private final double speed = 300;

    public Projectile(double x, double y, double damage) {
        this.x = x;
        this.y = y;
        this.width = 24;
        this.height = 24;
        this.damage = damage;
        setProjectImage();
    }

    @Override
    public void update(double delta) {
        x += speed * delta;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(projectImage, x, y, width, height);
    }

    public void setProjectImage() {
        projectImage = Assets.get("/fx/zombiesfx/assets/projectile.jpg");
    }
}
