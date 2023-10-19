package csc305.gymnasticsApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class GymnasticsAppBeta extends Application {

    private static File selectedFile = null;
    private static Scene scene;
    private static Stage stage;
    @Override
    public void start(Stage primaryStage){
        stage = primaryStage;
        scene = new Scene(new BorderPane(), 640, 480);
        stage.setScene(scene);
        stage.setResizable(true);

        stage.setOnCloseRequest(event -> {

            if(stage.isMaximized()) {
                stage.setMaximized(false);
            } else {
                stage.setMaximized(true);
            }
        });

        switchToHomePage();
        stage.show();

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

    public static void switchToMainEditDisplay(){switchToView("/csc305.gymnasticsApp/mainEditDisplay.fxml");}

    public static void switchToHomePage(){
        switchToView("/csc305.gymnasticsApp/homePage.fxml");
    }

    public static void switchToLessonPlan(){
        switchToView("/csc305.gymnasticsApp/lessonPlan.fxml");
    }

    public static void switchToPreviewPage(){
        switchToView("/csc305.gymnasticsApp/previewPage.fxml");
    }
    public static void callFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File gymFile = fileChooser.showOpenDialog(stage);

        if(gymFile != null) {
            selectedFile = gymFile;
            System.out.println("Selected file: " + selectedFile.getName());
        }
    }

    public static void callPrinter(){
        PrinterJob job = PrinterJob.createPrinterJob();

        if(job != null) {
            boolean success = job.showPrintDialog(null);
            if(success) {
                System.out.println(selectedFile.getName());
                job.endJob();
            }
        }
    }
}
