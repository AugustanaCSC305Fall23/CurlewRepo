package csc305.gymnasticsApp.ui;

import csc305.gymnasticsApp.data.Card;
import csc305.gymnasticsApp.data.Course;
import csc305.gymnasticsApp.data.LessonPlan;
import csc305.gymnasticsApp.data.PrintLessonPlan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PrintCoursePageController {

    private boolean showEquipment;
    private boolean showNotes;

    @FXML
    private VBox eventPreviewVBox;

    @FXML
    private TextField lessonPlanTextField;

    private List<TextField> eventTitles = new ArrayList<>();
    @FXML
    private FlowPane mainFlowPane;
    @FXML
    private List<VBox> VBoxes = new ArrayList<>();
    private VBox titleVBox;
    @FXML
    private ScrollPane mainScrollPane;
    @FXML
    private Button addPlanToCurrentCourseButton;

    @FXML
    private Button printButton;
    @FXML
    private Button backButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button hideEquipment;

    /**
     * Initializes the display for the main course page. Clears and resets panes, sets up a new VBox, and adds titles and content
     * for each lesson plan and its associated events. The display is organized within the main flow pane.
     *
     * @throws FileNotFoundException If there is an issue with locating or loading the required file.
     */
    public void initialize() throws FileNotFoundException {
        showEquipment = false;
        showNotes = false;
        mainFlowPane.getChildren().clear();
        VBoxes.clear();
        boolean createdTitle = false;
        initializeCoursePage();
        for(int h = 0; h < Course.getTheCourse().getLessonPlanList().size(); h++) {
            LessonPlan lessonPlan = Course.getTheCourse().getLessonPlanList().get(h);
            for (int i = 0; i < lessonPlan.getEventList().size(); i++) {
                //Checks if this is first event
                VBox currentVBox;
                if (!createdTitle) {
                    VBox nVBox = getNewVBox();
                    addTitleToVBox(nVBox, lessonPlan.getLessonPlanTitle());
                    createdTitle = true;
                    titleVBox = nVBox;
                    nVBox.setPadding(new javafx.geometry.Insets(20, 0, 0, 0));
                    currentVBox = nVBox;
                } else {
                    VBox nVBox = getNewVBox();
                    nVBox.setPadding(new javafx.geometry.Insets(30, 0, 0, 0));
                    currentVBox = nVBox;
                }
                fillVBox(currentVBox, i, 30, lessonPlan);

                mainFlowPane.getChildren().add(currentVBox);
                VBoxes.add(currentVBox);

            }
            createdTitle = false;
        }
        mainFlowPane.setAlignment(Pos.CENTER);

    }

    private void createCourseBox(){
//        VBox nVBox = getNewVBox();
//        addTitleToVBox(nVBox, lessonPlan.getLessonPlanTitle());
//        titleVBox = nVBox;
//        nVBox.setPadding(new javafx.geometry.Insets(20, 0, 0, 0));
//        fillVBox(nVBox, 30);
//
//        mainFlowPane.getChildren().add(currentVBox);
//        VBoxes.add(currentVBox);
    }

    /**
     * Creates and returns a new VBox with specific styling and dimensions.
     *
     * @return a new VBox with styling and dimensions.
     */
    private VBox getNewVBox(){
        VBox returnVBox = new VBox();
        returnVBox.setStyle("-fx-background-color: #CCCCCC;");
        returnVBox.setPrefSize(770.0, 595.0);
        returnVBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        returnVBox.setAlignment(Pos.TOP_CENTER);
        returnVBox.setFillWidth(false);
        returnVBox.setSpacing(10.0);
        return returnVBox;
    }

    /**
     * Fills a given VBox with event information, including the event title and associated cards.
     *
     * @param vbox      the VBox to be filled.
     * @param eventIndex the index of the event to be displayed.
     * @param flowGap   the gap between cards in the event.
     */
    private void fillVBox(VBox vbox, int eventIndex, double flowGap, LessonPlan lessonPlan){
        HBox eventTitleHBox = createEventTitleHBox();
        TextField eventTitleTextField = createEventTitleTextField(eventIndex, lessonPlan);
        FlowPane eventCards = createEventCardFlowPane();
        eventCards.getChildren().addAll(addCards(eventIndex, flowGap == 10, lessonPlan));
        eventCards.setHgap(flowGap);
        if(eventCards.getChildren().size() > 6){
            eventCards.setVgap(50);
            eventCards.setHgap(10);
        }
        eventTitleHBox.getChildren().add(eventTitleTextField);
        vbox.getChildren().addAll(eventTitleHBox, eventCards);

    }

    /**
     * Adds image cards to the VBox based on the specified event index.
     *
     * @param eventIndex   the index of the event.
     * @param isEquipment  a flag indicating whether the cards are equipment.
     * @return a list of ImageViews representing the cards.
     */
    private List<ImageView> addCards(int eventIndex, boolean isEquipment, LessonPlan lessonPlan){
        List<Card> eventCards = lessonPlan.getEventCards(eventIndex);
        List<ImageView> cardView = new ArrayList<>();
        for(Card card : eventCards){
            Image image = null;
            try {
                image = new Image(new FileInputStream("GymSoftwarePics/" +
                        card.getPackFolder() + "/" +
                        card.getImage()));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            ImageView imageView = new ImageView(image);
            if(eventCards.size() > 6){
                if(isEquipment) {
                    imageView.setFitWidth(150); // Set the width of the image view
                    imageView.setFitHeight(150); // Set the height of the image view
                } else{
                    imageView.setFitWidth(175); // Set the width of the image view
                    imageView.setFitHeight(175); // Set the height of the image view
                }
            } else {
                imageView.setFitWidth(200); // Set the width of the image view
                imageView.setFitHeight(200); // Set the height of the image view
            }
            cardView.add(imageView);
        }
        return cardView;
    }

    /**
     * Creates and returns a TextField for the event title.
     *
     * @param eventIndex the index of the event.
     * @return a TextField with specific styling and dimensions.
     */
    private TextField createEventTitleTextField(int eventIndex, LessonPlan lessonPlan){
        TextField eventTitleTextField = new TextField();
        eventTitleTextField.setAlignment(Pos.CENTER);
        eventTitleTextField.setPrefSize(250.0, 40.0);
        eventTitleTextField.setStyle("-fx-text-fill: black; -fx-background-color: white; -fx-background-radius: 10px;");
        eventTitleTextField.setFont(Font.font("System Bold", 16.0));
        eventTitleTextField.setText(lessonPlan.getEventNames().get(eventIndex));

        return eventTitleTextField;
    }

    /**
     * Creates and returns an HBox for the event title.
     *
     * @return an HBox with specific styling and dimensions.
     */
    private HBox createEventTitleHBox(){
        HBox eventTitleHBox = new HBox();
        eventTitleHBox.setAlignment(Pos.CENTER_LEFT);
        eventTitleHBox.setPrefSize(760.0, 40.0);
        eventTitleHBox.setSpacing(50.0);
        return eventTitleHBox;
    }

    /**
     * Creates and returns a FlowPane for displaying event cards.
     *
     * @return a FlowPane with specific styling and dimensions.
     */
    private FlowPane createEventCardFlowPane(){
        FlowPane eventFlowPane = new FlowPane();
        eventFlowPane.setPrefHeight(200.0);
        eventFlowPane.setPrefWidth(749.0);
        eventFlowPane.setHgap(40.0);
        eventFlowPane.setVgap(20.0);
        eventFlowPane.setAlignment(Pos.CENTER);
        eventFlowPane.setColumnHalignment(HPos.CENTER);
        return eventFlowPane;
    }


    /**
     * Adds the lesson plan title to a given VBox.
     *
     * @param nVBox the VBox to which the title is added.
     * @param title the title to be added.
     */
    private void addTitleToVBox(VBox nVBox, String title) {
        TextField lessonPlanTextField = new TextField();
        lessonPlanTextField.setAlignment(Pos.CENTER);
        lessonPlanTextField.setPrefSize(402.0, 50.0);
        lessonPlanTextField.setStyle("-fx-text-fill: black; -fx-background-color: white; -fx-background-radius: 10px;");
        lessonPlanTextField.setFont(Font.font("System Bold", 24.0));
        lessonPlanTextField.setText(title);
        nVBox.getChildren().add(lessonPlanTextField);
    }

    /**
     * Toggles the display of equipment content. If equipment is not currently shown, it adds the equipment content to the
     * display. If equipment is already shown, it removes the equipment content. In either case, it updates the display accordingly.
     *
     * @throws FileNotFoundException If there is an issue with locating or loading the required file.
     */
    private void fillEquipmentBox() throws FileNotFoundException {
        //if equipment isn't shown, add equipment. If equipment is shown, remove it
        if(showEquipment == false){
            showEquipment = true;
            initializeWithEquipment();
        }else if(showNotes == true) {
            showEquipment = false;
            initializeWithEquipment();
        } else{
            initialize();
        }
    }

    /**
     * Toggles the display of notes content. If notes are not currently shown, it adds the notes content to the display.
     * If notes are already shown, it removes the notes content. In either case, it updates the display accordingly.
     *
     * @throws FileNotFoundException If there is an issue with locating or loading the required file.
     */
    private void fillNotesBox() throws FileNotFoundException{
        if(showNotes == false){
            showNotes = true;
            initializeWithEquipment();
        }else if(showEquipment == true) {
            showNotes = false;
            initializeWithEquipment();
        } else{
            initialize();
        }
    }

    /**
     * Initializes the view with equipment display
     */
    public void initializeWithEquipment() {
        //clear and reset panes
        mainFlowPane.getChildren().clear();
        VBoxes.clear();
        initializeCoursePage();
        boolean isFirst = true;
        boolean createdTitle = false;
        for(int f = 0; f < Course.getTheCourse().getLessonPlanList().size(); f++){
            LessonPlan lessonPlan = Course.getTheCourse().getLessonPlanList().get(f);
            //iterates through each event and adds subsequent cards
            for (int i = 0; i < lessonPlan.getEventNames().size(); i++) {
                //Checks if this is first event
                VBox currentVBox;
                if (!createdTitle) {
                    VBox nVBox = getNewVBox();
                    addTitleToVBox(nVBox, lessonPlan.getLessonPlanTitle());
                    createdTitle = true;
                    titleVBox = nVBox;
                    nVBox.setPadding(new javafx.geometry.Insets(20, 0, 0, 0));
                    currentVBox = nVBox;
                } else {
                    VBox nVBox = getNewVBox();
                    nVBox.setPadding(new javafx.geometry.Insets(30, 0, 0, 0));
                    currentVBox = nVBox;
                }
                HBox nHBox = new HBox();
                nHBox.setAlignment(Pos.CENTER_LEFT);
                nHBox.setPrefSize(760.0, 535.0);
                nHBox.setSpacing(5);
                VBox eventVBox = new VBox();
                eventVBox.setAlignment(Pos.TOP_CENTER);
                eventVBox.setPrefSize(700, 535);
                eventVBox.setSpacing(10);
                fillVBox(eventVBox, i, 10, lessonPlan);

                nHBox.getChildren().add(eventVBox);


                String finalString = "";
                if(showEquipment == true) {
                    finalString = "Equipment:\n";
                    for (int j = 0; j < lessonPlan.getEventList().get(i).size(); j++) {
                        String[] equipmentList = lessonPlan.getEventList().get(i).get(j).getEquipment().split(", "); //gets each equipment item

                        for (int h = 0; h < equipmentList.length; h++) {
                            if (!(equipmentList[h].equalsIgnoreCase("none"))) {//checks if the equipment is none
                                if (!(finalString.contains(equipmentList[h]))) {
                                    finalString = finalString + "- " + equipmentList[h] + "\n";
                                }
                            }
                        }
                    }
                    finalString = finalString + "\n";
                }
                if(showNotes == true){
                    finalString = finalString + "Notes:\n";
                }

                TextArea equipmentBox = new TextArea();
                equipmentBox.setPrefWidth(150);
                if(showNotes == true){
                    equipmentBox.setPrefHeight(500);
                } else {
                    equipmentBox.setPrefHeight(300);
                }
                equipmentBox.setWrapText(true);
                equipmentBox.setText(finalString);
                equipmentBox.setStyle("-fx-font-size: 14;");
                equipmentBox.setStyle("-fx-font-weight: bold;");
                nHBox.getChildren().add(equipmentBox);
                nHBox.setFillHeight(false);

                currentVBox.getChildren().add(nHBox);
                mainFlowPane.getChildren().add(currentVBox);
                VBoxes.add(currentVBox);

            }
        }
        mainFlowPane.setAlignment(Pos.CENTER);
    }

    /**
     * Initializes the course page by creating a new VBox, setting up the course name display, and adding it to the main flow pane.
     * The course name is displayed in a TextField with specified styles and dimensions.
     */
    private void initializeCoursePage() {
        VBox nVBox = getNewVBox();
        nVBox.setAlignment(Pos.CENTER);
        TextField courseName = new TextField(Course.getCourseName());
        courseName.setStyle("-fx-font-size: 50; -fx-font-weight: bold; -fx-text-fill: white;-fx-background-color: #CCCCCC");
        courseName.setPrefSize(900, 50);
        courseName.setAlignment(Pos.CENTER);

        nVBox.getChildren().add(courseName);
        mainFlowPane.getChildren().add(nVBox);
        VBoxes.add(nVBox);
    }

    /**
     * Handles the action event when the print button is clicked. Prints the lesson plan displayed in the main scroll pane.
     *
     * @param event The ActionEvent triggered by clicking the print button.
     */
    @FXML
    void printButtonController(ActionEvent event) {
        Node lessonPlanNode = mainScrollPane;
        PrintLessonPlan.printPlan(lessonPlanNode, mainScrollPane, false);
    }

    /**
     * Handles the action event when the equipment bar button is clicked. Toggles the display of equipment content.
     *
     * @param event The ActionEvent triggered by clicking the equipment bar button.
     * @throws FileNotFoundException If there is an issue with locating or loading the required file.
     */
    @FXML
    void handleEquipmentBar(ActionEvent event) throws FileNotFoundException {
        fillEquipmentBox();
    }

    /**
     * Handles the action event when the notes box button is clicked. Toggles the display of notes content.
     *
     * @param event The ActionEvent triggered by clicking the notes box button.
     * @throws FileNotFoundException If there is an issue with locating or loading the required file.
     */
    @FXML
    void handleNotesBox(ActionEvent event) throws FileNotFoundException {
        fillNotesBox();
    }

    /**
     * Handles the action event when the home button is clicked. Clears tree card items, resets buttons, and switches to the home page.
     *
     * @param event The ActionEvent triggered by clicking the home button.
     */
    @FXML
    void homeButtonController(ActionEvent event) {
            MainEditDisplayController.clearTreeCardItems();
            MainEditDisplayController.events.clear();
            MainEditDisplayController.resetButtons();
            GymnasticsAppBeta.switchToHomePage();
    }

    /**
     * Handles the action event when the back button is clicked. Switches to the course edit page.
     *
     * @param event The ActionEvent triggered by clicking the back button.
     */
    @FXML
    private void backButtonController(ActionEvent event){
        GymnasticsAppBeta.switchToCourseEditPage();
    }



}
