package csc305.gymnasticsApp.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import csc305.gymnasticsApp.data.*;

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

    private static LessonPlan lessonPlan;


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
        lessonPlan = new LessonPlan();
        stage = primaryStage;
        scene = new Scene(new BorderPane(), 1000, 700);
        stage.setScene(scene);
        stage.setResizable(true);
        MainEditDisplayController start = new MainEditDisplayController();
        new Thread(() -> {
            try{Thread.sleep(1); } catch (InterruptedException e) { }
            Platform.runLater(() -> start.createCardButtons());
            }).start();

        //start.createCardButtons();
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
    public static void switchToMainEditDisplay(){switchToView("/csc305/gymnasticsApp/mainEditDisplay.fxml");}


    public static void switchToCourseEditPage(){
        switchToView("/csc305/gymnasticsApp/courseEditPage.fxml");
    }
    /**
     * Switches the view to the home page display
     */
    public static void switchToHomePage(){
        switchToView("/csc305/gymnasticsApp/homePage.fxml");
        LessonPlan.resetBoolean();
    }

    /**
     * Switches the view to the preview page display.
     */
    public static void switchToPreviewPage(){
        switchToView("/csc305/gymnasticsApp/previewPage.fxml");
    }

    /**
     * Switches to the template page display.
     */
    public static void switchToTemplatePage() {
        switchToView("/csc305/gymnasticsApp/templatePage.fxml");
    }


    /**
     * Switches to the about page
     */
    public static void switchToAboutPage() {switchToView("/csc305/gymnasticsApp/aboutPage.fxml");}

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
            fileLoaded = true;
        }

    }

    /**
     * Selects a file and sets it to the selectedFile variable and sets the fileLoaded variable true
     * @param file the current file that is being selected
     */
    public static void callFileChooser(File file){
        selectedFile = file;
        fileLoaded = true;
    }
    /**
     * Retrieves the currently selected file
     * @return - The selected file, or null if no file is selected is return.
     */
    public static File getFile(){
        return selectedFile;
    }

    /**
        Checks if the user imports a file on the home page
     */
    public static boolean getLoaded() {return fileLoaded; }

    /**
     * Reads through a file and breaks the different lines of the file up into separate ArrayList indexes
     * @return Returns an ArrayList containing all the information from the file
     */
    public static ArrayList<String> readLessonPlan(){
        ArrayList<String> arrayList = new ArrayList<String>();
        if(selectedFile != null) {
            fileLoaded = true;
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    arrayList.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    /**
     * Gets the entire lesson plan
     * @return Returns the lesson plan
     */
    public static LessonPlan getLessonPlan() {
        return lessonPlan;
    }

    /**
     * Sets an old lesson plan to a new lesson plan
     * @param newLessonPlan A lesson plan that is being set
     */
    public static void setLessonPlan(LessonPlan newLessonPlan) {
        lessonPlan = newLessonPlan;
    }

}
