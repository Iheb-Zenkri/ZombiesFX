package fx.zombiesfx.game;

import fx.zombiesfx.App;
import fx.zombiesfx.entities.Entity;
import fx.zombiesfx.entities.Plant;
import fx.zombiesfx.entities.Projectile;
import fx.zombiesfx.entities.Zombie;
import fx.zombiesfx.patterns.factory.EntityFactory;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PlayState extends GameState {
    private final List<Entity> entities = new ArrayList<>();
    private final List<Projectile> projectiles = new ArrayList<>();
    private final List<Zombie> zombies = new ArrayList<>();
    private AnimationTimer timer;

    public PlayState(App app, Stage stage) {
        super(app, stage);
    }

    @Override
    public void start() {
        Canvas canvas = new Canvas(600, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Create one plant and one zombie for demo
        Plant plant = EntityFactory.createPlant("pea", 100, 200);
        entities.add(plant);
        zombies.add(EntityFactory.createZombie("fast", 500, 200));

        timer = new AnimationTimer() {
            long last = 0;

            @Override
            public void handle(long now) {
                if (last == 0) last = now;
                double delta = (now - last) / 1e9;
                last = now;
                update(delta);
                render(gc);
            }
        };
        timer.start();

        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
    }

    private void update(double delta) {
        // Update entities
        for (Entity e : entities) e.update(delta);
        for (Projectile p : projectiles) p.update(delta);
        for (Zombie z : zombies) z.update(delta);

        // Plant shooting
        for (Entity e : entities) {
            if (e instanceof Plant plant && plant.canShoot()) {
                Projectile proj = plant.shoot();
                if (proj != null) projectiles.add(proj);
            }
        }

        // Collision detection (simple)
        for (Projectile p : new ArrayList<>(projectiles)) {
            for (Zombie z : zombies) {
                if (Math.abs(p.getX() - z.getX()) < 20 && Math.abs(p.getY() - z.getY()) < 20) {
                    z.damage(p.getDamage());
                    p.kill();
                }
            }
        }

        // Clean up dead objects
        projectiles.removeIf(p -> !p.isAlive());
        zombies.removeIf(z -> !z.isAlive());

        // Check game over
        if (zombies.isEmpty()) {
            timer.stop();
            app.setState(new GameOverState(app, stage));
        }
    }

    private void render(GraphicsContext gc) {
        gc.clearRect(0, 0, 600, 400);
        for (Entity e : entities) e.render(gc);
        for (Projectile p : projectiles) p.render(gc);
        for (Zombie z : zombies) z.render(gc);
    }
}
