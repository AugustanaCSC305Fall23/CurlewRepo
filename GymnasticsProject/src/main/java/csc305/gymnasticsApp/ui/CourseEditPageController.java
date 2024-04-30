package csc305.gymnasticsApp.ui;

import csc305.gymnasticsApp.data.Course;
import csc305.gymnasticsApp.data.LessonPlan;
import csc305.gymnasticsApp.data.PrefCourses;
import csc305.gymnasticsApp.data.PrefFileLocation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * The CourseEditPageController class handles the interactions and logic for the Course Edit Page in the application
 */
public class CourseEditPageController {
    @FXML
    private TreeView treeView;
    @FXML
    private TextField courseName;
    public static LessonPlan loadPlan;
    @FXML private Button loadLessonPlanButton;


    @FXML
    private Button createNewLessonPlanButton;
    @FXML
    private Button preMadeLessonButton;
    @FXML
    private Button resetCourseButton;
    @FXML
    private Button homePageButton;
    @FXML
    private Button saveCourseButton;
    @FXML
    private Button loadCourseButton;

    private Course course;
    public static TreeItem<String> rootItem = new TreeItem<>("Root");
    private static CourseUndoRedoHandler undoRedoHandler;

    private PrefCourses recentCourses = GymnasticsAppBeta.getRecentCourses();
    private PrefFileLocation saveLocation = GymnasticsAppBeta.getSavedLocation();

    /**
     * Initializes the Course Edit Page
     */
    @FXML
    public void initialize() {
        course = GymnasticsAppBeta.getCourse();
        initializeTreeView();
        courseName.setText(Course.getCourseName());
        courseName.textProperty().addListener((obs, oldVal, newVal) -> changeCourseNameHandle());
        undoRedoHandler = new CourseUndoRedoHandler(course);
        GymnasticsAppBeta.courseURHandle = undoRedoHandler;
    }

    /**
     * Handles the undo button click
     */
    @FXML
    public void undoButtonHandle(){
        undoRedoHandler.undo();
    }

    /**
     * Handles the redo button click
     */
    @FXML
    public void redoButtonHandle(){
        undoRedoHandler.redo();
    }

    /**
     * Initializes the tree view with lesson plans
     */
    public void initializeTreeView(){
        rootItem.getChildren().clear();
        if (rootItem.getChildren().isEmpty()) {
            if(!course.getTheCourse().getLessonPlanList().isEmpty()){
                addItemsToTreeView(course);
            }
        }


        treeView.setShowRoot(false);
        treeView.setRoot(rootItem);

    }

    /**
     * Clears all child items from the root of the tree view.
     * This method is used to reset the tree view by removing all existing items.
     */
    public static void clearTreeCardItems(){
        rootItem.getChildren().clear();
    }

    /**
     * Adds lesson plan items to the tree view based on the specified course.
     *
     * @param course The course containing lesson plans to be added to the tree view.
     */
    public static void addItemsToTreeView(Course course){
        for(int i = 0; i < course.getTheCourse().getLessonPlanList().size(); i++) {
            TreeItem<String> lesson = new TreeItem<>();
            lesson.setValue(course.getTheCourse().getLessonPlanList().get(i).getLessonPlanTitle());
            rootItem.getChildren().add(lesson);}
    }

