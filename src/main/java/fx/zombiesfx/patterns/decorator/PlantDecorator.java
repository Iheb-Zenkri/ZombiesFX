package fx.zombiesfx.patterns.decorator;

import fx.zombiesfx.entities.Plant;

public abstract class PlantDecorator extends Plant {
    protected Plant wrapped;

    public PlantDecorator(Plant wrapped) {
        super(wrapped.getX(), wrapped.getY(), wrapped.getName());
        this.wrapped = wrapped;
    }

    @Override
    public void update(double delta) {
        wrapped.update(delta);
    }
}
