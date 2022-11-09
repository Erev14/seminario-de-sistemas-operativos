module com.cucei {
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires javafx.fxml;

    opens com.cucei to javafx.fxml;
    exports com.cucei;
    exports com.cucei.models;
    exports com.cucei.runnables;
    exports com.cucei.services;
    exports com.cucei.states;
    exports com.cucei.views;
}