    /**
     * Handles the selection of an item in the tree view
     *
     * @param event The mouse event
     */
    public void selectItem(MouseEvent event){
        TreeItem<String> selectedItem = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Caution");
            alert.setHeaderText("Would you like to edit or delete this LessonPlan?");
            alert.setContentText("Please select an option.");
            ButtonType editButton = new ButtonType("Edit");
            ButtonType deleteButton = new ButtonType("Delete");
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(editButton, deleteButton, cancelButton);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == editButton) {

                MainEditDisplayController.clearTreeCardItems();
                MainEditDisplayController.events.clear();
                MainEditDisplayController.resetButtons();

                for (int i = 0; i < course.getTheCourse().getLessonPlanList().size(); i++) {
                    if (course.getTheCourse().getLessonPlanList().get(i).getLessonPlanTitle().equals(selectedItem.getValue())) {
                        LessonPlan selectedLessonPlan = course.getTheCourse().getLessonPlanList().get(i);
                        GymnasticsAppBeta.setLessonPlan(selectedLessonPlan);
                        MainEditDisplayController.isInitialized = false;
                        GymnasticsAppBeta.switchToMainEditDisplay();
                        MainEditDisplayController.addTreeCardItems(selectedLessonPlan);


                    }
                }
            } else if(result.get() == deleteButton){
                for (int i = 0; i < course.getTheCourse().getLessonPlanList().size(); i++) {
                    if (course.getTheCourse().getLessonPlanList().get(i).getLessonPlanTitle().equals(selectedItem.getValue())) {
                        LessonPlan selectedLessonPlan = course.getTheCourse().getLessonPlanList().get(i);
                        course.getTheCourse().removePlanFromCourse(selectedLessonPlan);
                        TreeItem<String> removeTree = new TreeItem<>();
                        removeTree.setValue(selectedLessonPlan.getLessonPlanTitle());
                        rootItem.getChildren().remove(removeTree);
                    }
                }
                undoRedoHandler.saveState();
                initializeTreeView();
            } else{
                alert.close();
            }
        }
        treeView.getSelectionModel().clearSelection();
    }


    /**
     * Handles the "Load Template" button click
     *
     * @param event The action event
     */
    @FXML
    private void preMadeLessonButtonHandle(ActionEvent event){
        GymnasticsAppBeta.switchToTemplatePage();
    }

    /**
     * Handles the "Create New Lesson Plan" button click
     *
     * @param event The action event
     */
    @FXML
    private void createNewLessonPlanButtonHandle(ActionEvent event){
        MainEditDisplayController.clearTreeCardItems();
        MainEditDisplayController.events.clear();
        MainEditDisplayController.resetButtons();
        GymnasticsAppBeta.setLessonPlan(new LessonPlan());
        GymnasticsAppBeta.switchToMainEditDisplay();
        undoRedoHandler.saveState();
    }

    /**
     * Handles the "Reset Course" button click
     *
     * @param event The action event
     */
    @FXML
    private void resetCourseButtonHandle(ActionEvent event){
        Alert maxCardAlert = new Alert(Alert.AlertType.WARNING);
        maxCardAlert.setTitle("Caution");
        maxCardAlert.setHeaderText("This will FULLY RESET your course! ");
        ButtonType yesButton = new ButtonType("Yes, reset my course");
        ButtonType noButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        maxCardAlert.getButtonTypes().setAll(yesButton, noButton);
        Optional<ButtonType> newResult = maxCardAlert.showAndWait();

        if(newResult.isPresent()) {
            if (newResult.get() == noButton) {
                maxCardAlert.close();
            } else if (newResult.get() == yesButton) {
                course.getTheCourse().clearLessonPlanList();
                rootItem.getChildren().clear();
                initializeTreeView();
                undoRedoHandler.saveState();
            }
        }

    }

    /**
     * Handles the "Home" button click
     *
     * @param event The action event
     */
    @FXML
    private void homePageButtonHandle(ActionEvent event){
        MainEditDisplayController.clearTreeCardItems();
        MainEditDisplayController.events.clear();
        MainEditDisplayController.resetButtons();
        GymnasticsAppBeta.setLessonPlan(new LessonPlan());
        GymnasticsAppBeta.switchToHomePage();
    }

    /**
     * Handles the "Save Course" button click
     *
     * @param event The action event
     * @throws IOException If an I/O error occurs during saving
     */
    @FXML
    void saveCourseButtonHandle(ActionEvent event) throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Gym Plan Files (*.GymPlanFile)", "*.GymPlanFile");
        fileChooser.getExtensionFilters().add(extensionFilter);

        String location = saveLocation.getLastSavedLocationCourse();
        try {
            String userHome = System.getProperty("user.home");
            File desktopDirectory = new File(userHome, "Desktop");
            if (desktopDirectory.exists() && desktopDirectory.isDirectory()) {
                fileChooser.setInitialDirectory(desktopDirectory);
            } else {
                throw new IllegalArgumentException("Desktop directory is invalid");
            }
        } catch (Exception e) {
            // Fallback if `user.home` or `Desktop` isn't valid
            File defaultLocation = new File(".");
            fileChooser.setInitialDirectory(defaultLocation);
        }

        // Show the file save dialog and get the selected file.
        File selectedFile = fileChooser.showSaveDialog(null);


        if (selectedFile != null) {
            recentCourses.setCoursePreference(selectedFile.getAbsolutePath());
            saveLocation.setLastSavedLocationCourses(selectedFile.getAbsolutePath());
            // Create a FileWriter for the selected file and write the data.
            try (FileWriter fileWriter = new FileWriter(selectedFile)) {
                fileWriter.write(course.getTheCourse().getCourseName() + "\n");
                //iterate through each lessonPlan in the course
                for(int i = 0; i < course.getTheCourse().getLessonPlanList().size(); i++){
                    LessonPlan curPlan = course.getTheCourse().getLessonPlanList().get(i);
                    fileWriter.write(curPlan.getLessonPlanTitle() + "\n");
                    for(int j = 0; j < curPlan.getEventNames().size(); j++){
                        //writes event's name
                        fileWriter.write(curPlan.getEventNames().get(j) + "\n");
                        for(int cardIndex = 0; cardIndex < curPlan.getEventList().get(j).size(); cardIndex++){
                            fileWriter.write(curPlan.getEventList().get(j).get(cardIndex).getUniqueID() + "\n");
                        }
                        fileWriter.write("done with event" + "\n");
                    }
                    fileWriter.write("done with lessonplan" + "\n");
                }
                fileWriter.write("done with course" + "\n");
            } catch (IOException e) {
                // Handle the exception appropriately (e.g., show an error message).
                e.printStackTrace();
            }
        }
    }

    /**
     * Handles the "Load Course" button click
     *
     * @param event The action event
     */
    @FXML
    public void loadCourseButtonHandle(ActionEvent event){
        Alert maxCardAlert = new Alert(Alert.AlertType.WARNING);
        maxCardAlert.setTitle("Caution");
        maxCardAlert.setHeaderText("This will FULLY DELETE the course you are currently working on! ");
        ButtonType yesButton = new ButtonType("Load New Course and delete current one");
        ButtonType noButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        maxCardAlert.getButtonTypes().setAll(yesButton, noButton);
        Optional<ButtonType> newResult = maxCardAlert.showAndWait();
        if(newResult.isPresent()) {
            if (newResult.get() == noButton) {
                maxCardAlert.close();
            } else if (newResult.get() == yesButton) {
                GymnasticsAppBeta.callFileChooser();
                if(GymnasticsAppBeta.getUserClickedCancel() == false) {
                    course.getTheCourse().clearLessonPlanList();
                    ArrayList<String> loadedLessonPlan = GymnasticsAppBeta.readFile();
                    course.getTheCourse().loadCourseFromFile(loadedLessonPlan);
                    GymnasticsAppBeta.setCourse(course.getTheCourse());
                    GymnasticsAppBeta.switchToCourseEditPage();
                }
                GymnasticsAppBeta.setUserClickedCancel(false);
            }
        }
    }

    /**
     * Handles the change in the course name
     */
    @FXML
    private void changeCourseNameHandle(){
        Course.setCourseName(courseName.getText());
        undoRedoHandler.saveState();
    }

    /**
     * Handles the action when the "Print Course Page" button is clicked.
     * Switches the view to the print course page in the GymnasticsAppBeta application.
     */
    @FXML
    private void printCoursePageHandle(){
        GymnasticsAppBeta.switchToPrintCoursePage();
    }


    @FXML
    public void loadLessonPlanButtonHandle(ActionEvent event) {

        loadPlan = new LessonPlan();
        GymnasticsAppBeta.callFileChooser();
        if(GymnasticsAppBeta.getUserClickedCancel() == false) {

            ArrayList<String> loadedLessonPlan = GymnasticsAppBeta.readFile();
            loadPlan.loadPlanFromFile(loadedLessonPlan);
            GymnasticsAppBeta.setLessonPlan(loadPlan);
            GymnasticsAppBeta.getLessonPlan().printEverything();
            if (!(loadPlan.getEventList().isEmpty()) && GymnasticsAppBeta.getLoaded() == true) {
                GymnasticsAppBeta.switchToPreviewPage();
            }
        }
        GymnasticsAppBeta.setUserClickedCancel(false);
    }
    public static LessonPlan getloadPlan() {
        return loadPlan;
    }

}

