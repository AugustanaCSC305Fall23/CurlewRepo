module GymnasticsProject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires com.google.gson;
    requires java.prefs;
    requires org.apache.commons.csv;

    exports csc305.gymnasticsApp;
    opens csc305.gymnasticsApp to javafx.fxml;
    exports csc305.gymnasticsApp.filters;
    opens csc305.gymnasticsApp.filters to javafx.fxml;
    exports csc305.gymnasticsApp.data;
    opens csc305.gymnasticsApp.data to javafx.fxml;
    exports csc305.gymnasticsApp.ui;
    opens csc305.gymnasticsApp.ui to javafx.fxml;
}