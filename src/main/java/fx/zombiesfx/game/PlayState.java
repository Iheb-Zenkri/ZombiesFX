package fx.zombiesfx.game;

import fx.zombiesfx.App;
import fx.zombiesfx.assets.Assets;
import fx.zombiesfx.entities.*;
import fx.zombiesfx.patterns.factory.EntityFactory;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class PlayState extends GameState {
    private final List<Plant> plants = new ArrayList<>();
    private final List<Projectile> projectiles = new ArrayList<>();
    private final List<Zombie> zombies = new ArrayList<>();
    private final List<Sun> suns = new ArrayList<>();

    private AnimationTimer timer;
    private final Random random = new Random();
    private double spawnTimer = 0;
    private GraphicsContext gc;

    private int sunPoints = 1000; // start with some suns
    private String selectedPlantType = "pea";

    public PlayState(App app, Stage stage) {
        super(app, stage);
    }

    @Override
    public void start() {
        Canvas canvas = new Canvas(app.getScreenWidth(), app.getScreenHeight());
        gc = canvas.getGraphicsContext2D();

        canvas.setOnMouseClicked(this::handleClick);

        Assets.preloadAll();

        Scene scene = new Scene(new StackPane(canvas));
        scene.setOnKeyPressed(this::handleKey);

        stage.setScene(scene);
        stage.setTitle("Plant vs Zombie - Play!");
        stage.show();

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
    }

    private void handleClick(MouseEvent e) {
        int row = GameGrid.getRowFromY(e.getY());
        int col = GameGrid.getColFromX(e.getX());
        double px = GameGrid.getCellX(col);
        double py = GameGrid.getCellY(row);

        // Prevent planting over another plant
        for (Plant p : plants) {
            if (Math.abs(p.getX() - px) < p.getWidth() && Math.abs(p.getY() - py) < p.getHeight()) return;
        }

        int cost = getPlantCost(selectedPlantType);
        if (sunPoints < cost) return; // not enough sun

        Plant newPlant = EntityFactory.createPlant(selectedPlantType, px, py);
        newPlant.setX(px + (GameGrid.CELL_WIDTH - newPlant.getWidth()) / 2);
        newPlant.setY(py + (GameGrid.CELL_HEIGHT - newPlant.getHeight()) / 2);
        plants.add(newPlant);
        sunPoints -= cost;
    }

    private void handleKey(KeyEvent e) {
        switch (e.getCode()) {
            case DIGIT1 -> selectedPlantType = "pea";
            case DIGIT2 -> selectedPlantType = "sun";
            case DIGIT3 -> selectedPlantType = "wall";
        }
    }

    private void update(double delta) {
        spawnTimer += delta;

        // Spawn zombie every 4 seconds
        if (spawnTimer > 4) {
            spawnTimer = 0;
            int row = random.nextInt(GameGrid.ROWS);
            double y = GameGrid.getCellY(row);
            String[] types = {"normal", "fast", "tank"};
            String type = types[random.nextInt(types.length)];

            Zombie newZombie = EntityFactory.createZombie("tank", app.getScreenWidth(), y);
            newZombie.setX(app.getScreenWidth() - newZombie.getWidth() * 0.2);
            newZombie.setY(y + (GameGrid.CELL_HEIGHT - newZombie.getHeight()) / 2);
            zombies.add(newZombie);
        }

        // Update plants
        for (Plant plant : plants) {
            plant.update(delta);

            // Sunflower: produce suns
            if (plant instanceof Sunflower sun && sun.canProduceSun()) {
                sunPoints += 25;
                sun.tryProduceSun(suns);
                sun.resetSunTimer();
            }

            // Suns: fade effects
            for (Sun s : suns) {
                s.update(delta);
            }
            suns.removeIf(Sun::shouldBeRemoved);

            // PeaShooter: shoot
            if (plant instanceof PeaShooter shooter) {
                boolean zombieInLane = zombies.stream()
                        .anyMatch(z ->
                                Math.abs(z.getY() - plant.getY()) < Math.max(z.getHeight(), plant.getHeight())
                                        && z.getX() > plant.getX());
                if (zombieInLane && shooter.canAct()) {
                    Projectile proj = shooter.shoot();
                    if (proj != null) projectiles.add(proj);
                }
                if (!zombieInLane) {
                    plant.animationOff();
                }
            }

            // SnowPeat: shoot
            if (plant instanceof SnowPeat snowPeat) {
                boolean zombieInLane = zombies.stream()
                        .anyMatch(
                                z -> Math.abs(z.getY() - plant.getY()) < Math.max(z.getHeight(), plant.getHeight())
                                        && z.getX() > plant.getX());
                if (zombieInLane && snowPeat.canAct()) {
                    IceProjectile iceProjectile = snowPeat.shoot();
                    if (iceProjectile != null) projectiles.add(iceProjectile);
                }
                if (!zombieInLane) {
                    plant.animationOff();
                }
            }
        }

        // Update projectiles
        Iterator<Projectile> itProj = projectiles.iterator();
        while (itProj.hasNext()) {
            Projectile p = itProj.next();
            p.update(delta);

            for (Zombie z : zombies) {
                if (Math.abs(p.getX() - z.getX()) < 20
                        && Math.abs(p.getY() - z.getY()) < z.getHeight()
                ) {
                    z.takeDamage(p.getDamage());
                    if (p instanceof IceProjectile) {
                        z.applySlow();
                    }
                    itProj.remove();
                    break;
                }
            }
            if (p.getX() > app.getScreenWidth()) itProj.remove();
        }

        // Update zombies
        Iterator<Zombie> itZ = zombies.iterator();
        while (itZ.hasNext()) {
            Zombie z = itZ.next();
            z.update(delta);
            boolean attacking = false;

            for (Iterator<Plant> itP = plants.iterator(); itP.hasNext(); ) {
                Plant p = itP.next();
                if (
                        Math.abs(z.getY() - p.getY()) < Math.max(z.getHeight(), p.getHeight())
                                && z.getX() < p.getX() + z.getWidth() * 0.5
                                && z.getX() > p.getX()
                ) {
                    z.attack(p, delta);
                    attacking = true;
                    if (!p.isAlive()) itP.remove();
                    break;
                }
            }
            if (!attacking) {
                z.move(delta);
            }

            if (!z.isAlive()) itZ.remove();

            if (z.getX() < 0) {
                timer.stop();
                app.setState(new GameOverState(app, stage));
                return;
            }
        }
    }

    private void render(GraphicsContext gc) {
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(0, 0, app.getScreenWidth(), app.getScreenHeight());

        // Draw grid
        gc.setStroke(Color.LIGHTYELLOW);
        for (int r = 0; r < GameGrid.ROWS; r++) {
            for (int c = 0; c < GameGrid.COLS; c++) {
                gc.strokeRect(c * GameGrid.CELL_WIDTH, r * GameGrid.CELL_HEIGHT + 50,
                        GameGrid.CELL_WIDTH, GameGrid.CELL_HEIGHT);
            }
        }

        // Draw entities
        plants.forEach(p -> p.render(gc));
        projectiles.forEach(p -> p.render(gc));
        zombies.forEach(z -> z.render(gc));
        suns.forEach(s -> s.render(gc));

        // HUD
        gc.setFill(Color.BLACK);
        gc.fillText("☀ Sun: " + sunPoints, 20, 20);
        gc.fillText("Selected: " + selectedPlantType + " (1-3)", 120, 20);
    }

    private int getPlantCost(String type) {
        return switch (type) {
            case "sun" -> 25;
            case "wall" -> 75;
            default -> 50;
        };
    }


}

