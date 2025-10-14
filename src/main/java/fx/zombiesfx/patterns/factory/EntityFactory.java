package fx.zombiesfx.patterns.factory;

import fx.zombiesfx.entities.Plant;
import fx.zombiesfx.entities.Zombie;

public class EntityFactory {
    public static Plant createPlant(String type, double x, double y) {
        switch (type) {
            case "pea":
                return new Plant(x, y, "Peashooter");
            default:
                return new Plant(x, y, "Basic");
        }
    }

    public static Zombie createZombie(String type, double x, double y) {
        switch (type) {
            case "fast":
                return new Zombie(x, y, 60);
            default:
                return new Zombie(x, y, 40);
        }
    }
}
