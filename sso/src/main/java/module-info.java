module com.cucei {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.cucei to javafx.fxml;
    exports com.cucei;
    exports com.cucei.states;
}
