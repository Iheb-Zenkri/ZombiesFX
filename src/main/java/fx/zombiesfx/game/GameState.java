package fx.zombiesfx.game;

import fx.zombiesfx.App;
import javafx.stage.Stage;

public abstract class GameState {
    protected App app;
    protected Stage stage;

    public GameState(App app, Stage stage) {
        this.app = app;
        this.stage = stage;
    }

    public abstract void start();
}
