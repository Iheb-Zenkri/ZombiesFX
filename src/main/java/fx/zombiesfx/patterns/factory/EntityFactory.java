package fx.zombiesfx.patterns.factory;

import fx.zombiesfx.entities.*;

public class EntityFactory {
    public static Plant createPlant(String type, double x, double y) {
        return switch (type) {
            case "sun" -> new Sunflower(x, y);
            case "wall" -> new SnowPeat(x, y);
            default -> new PeaShooter(x, y);
        };
    }

    public static Zombie createZombie(String type, double x, double y) {
        return switch (type) {
            case "fast" -> new FastZombie(x, y);
            case "tank" -> new TankZombie(x, y);
            default -> new NormalZombie(x, y);
        };
    }
}
