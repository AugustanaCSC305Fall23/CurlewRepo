module GymnasticsProject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.opencsv;

    opens csc305.gymnasticsApp to javafx.fxml;
    exports csc305.gymnasticsApp;
//    exports;
//    opens to;
}