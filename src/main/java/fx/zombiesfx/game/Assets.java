package fx.zombiesfx.assets;

import javafx.scene.image.Image;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Assets {
    private static final Map<String, Image> cache = new HashMap<>();

    public static Image get(String path) {
        if (cache.containsKey(path)) {
            return cache.get(path);
        }

        URL url = Assets.class.getResource(path);
        if (url == null) {
            throw new IllegalStateException("❌ Asset not found: " + path);
        }

        Image img = new Image(url.toExternalForm());
        cache.put(path, img);
        return img;
    }

    public static void preloadAll() {
        // grass's Image
        get("/fx/zombiesfx/assets/grass-dark.png");
        get("/fx/zombiesfx/assets/grass-light.png");

        // street's Image
        get("/fx/zombiesfx/assets/street.png");

        // sunflower's gifs
        get("/fx/zombiesfx/assets/sunflower.gif");
        get("/fx/zombiesfx/assets/sun.jpg");

        // PeaShooter's gifs
        get("/fx/zombiesfx/assets/pea-shooter.gif");
        get("/fx/zombiesfx/assets/pea-shooter-shooting.gif");

        // SnowPea's gifs
        get("/fx/zombiesfx/assets/snow-pea.gif");


        // Normal Zombie's gifs
        get("/fx/zombiesfx/assets/normal-zombie-walking.gif");
        get("/fx/zombiesfx/assets/normal-zombie-eating.gif");

        // Fast Zombie's gifs
        get("/fx/zombiesfx/assets/fast-zombie-walking.gif");
        get("/fx/zombiesfx/assets/fast-zombie-eating.gif");

        // Tank Zombie's gifs
        get("/fx/zombiesfx/assets/tank-zombie-walking.gif");
        get("/fx/zombiesfx/assets/tank-zombie-attacking.gif");

        // Projectile's images
        get("/fx/zombiesfx/assets/projectile.jpg");
        get("/fx/zombiesfx/assets/ice-projectile.jpg");
    }
}
