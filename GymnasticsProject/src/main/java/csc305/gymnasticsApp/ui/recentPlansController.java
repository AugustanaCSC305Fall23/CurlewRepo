package csc305.gymnasticsApp.ui;

import csc305.gymnasticsApp.data.Course;
import csc305.gymnasticsApp.data.LessonPlan;
import csc305.gymnasticsApp.data.PrefCourses;
import csc305.gymnasticsApp.data.PrefPlans;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.File;

public class recentPlansController {
    @FXML private Button recentPlan1;
    @FXML private Button recentPlan2;
    @FXML private Button recentPlan3;
    @FXML private Button recentCourse1;
    @FXML private Button recentCourse2;
    @FXML private Button recentCourse3;
    private PrefPlans recentLP = GymnasticsAppBeta.getRecentPlans();
    private PrefCourses recentCourses = GymnasticsAppBeta.getRecentCourses();

    public void initialize() {
        setLessonPlanButtons();
        setCourseButtons();
    }

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


    @FXML
   void openRecentLessonPlan(ActionEvent event){
        Button lessonButton = (Button) event.getSource();
        LessonPlan loadPlan = new LessonPlan();
        if(lessonButton == recentPlan1){
            GymnasticsAppBeta.callFileChooser(new File(recentLP.getPlan1()));
        } else if (lessonButton == recentPlan2) {
            GymnasticsAppBeta.callFileChooser(new File(recentLP.getPlan2()));
        }else{
            GymnasticsAppBeta.callFileChooser(new File(recentLP.getPlan3()));
        }
        loadPlan.loadPlanFromFile(GymnasticsAppBeta.readFile());
        GymnasticsAppBeta.setLessonPlan(loadPlan);
        GymnasticsAppBeta.switchToPreviewPage();

    }

    @FXML
    void loadRecentCourse(ActionEvent event){
        Button courseButton = (Button) event.getSource();
        Course loadCourse = new Course();
        if(courseButton == recentCourse1){
            GymnasticsAppBeta.callFileChooser(new File(recentCourses.getCourse1()));
        } else if (courseButton == recentCourse2) {
            GymnasticsAppBeta.callFileChooser(new File(recentCourses.getCourse2()));
        }else{
            GymnasticsAppBeta.callFileChooser(new File(recentCourses.getCourse3()));
        }
        loadCourse.loadCourseFromFile(GymnasticsAppBeta.readFile());
        GymnasticsAppBeta.setCourse(loadCourse);
        GymnasticsAppBeta.switchToCourseEditPage();
    }

    @FXML
    void backToHomePage(){
        GymnasticsAppBeta.switchToHomePage();
    }

}
