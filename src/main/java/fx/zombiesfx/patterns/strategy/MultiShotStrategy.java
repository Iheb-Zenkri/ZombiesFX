package fx.zombiesfx.patterns.strategy;

import fx.zombiesfx.entities.Zombie;

public class MultiShotStrategy implements AttackStrategy {
    @Override
    public void attack(Zombie target) {
        target.takeDamage(5);
    }
}
