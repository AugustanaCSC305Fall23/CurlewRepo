package csc305.gymnasticsApp.ui;


import csc305.gymnasticsApp.data.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Flow;

/**
 * Controller class for the preview page, responsible for displaying lesson plans
 */
public class PreviewPageController {

    private boolean showEquipment;
    private boolean showNotes;
    private boolean isTextOnlyShowing;
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

    private LessonPlan lessonPlan;
    private Course course;

    private PrefPlans recents = GymnasticsAppBeta.getRecentPlans();

    private PrefFileLocation saveLocation = GymnasticsAppBeta.getSavedLocation();

    /**
     * Initializes the preview page
     *
     * @throws FileNotFoundException if files are not found
     */
    public void initialize() throws FileNotFoundException {
        showEquipment = false;
        showNotes = false;
        isTextOnlyShowing = false;
        lessonPlan = GymnasticsAppBeta.getLessonPlan();
        course = GymnasticsAppBeta.getCourse();
        //clear and reset panes
        mainFlowPane.getChildren().clear();
        VBoxes.clear();
        boolean isFirst = true;
        boolean createdTitle = false;

        //iterates through each event and adds subsequent cards
        for(int i = 0; i < lessonPlan.getEventNames().size(); i++){
            //Checks if this is first event
            VBox currentVBox;
            if(!createdTitle) {
                VBox nVBox = getNewVBox();
                addTitleToVBox(nVBox, lessonPlan.getLessonPlanTitle());
                createdTitle = true;
                titleVBox = nVBox;
                nVBox.setPadding(new javafx.geometry.Insets(20, 0, 0, 0));
                currentVBox = nVBox;
            } else{
                VBox nVBox = getNewVBox();
                nVBox.setPadding(new javafx.geometry.Insets(30, 0, 0, 0));
                currentVBox = nVBox;
            }
            fillVBox(currentVBox, i, 30);

            mainFlowPane.getChildren().add(currentVBox);
            VBoxes.add(currentVBox);

        }
        mainFlowPane.setAlignment(Pos.CENTER);
    }

