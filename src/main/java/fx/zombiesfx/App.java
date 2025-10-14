package fx.zombiesfx;

import fx.zombiesfx.game.GameState;
import fx.zombiesfx.game.MenuState;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    private GameState currentState;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        currentState = new MenuState(this, stage);
        currentState.start();
    }

    public void setState(GameState state) {
        this.currentState = state;
        currentState.start();
    }
}
