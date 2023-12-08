module GymnasticsProject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.opencsv;
    requires java.sql;
    requires com.google.gson;
    requires java.prefs;

    exports csc305.gymnasticsApp.filters;
    opens csc305.gymnasticsApp.filters to javafx.fxml;
    exports csc305.gymnasticsApp.data;
    opens csc305.gymnasticsApp.data to javafx.fxml;
    exports csc305.gymnasticsApp.ui;
    opens csc305.gymnasticsApp.ui to javafx.fxml;
//    exports;
//    opens to;
}