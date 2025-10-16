package fx.zombiesfx;

import fx.zombiesfx.game.GameState;
import fx.zombiesfx.game.MenuState;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    private GameState currentState;
    private final int screenWidth = 1600;
    private final int screenHeight = 900;
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

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
