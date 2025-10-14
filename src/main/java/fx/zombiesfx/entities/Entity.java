package fx.zombiesfx.entities;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
    protected double x, y;
    protected boolean alive = true;

    public abstract void update(double delta);

    public abstract void render(GraphicsContext gc);

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        this.alive = false;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
