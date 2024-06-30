package csc305.gymnasticsApp.ui;

import csc305.gymnasticsApp.data.Course;
import csc305.gymnasticsApp.data.LessonPlan;
import csc305.gymnasticsApp.data.PrefCourses;
import csc305.gymnasticsApp.data.PrefPlans;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.File;
/**
 * Controller class for the recent plans view, responsible for handling user interactions and managing recent lesson plans and courses.
 */
public class recentPlansController {
    @FXML private Button recentPlan1;
    @FXML private Button recentPlan2;
    @FXML private Button recentPlan3;
    @FXML private Button recentCourse1;
    @FXML private Button recentCourse2;
    @FXML private Button recentCourse3;
    private PrefPlans recentLP = GymnasticsAppBeta.getRecentPlans();
    private PrefCourses recentCourses = GymnasticsAppBeta.getRecentCourses();

    /**
     * Initializes the recent plans view by setting up the visibility and labels of recent lesson plans and courses.
     */
    public void initialize() {
        setLessonPlanButtons();
        setCourseButtons();
    }
    /**
     * Sets up the visibility and labels of recent course buttons based on the recentCourses object.
     */
    private void setCourseButtons() {
        recentCourse1.setVisible(false);
        recentCourse2.setVisible(false);
        recentCourse3.setVisible(false);
        if(!(recentCourses.getCourse1().equals(""))){
            File course1 = new File(recentCourses.getCourse1());
            recentCourse1.setText(course1.getName());
            recentCourse1.setVisible(true);
        }
        if(!(recentCourses.getCourse2().equals(""))) {
            File course2 = new File(recentCourses.getCourse2());
            recentCourse2.setText(course2.getName());
            recentCourse2.setVisible(true);
        }
        if(!(recentCourses.getCourse3().equals(""))){
            File course3 = new File(recentCourses.getCourse3());
            recentCourse3.setText(course3.getName());
            recentCourse3.setVisible(true);
        }

    }
    /**
     * Sets up the visibility and labels of recent lesson plan buttons based on the recentLP object.
     */
    private void setLessonPlanButtons() {
        recentPlan1.setVisible(false);
        recentPlan2.setVisible(false);
        recentPlan3.setVisible(false);

        if(!(recentLP.getPlan1().equals(""))){
            File lp1 = new File(recentLP.getPlan1());
            recentPlan1.setText(lp1.getName());
            recentPlan1.setVisible(true);
        }
        if(!(recentLP.getPlan2().equals(""))){
            File lp2 = new File(recentLP.getPlan2());
            recentPlan2.setText(lp2.getName());
            recentPlan2.setVisible(true);
        }
        if(!(recentLP.getPlan3().equals(""))){
            File lp3 = new File(recentLP.getPlan3());
            recentPlan3.setText(lp3.getName());
            recentPlan3.setVisible(true);
        }
    }

    /**
     * Opens the selected recent lesson plan by calling the file chooser and loading the plan.
     * Switches to the preview page after loading the plan.
     *
     * @param event The ActionEvent triggered by clicking the open recent lesson plan button.
     */
    @FXML
   void openRecentLessonPlan(ActionEvent event){
        Button lessonButton = (Button) event.getSource();
        LessonPlan loadPlan = new LessonPlan();
        if(lessonButton == recentPlan1){
            GymnasticsAppBeta.setSelectedFileBool(new File(recentLP.getPlan1()));
        } else if (lessonButton == recentPlan2) {
            GymnasticsAppBeta.setSelectedFileBool(new File(recentLP.getPlan2()));
        }else{
            GymnasticsAppBeta.setSelectedFileBool(new File(recentLP.getPlan3()));
        }
        loadPlan.loadPlanFromFile(GymnasticsAppBeta.readFile());
        GymnasticsAppBeta.setLessonPlan(loadPlan);
        GymnasticsAppBeta.switchToPreviewPage();

    }

    /**
     * Loads the selected recent course by calling the file chooser and loading the course.
     * Switches to the course edit page after loading the course.
     *
     * @param event The ActionEvent triggered by clicking the load recent course button.
     */
    @FXML
    void loadRecentCourse(ActionEvent event){
        Button courseButton = (Button) event.getSource();
        Course loadCourse = new Course();
        if(courseButton == recentCourse1){
            GymnasticsAppBeta.setSelectedFileBool(new File(recentCourses.getCourse1()));
        } else if (courseButton == recentCourse2) {
            GymnasticsAppBeta.setSelectedFileBool(new File(recentCourses.getCourse2()));
        }else{
            GymnasticsAppBeta.setSelectedFileBool(new File(recentCourses.getCourse3()));
        }
        loadCourse.loadCourseFromFile(GymnasticsAppBeta.readFile());
        GymnasticsAppBeta.setCourse(loadCourse);
        GymnasticsAppBeta.switchToCourseEditPage();
    }

    /**
     * Switches to the home page.
     */
    @FXML
    void backToHomePage(){
        GymnasticsAppBeta.switchToHomePage();
    }

}
