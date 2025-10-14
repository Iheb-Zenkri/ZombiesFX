package fx.zombiesfx.patterns.decorator;

import fx.zombiesfx.entities.Plant;
import fx.zombiesfx.entities.Zombie;

public class DoubleDamageDecorator extends PlantDecorator {
    public DoubleDamageDecorator(Plant wrapped) {
        super(wrapped);
    }

    @Override
    public void attack(Zombie z) {
        wrapped.attack(z);
        wrapped.attack(z); // deal double
    }
}
