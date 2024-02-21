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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Controller class for the single card page, responsible for displaying details of a selected card.
 */
public class SingleCardPageController {

    @FXML
    private ScrollPane mainScrollPane;
    @FXML
    private FlowPane mainFlowPane;
    @FXML
    private VBox mainVBox;

    /**
     * Initializes the single card page by displaying the image of the selected card.
     *
     * @throws FileNotFoundException If the image file of the selected card is not found.
     */
    @FXML
    public void initialize() throws FileNotFoundException {
        Card card = MainEditDisplayController.getCurrentSelectedCard().getCard();

        Image image = new Image(new FileInputStream("GymSoftwarePics/" + card.getPackFolder() + "/" + card.getImage()));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(595); // Set the width of the image view
        imageView.setFitHeight(595);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(imageView);

        // Apply the border style to the StackPane
        stackPane.setStyle("-fx-border-color: black;");

        mainVBox.setStyle("-fx-background-color: white");
        mainVBox.getChildren().add(stackPane);
    }

    /**
     * Handles the action when the back button is clicked.
     * Switches to the main edit display page.
     *
     * @param event The ActionEvent triggered by clicking the back button.
     */
    @FXML
    void backButtonHandle(ActionEvent event){
        GymnasticsAppBeta.switchToMainEditDisplay();
    }

    /**
     * Handles the action when the print button is clicked.
     * Prints the lesson plan displayed in the scroll pane.
     *
     * @param event The ActionEvent triggered by clicking the print button.
     */
    @FXML
    void printButtonHandle(ActionEvent event){
        Node lessonPlanNode = mainScrollPane;
        PrintLessonPlan.printPlan(lessonPlanNode, mainScrollPane, false);
    }

}
