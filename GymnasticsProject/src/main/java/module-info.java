module GymnasticsProject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.opencsv;

    opens csc305.gymnasticsApp to javafx.fxml;
    exports csc305.gymnasticsApp;
    exports csc305.gymnasticsApp.CardFilter;
    opens csc305.gymnasticsApp.CardFilter to javafx.fxml;
//    exports;
//    opens to;
}