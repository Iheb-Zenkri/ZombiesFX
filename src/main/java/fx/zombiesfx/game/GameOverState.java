package fx.zombiesfx.game;

import fx.zombiesfx.App;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameOverState extends GameState {
    public GameOverState(App app, Stage stage) {
        super(app, stage);
    }

    @Override
    public void start() {
        Button retry = new Button("Try Again");
        retry.setOnAction(e -> app.setState(new PlayState(app, stage)));
        VBox root = new VBox(20, retry);
        root.setStyle("-fx-alignment: center; -fx-padding: 50;");
        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle("Game Over!");
    }
}
