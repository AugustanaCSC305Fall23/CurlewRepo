package csc305.gymnasticsApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GymnasticsAppBeta extends Application {
    private static Scene scene;
    @Override
    public void start(Stage primaryStage){
        scene = new Scene(new BorderPane(), 640, 480);
        primaryStage.setScene(scene);
        switchToHomePage();
        primaryStage.show();

    }

    private static void switchToView(String fxmlFileName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GymnasticsAppBeta.class.getResource(fxmlFileName));
            scene.setRoot(fxmlLoader.load());
        } catch (IOException ex) {
            System.err.println("Can't find FXML file " + fxmlFileName);
            ex.printStackTrace();
        }

    }

    public static void switchToMainEdit(){
        switchToView("csc305.gymnasticsApp/mainEditDisplay.fxml");
    }
    public static void switchToHomePage(){
        switchToView("csc305.gymnasticsApp/homePage.fxml");
    }

}
