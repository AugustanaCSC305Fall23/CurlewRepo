package csc305.gymnasticsApp.ui;

import csc305.gymnasticsApp.data.Card;
import csc305.gymnasticsApp.data.Course;
import csc305.gymnasticsApp.data.PrintLessonPlan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SingleCardPageController {

    @FXML
    private ScrollPane mainScrollPane;
    @FXML
    private FlowPane mainFlowPane;
    @FXML
    private VBox mainVBox;



    @FXML
    public void initialize() throws FileNotFoundException {
        Card card = MainEditDisplayController.getCurrentSelectedCard().getCard();

        Image image = new Image(new FileInputStream("GymSoftwarePics/" + card.getPackFolder() + "/" + card.getImage()));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(595); // Set the width of the image view
        imageView.setFitHeight(595);
        mainVBox.setStyle("-fx-background-color: white");
        mainVBox.getChildren().add(imageView);
    }

    @FXML
    void backButtonHandle(ActionEvent event){
        GymnasticsAppBeta.switchToMainEditDisplay();
    }

    @FXML
    void printButtonHandle(ActionEvent event){
        Node lessonPlanNode = mainScrollPane;
        PrintLessonPlan.printPlan(lessonPlanNode, mainScrollPane, false);
    }

}
