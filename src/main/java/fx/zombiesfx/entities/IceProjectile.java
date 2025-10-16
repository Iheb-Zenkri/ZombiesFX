package fx.zombiesfx.entities;

import fx.zombiesfx.assets.Assets;
import javafx.scene.canvas.GraphicsContext;

public class IceProjectile extends Projectile {
    public IceProjectile(double x, double y, double damage) {
        super(x, y, damage);
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }

    @Override
    public void setProjectImage() {
        this.projectImage = Assets.get("/fx/zombiesfx/assets/ice-projectile.jpg");
    }
}