    /**
     * Creates and returns a new VBox with specific styling and dimensions.
     *
     * @return a new VBox with styling and dimensions.
     */
    private VBox getNewVBox(){
        VBox returnVBox = new VBox();
        returnVBox.setStyle("-fx-background-color: white; -fx-border-color: black;");
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
    private void fillVBox(VBox vbox, int eventIndex, double flowGap){
        HBox eventTitleHBox = createEventTitleHBox();
        TextField eventTitleTextField = createEventTitleTextField(eventIndex);
        FlowPane eventCards = createEventCardFlowPane();
        eventCards.getChildren().addAll(addCards(eventIndex, flowGap == 10));
        eventCards.setHgap(5);
        if(eventCards.getChildren().size() > 6){
            eventCards.setVgap(5);
            eventCards.setHgap(5);
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
    private List<StackPane> addCards(int eventIndex, boolean isEquipment) {
        List<Card> eventCards = lessonPlan.getEventCards(eventIndex);
        List<StackPane> cardViews = new ArrayList<>();

        for (Card card : eventCards) {
            Image image = null;
            try {
                image = new Image(new FileInputStream("GymSoftwarePics/" +
                        card.getPackFolder() + "/" +
                        card.getImage()));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            ImageView imageView = new ImageView(image);
            imageView.setPreserveRatio(true);  //Allows us to only have to set one size of the image

            if (eventCards.size() > 6) {
                imageView.setFitWidth(185);
            } else if(eventCards.size() > 4) {
                if (isEquipment) {
                    imageView.setFitWidth(205);
                } else {
                    imageView.setFitWidth(235);
                }
            } else{
                imageView.setFitWidth(275);

            }

            // Create a StackPane to contain the ImageView
            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(imageView);

            // Apply the border style to the StackPane
            stackPane.setStyle("-fx-border-color: black;");

            cardViews.add(stackPane);
        }

        return cardViews;
    }

    /**
     * Creates and returns a TextField for the event title.
     *
     * @param eventIndex the index of the event.
     * @return a TextField with specific styling and dimensions.
     */
    private TextField createEventTitleTextField(int eventIndex){
        TextField eventTitleTextField = new TextField();
        eventTitleTextField.setAlignment(Pos.CENTER);
        eventTitleTextField.setPrefSize(250.0, 40.0);
        eventTitleTextField.setStyle("-fx-text-fill: black; -fx-border-color: black;-fx-border-radius: 10px;-fx-background-radius: 10px;");
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
        lessonPlanTextField.setStyle("-fx-text-fill: black; -fx-border-color: black;-fx-border-radius: 10px;-fx-background-radius: 10px;");
        lessonPlanTextField.setFont(Font.font("System Bold", 24.0));
        lessonPlanTextField.setText(title);
        nVBox.getChildren().add(lessonPlanTextField);
    }



    /**
     * Switches the application to the main edit display view.
     *
     * @param event the triggering event.
     */
    @FXML
    void editButtonController(ActionEvent event) {
        GymnasticsAppBeta.switchToMainEditDisplay();
    }

    /**
     * Handles the action event for filling the equipment box
     *
     * @param event The action event triggered by the button
     * @throws FileNotFoundException If the equipment file is not found
     */
    @FXML
    void handleEquipmentBar(ActionEvent event) throws FileNotFoundException {
        fillEquipmentBox();
    }

    /**
     * Handles the action event triggered when the user interacts with the "Notes Box."
     *
     * @param event The ActionEvent triggered by user interaction with the "Notes Box."
     * @throws FileNotFoundException If there is an issue with locating or loading the required file.
     */
    @FXML
    void handleNotesBox(ActionEvent event) throws FileNotFoundException {
        fillNotesBox();
    }
    /**
     * Handles the action event for going back to the home page
     *
     * @param event The action event triggered by the button
     */
    @FXML
    void homeButtonController(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Caution");
        alert.setHeaderText("Are you sure you want to exit to the home page? Any unsaved lessons will be lost.");
        alert.setContentText("Please select an option.");
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == yesButton) {
            MainEditDisplayController.clearTreeCardItems();
            MainEditDisplayController.events.clear();
            MainEditDisplayController.resetButtons();
            lessonPlan = new LessonPlan();
            GymnasticsAppBeta.setLessonPlan(lessonPlan);
            GymnasticsAppBeta.switchToHomePage();
        }

    }

    /**
     * Handles the action event for printing the lesson plan
     *
     * @param event The action event triggered by the button
     */
    @FXML
    void printButtonController(ActionEvent event) {
        Node lessonPlanNode = mainScrollPane;
        if(VBoxes.get(0).getPrefHeight() == 700){
            PrintLessonPlan.printPlan(lessonPlanNode, mainScrollPane, true);
        } else {
            PrintLessonPlan.printPlan(lessonPlanNode, mainScrollPane, false);
        }
    }

    /**
     * Handles the action event for switching to the template page
     *
     * @param event The action event triggered by the button
     */
    @FXML
    void handleTemplateButton(ActionEvent event){
        GymnasticsAppBeta.switchToTemplatePage();
    }

    /**
     * Handles the action event for saving the lesson plan
     *
     * @param event The action event triggered by the button
     * @throws IOException If an I/O error occurs during the saving process
     */
    @FXML
    void saveController(ActionEvent event) throws IOException {
        lessonPlan.printEverything();
        List<List<Card>> eventCardList = new ArrayList<>();
        for(int i =0; i < lessonPlan.getEventList().size(); i++){
            List<Card> cardList = new ArrayList<>();
            cardList.addAll(lessonPlan.getEventCards(i));
            eventCardList.add(cardList);
        }
        String location = saveLocation.getLastSavedLocationLP();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Gym Plan Files (*.GymPlanFile)", "*.GymPlanFile");
        fileChooser.getExtensionFilters().add(extensionFilter);
        // Set initial directory
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
            recents.setPreference(selectedFile.getAbsolutePath());
            saveLocation.setLastSavedLocationLP(selectedFile.getAbsolutePath());
            // Create a FileWriter for the selected file and write the data.
            try (FileWriter fileWriter = new FileWriter(selectedFile)) {
                fileWriter.write(lessonPlan.getLessonPlanTitle() + "\n");

                //NEED A FOR LOOP FOR EVENT TITLES

                for(int h = 0; h < lessonPlan.getEventList().size(); h++) {
                    fileWriter.write(lessonPlan.getEventNames().get(h) + "\n");
                    for (int i = 0; i < lessonPlan.getEventList().get(h).size(); i++) {
                        fileWriter.write(lessonPlan.getEventList().get(h).get(i).getUniqueID() + "\n");
                    }
                    fileWriter.write("end\n");
                }
            } catch (IOException e) {
                // Handle the exception appropriately (e.g., show an error message).
                e.printStackTrace();
            }
        }
    }

    /**
     * Sets the lesson plan titles for display
     */
    private void setLessonPlanTitles() {
        lessonPlanTextField.setText(lessonPlan.getLessonPlanTitle());
        for(int i = 0; i < lessonPlan.getEventNames().size(); i++ ) {
            eventTitles.add(new TextField());
            eventTitles.get(i).setText(lessonPlan.getEventNames().get(i));
        }
    }

    /**
     * Fills the equipment box based on the current state (shown/hidden)
     *
     * @throws FileNotFoundException if equipment images are not found
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
     * Fills or updates the content of the "Notes Box" based on the current state of display options.
     * If the "Notes Box" is not currently displayed, it sets the display state to show notes and initializes with equipment.
     * If the "Equipment Box" is currently displayed, it switches to displaying notes and initializes with equipment.
     * Otherwise, it initializes the "Notes Box" without equipment.
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
        boolean isFirst = true;
        boolean createdTitle = false;
        isTextOnlyShowing = false;

        //iterates through each event and adds subsequent cards
        for(int i = 0; i < lessonPlan.getEventNames().size(); i++){
            //Checks if this is first event
            VBox currentVBox;
            if(!createdTitle) {
                VBox nVBox = getNewVBox();
                addTitleToVBox(nVBox, lessonPlan.getLessonPlanTitle());
                createdTitle = true;
                titleVBox = nVBox;
                nVBox.setPadding(new javafx.geometry.Insets(20, 0, 0, 0));
                currentVBox = nVBox;
            } else{
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
            fillVBox(eventVBox, i, 10);

            nHBox.getChildren().add(eventVBox);


            String finalString = "";
            if(showEquipment == true) {
                finalString = "Equipment:\n";
                for (int j = 0; j < lessonPlan.getEventList().get(i).size(); j++) {
                    String[] equipmentList = lessonPlan.getEventList().get(i).get(j).getEquipment().split(", "); //gets each equipment item

                    for (int h = 0; h < equipmentList.length; h++) {
                        if (!(equipmentList[h].equalsIgnoreCase("none"))) {//checks if the equipment is none
                            if (!(finalString.contains(equipmentList[h]))) {
                                finalString = finalString + "• " + equipmentList[h] + "\n";
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
                equipmentBox.setPrefHeight(350);
            }
            equipmentBox.setWrapText(true);
            equipmentBox.setText(finalString);
            equipmentBox.setStyle("-fx-font-size: 12;-fx-font-weight: bold;-fx-border-color: black;");
            nHBox.getChildren().add(equipmentBox);
            nHBox.setFillHeight(false);

            currentVBox.getChildren().add(nHBox);
            mainFlowPane.getChildren().add(currentVBox);
            VBoxes.add(currentVBox);

        }
        mainFlowPane.setAlignment(Pos.CENTER);
    }

    /**
     * Handles the action when the "Text Mode" option is selected. If text-only mode is not currently showing,
     * it switches to text-only mode and initializes the display. Otherwise, it switches back to the regular display.
     *
     * @param event The ActionEvent triggered by selecting the "Text Mode" option.
     * @throws FileNotFoundException If there is an issue with locating or loading the required file.
     */
    @FXML
    private void textModeHandle(ActionEvent event) throws FileNotFoundException {
        if(isTextOnlyShowing == false) {
            isTextOnlyShowing = true;
            initializeTextOnly();
        }
        else{
            isTextOnlyShowing = false;
            initialize();
        }
    }

    /**
     * Initializes the display in text-only mode. Clears and resets panes, sets up a new VBox, and adds a TextArea
     * containing the entire lesson plan as text, including a list of all equipment used. The display is then added
     * to the main flow pane.
     *
     * @throws FileNotFoundException If there is an issue with locating or loading the required file.
     */
    private void initializeTextOnly() {
        showEquipment = false;
        showNotes = false;
        lessonPlan = GymnasticsAppBeta.getLessonPlan();
        course = GymnasticsAppBeta.getCourse();

        // Clear and reset panes
        mainFlowPane.getChildren().clear();
        VBoxes.clear();
        mainFlowPane.setHgap(100);
        // Constants for the VBox dimensions
        final double PAGE_WIDTH = 595;
        final double PAGE_HEIGHT = 700;
        final int MAX_LINES_PER_PAGE = 39;

        // Gather initial text and equipment information
        String lessonPlanText = lessonPlan.getEntirePlanAsText();
        String finalString = "All Equipment:\n";
        boolean isFirstEquipment = true;

        // Add equipment details to the final string
        for (int i = 0; i < lessonPlan.getEventNames().size(); i++) {
            for (int j = 0; j < lessonPlan.getEventList().get(i).size(); j++) {
                String[] equipmentList = lessonPlan.getEventList().get(i).get(j).getEquipment().split(", ");

                for (String equipment : equipmentList) {
                    if (!equipment.equalsIgnoreCase("none") && !finalString.contains(equipment)) {
                        if (isFirstEquipment) {
                            finalString += equipment;
                            isFirstEquipment = false;
                        } else {
                            finalString += ", " + equipment;
                        }
                    }
                }
            }
        }

        // Complete text to be paginated
        String completeText = lessonPlanText + "\n" + finalString;

        // Split the text into pages
        List<String> pages = splitTextIntoPages(completeText, MAX_LINES_PER_PAGE);

        // Create and add VBoxes for each page
        for (String pageText : pages) {
            VBox nVBox = new VBox();
            nVBox.setPrefSize(PAGE_WIDTH, PAGE_HEIGHT); // Correct width and height
            nVBox.setAlignment(Pos.TOP_LEFT); // Left-aligned text

            TextArea textArea = new TextArea(pageText);
            textArea.setPrefSize(PAGE_WIDTH, PAGE_HEIGHT); // Consistent size
            textArea.setWrapText(true); // Ensure text wraps
            textArea.setEditable(false); // Non-editable
            textArea.setStyle("-fx-font-size: 12; -fx-font-weight: bold; -fx-background-color: white; -fx-border-color: white;");

            nVBox.getChildren().add(textArea); // Add TextArea to VBox
            mainFlowPane.getChildren().add(nVBox); // Add VBox to FlowPane
            VBoxes.add(nVBox); // Track VBoxes
        }

        // Center alignment for the FlowPane
        mainFlowPane.setAlignment(Pos.CENTER);
    }

    // Function to split text into pages with a maximum line limit
    private List<String> splitTextIntoPages(String text, final int MAX_LINES) {
        List<String> pages = new ArrayList<>();
        StringBuilder currentPage = new StringBuilder();
        String[] lines = text.split("\n");
        int lineCount = 0;

        for (String line : lines) {
            if (lineCount >= MAX_LINES) {
                pages.add(currentPage.toString()); // Add the current page
                currentPage.setLength(0); // Clear current page
                lineCount = 0; // Reset the line count
            }

            currentPage.append(line).append("\n");
            lineCount++; // Increment the line count
        }

        if (currentPage.length() > 0) {
            pages.add(currentPage.toString()); // Add any remaining text
        }

        return pages;
    }


    // Helper function to calculate text height
    private double calculateTextHeight(String text) {
        Text textNode = new Text(text);
        textNode.setStyle("-fx-font-size: 12;");
        return textNode.getBoundsInLocal().getHeight();
    }

    /**
     * Handles the action event for adding the current lesson plan to the current course
     *
     * @param event The action event triggered by the button
     */
    @FXML
    private void addPlanToCurrentCourseButtonHandle(ActionEvent event){
        if(!(course.getLessonPlanList().contains(GymnasticsAppBeta.getLessonPlan()))) {
            course.addPlanToCourse(GymnasticsAppBeta.getLessonPlan());
        }
        GymnasticsAppBeta.switchToCourseEditPage();
    }
}