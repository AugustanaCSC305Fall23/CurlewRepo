module GymnasticsProject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens csc305.gymnasticsApp to javafx.fxml;
    exports csc305.gymnasticsApp;
}