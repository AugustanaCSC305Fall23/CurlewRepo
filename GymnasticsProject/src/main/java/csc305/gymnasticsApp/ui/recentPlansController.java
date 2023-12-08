package csc305.gymnasticsApp.ui;

import csc305.gymnasticsApp.data.LessonPlan;
import csc305.gymnasticsApp.data.PrefPlans;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class recentPlansController {
    @FXML private Button recentPlan1;
    @FXML private Button recentPlan2;
    @FXML private Button recentPlan3;
    @FXML private Button recentCourse1;
    @FXML private Button recentCourse2;
    @FXML private Button recentCourse3;
    private PrefPlans recentLP = GymnasticsAppBeta.getRecentPlans();

    public void initialize() {
        setLessonPlanLabels();
    }

    private void setLessonPlanLabels() {
        File lp1 = new File(recentLP.getPlan1());
        recentPlan1.setText(lp1.getName());
        File lp2 = new File(recentLP.getPlan2());
        recentPlan2.setText(lp2.getName());
        File lp3 = new File(recentLP.getPlan3());
        recentPlan3.setText(lp3.getName());
        recentPlan1.setVisible(true);
        recentPlan2.setVisible(true);
        recentPlan3.setVisible(true);


        if(recentLP.getPlan3() == null){
            recentPlan3.setVisible(false);
        }else if(recentLP.getPlan2() == null){
            recentPlan2.setVisible(false);
        }else if(recentLP.getPlan1() == null){
            recentPlan1.setVisible(false);
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
        loadPlan.loadPlanFromFile(GymnasticsAppBeta.readLessonPlan());
        GymnasticsAppBeta.setLessonPlan(loadPlan);
        GymnasticsAppBeta.switchToPreviewPage();

    }

    @FXML
    void loadRecentCourse(){

    }

    @FXML
    void backToHomePage(){
        GymnasticsAppBeta.switchToHomePage();
    }

}
