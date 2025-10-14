package fx.zombiesfx.patterns.strategy;

import fx.zombiesfx.entities.Zombie;

public interface AttackStrategy {
    void attack(Zombie target);
}
