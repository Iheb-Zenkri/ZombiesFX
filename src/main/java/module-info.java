module fx.zombiesfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens fx.zombiesfx to javafx.fxml;
    exports fx.zombiesfx;
}