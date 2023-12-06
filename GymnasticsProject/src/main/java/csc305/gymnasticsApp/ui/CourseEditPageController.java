package csc305.gymnasticsApp.ui;

import csc305.gymnasticsApp.data.Course;
import csc305.gymnasticsApp.data.LessonPlan;
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

public class CourseEditPageController {
    @FXML
    private TreeView treeView;
    @FXML
    private TextField courseName;
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

    public TreeItem<String> rootItem = new TreeItem<>("Root");


    @FXML
    public void initialize() {
        System.out.println("Initializing Course Edit Page");
        initializeTreeView();

    }

    public void initializeTreeView(){
        rootItem.getChildren().clear();
        if (rootItem.getChildren().isEmpty()) {
            if(Course.getLessonPlanList().isEmpty()){
                System.out.println("COURSE DOESNT HAVE LESSON PLANS");
            } else {
                for(int i = 0; i < Course.getLessonPlanList().size(); i++) {
                    TreeItem<String> lesson = new TreeItem<>();
                    lesson.setValue(Course.getLessonPlanList().get(i).getLessonPlanTitle());
                    rootItem.getChildren().add(lesson);
                    System.out.println(lesson.toString());
                }
            }
        }
        treeView.setShowRoot(false);
        treeView.setRoot(rootItem);
    }

    public void selectItem(MouseEvent event){
        TreeItem<String> selectedItem = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Caution");
            alert.setHeaderText("Would you like to edit or delete this LessonPlan?");
            alert.setContentText("Please select an option.");
            ButtonType editButton = new ButtonType("Edit");
            ButtonType deleteButton = new ButtonType("Delete");
            alert.getButtonTypes().setAll(editButton, deleteButton);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == editButton) {

                MainEditDisplayController.clearTreeCardItems();
                MainEditDisplayController.events.clear();
                MainEditDisplayController.resetButtons();

                for (int i = 0; i < Course.getLessonPlanList().size(); i++) {
                    if (Course.getLessonPlanList().get(i).getLessonPlanTitle().equals(selectedItem.getValue())) {
                        LessonPlan selectedLessonPlan = Course.getLessonPlanList().get(i);
                        GymnasticsAppBeta.setLessonPlan(selectedLessonPlan);
                        GymnasticsAppBeta.switchToMainEditDisplay();
                        MainEditDisplayController.addTreeCardItems(selectedLessonPlan);

                    } else {
                        System.out.println("Could not find LessonPlan in the Course");
                    }
                }
            }
            if(result.get() == deleteButton){
                for (int i = 0; i < Course.getLessonPlanList().size(); i++) {
                    if (Course.getLessonPlanList().get(i).getLessonPlanTitle().equals(selectedItem.getValue())) {
                        LessonPlan selectedLessonPlan = Course.getLessonPlanList().get(i);
                        Course.removePlanFromCourse(selectedLessonPlan);
                        TreeItem<String> removeTree = new TreeItem<>();
                        removeTree.setValue(selectedLessonPlan.getLessonPlanTitle());
                        rootItem.getChildren().remove(removeTree);
                    }
                }
                initializeTreeView();
            }
        }
        treeView.getSelectionModel().clearSelection();
    }



    @FXML
    private void preMadeLessonButtonHandle(ActionEvent event){
        GymnasticsAppBeta.switchToTemplatePage();
    }
    @FXML
    private void createNewLessonPlanButtonHandle(ActionEvent event){
        MainEditDisplayController.clearTreeCardItems();
        MainEditDisplayController.events.clear();
        MainEditDisplayController.resetButtons();
        GymnasticsAppBeta.setLessonPlan(new LessonPlan());
        GymnasticsAppBeta.switchToMainEditDisplay();
    }

    @FXML
    private void resetCourseButtonHandle(ActionEvent event){
        Course.clearLessonPlanList();
        rootItem.getChildren().clear();
        initializeTreeView();
    }
    @FXML
    private void homePageButtonHandle(ActionEvent event){
        MainEditDisplayController.clearTreeCardItems();
        MainEditDisplayController.events.clear();
        MainEditDisplayController.resetButtons();
        GymnasticsAppBeta.setLessonPlan(new LessonPlan());
        GymnasticsAppBeta.switchToHomePage();
    }

    @FXML
    void saveCourseButtonHandle(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Gym Plan Files (*.GymPlanFile)", "*.GymPlanFile");
        fileChooser.getExtensionFilters().add(extensionFilter);

        // Show the file save dialog and get the selected file.
        File selectedFile = fileChooser.showSaveDialog(null);

        if (selectedFile != null) {
            // Create a FileWriter for the selected file and write the data.
            try (FileWriter fileWriter = new FileWriter(selectedFile)) {
                fileWriter.write(Course.getCourseName() + "\n");
                //iterate through each lessonPlan in the course
                for(int i = 0; i < Course.getLessonPlanList().size(); i++){
                    LessonPlan curPlan = Course.getLessonPlanList().get(i);
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


    //MAKE AN ALERT
    @FXML
    public void loadCourseButtonHandle(ActionEvent event){
        Alert maxCardAlert = new Alert(Alert.AlertType.WARNING);
        maxCardAlert.setTitle("Caution");
        maxCardAlert.setHeaderText("This will FULLY DELETE the course you are currently working on! ");
        ButtonType yesButton = new ButtonType("Load New Course and delete current one");
        ButtonType noButton = new ButtonType("Cancel");
        maxCardAlert.getButtonTypes().setAll(yesButton, noButton);
        Optional<ButtonType> newResult = maxCardAlert.showAndWait();
        if(newResult.isPresent()) {
            if (newResult.get() == noButton) {
                maxCardAlert.close();
            } else if (newResult.get() == yesButton) {
                GymnasticsAppBeta.callFileChooser();
                if(GymnasticsAppBeta.getUserClickedCancel() == false) {
                    Course.clearLessonPlanList();
                    ArrayList<String> loadedLessonPlan = GymnasticsAppBeta.readLessonPlan();
                    Course.loadEverythingFromFile(loadedLessonPlan);
                    GymnasticsAppBeta.switchToCourseEditPage();
                }
                GymnasticsAppBeta.setUserClickedCancel(false);
            }
        }
    }
}

