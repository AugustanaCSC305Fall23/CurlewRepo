package csc305.gymnasticsApp.ui;

import csc305.gymnasticsApp.data.Course;
import csc305.gymnasticsApp.data.LessonPlan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
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
        GymnasticsAppBeta.switchToHomePage();    }
}

