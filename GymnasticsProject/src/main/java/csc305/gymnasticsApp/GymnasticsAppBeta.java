package csc305.gymnasticsApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The GymnasticsAppBeta class is the main application for the Gymnastics App.
 * It extends the JavaFX Application class and provides functionality to switch between
 * different views and handle file operations.
 */
public class GymnasticsAppBeta extends Application {
    // Static variables for the selected file, scene, and stage.
    private static File selectedFile = null;
    private static Scene scene;
    private static Stage stage;

    private static boolean fileLoaded = false;

    /**
     *
     * @param primaryStage - The primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage primaryStage){
        stage = primaryStage;
        scene = new Scene(new BorderPane(), 1000, 700);
        stage.setScene(scene);
        stage.setResizable(true);

        // Handles window close event by toggling maximized state.
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

    /**
     * @param fxmlFileName - The name of the FXML file
     * Switches the application's view to the selected FXML view
     */
    private static void switchToView(String fxmlFileName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GymnasticsAppBeta.class.getResource(fxmlFileName));
            scene.setRoot(fxmlLoader.load());
        } catch (IOException ex) {
            System.err.println("Can't find FXML file " + fxmlFileName);
            ex.printStackTrace();
        }
    }

    /**
     * Switches the view to the main edit display
     */
    public static void switchToMainEditDisplay(){switchToView("/csc305.gymnasticsApp/mainEditDisplay.fxml");}

    /**
     * Switches the view to the home page display
     */
    public static void switchToHomePage(){switchToView("/csc305.gymnasticsApp/homePage.fxml");}

    /**
     * Switches the view to the preview page display.
     */
    public static void switchToPreviewPage(){
        switchToView("/csc305.gymnasticsApp/previewPage.fxml");
    }

    /**
     * Opens a file chooser dialog for selecting the Gymnastics Picture files
     */
    public static void callFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Gym Lesson Plan");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Gym Plan Files (*.GymPlanFile)", "*.GymPlanFile");
        fileChooser.getExtensionFilters().add(extFilter);
        File gymPlanFile = fileChooser.showOpenDialog(stage);
        if(gymPlanFile != null) {
            selectedFile = gymPlanFile;
        }

    }
    /**
     * Retrieves the currently selected file
     * @return - The selected file, or null if no file is selected is return.
     */
    public static File getFile(){
        return selectedFile;
    }

    public static boolean getLoaded() {return fileLoaded; }
    public static ArrayList<String> setPreviewPage(){
        fileLoaded = true;
        ArrayList<String> arrayList = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                arrayList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
