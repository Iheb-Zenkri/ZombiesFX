package fx.zombiesfx.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class GameSubject {
    private final List<GameObserver> observers = new ArrayList<>();

    public void addObserver(GameObserver o) {
        observers.add(o);
    }

    public void removeObserver(GameObserver o) {
        observers.remove(o);
    }

    public void notifyScoreChange(int score) {
        for (GameObserver o : observers) o.onScoreChange(score);
    }
}
