package fx.zombiesfx.patterns.strategy;

import fx.zombiesfx.entities.Zombie;

public class SingleShotStrategy implements AttackStrategy {
    @Override
    public void attack(Zombie target) {
        target.damage(10);
    }
}
