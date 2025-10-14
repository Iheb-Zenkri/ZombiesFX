package fx.zombiesfx.entities;

public class NormalZombie extends Zombie {
    public NormalZombie(double x, double y) {
        super(x, y, 100, 25, 15); // health, speed, damage
    }

    @Override
    public void update(double delta) {

    }
}

