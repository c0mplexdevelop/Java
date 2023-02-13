module cim.calc {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens cim.calc to javafx.fxml;
    exports cim.calc;
}
