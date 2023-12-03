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

    public TreeItem<String> rootItem = new TreeItem<>("Root");


    @FXML
    public void initialize() {
        System.out.println("Initializing Course Edit Page");
        initializeTreeView();

    }

    public void initializeTreeView(){
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
            MainEditDisplayController.clearTreeCardItems();
            MainEditDisplayController.events.clear();
            MainEditDisplayController.resetButtons();

            for(int i = 0; i < Course.getLessonPlanList().size(); i++) {
                if(Course.getLessonPlanList().get(i).getLessonPlanTitle().equals(selectedItem.getValue())) {
                    LessonPlan selectedLessonPlan = Course.getLessonPlanList().get(i);
                    GymnasticsAppBeta.setLessonPlan(selectedLessonPlan);
                    GymnasticsAppBeta.switchToMainEditDisplay();
                    MainEditDisplayController.addTreeCardItems(selectedLessonPlan);

                } else {
                    System.out.println("Could not find LessonPlan in the Course");
                }
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
                    for(int j = 0; j < curPlan.getEventList().size(); j++){
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
}

