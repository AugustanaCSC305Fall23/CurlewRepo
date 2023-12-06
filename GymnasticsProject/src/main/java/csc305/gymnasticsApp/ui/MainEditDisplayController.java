package csc305.gymnasticsApp.ui;

import csc305.gymnasticsApp.filters.*;
import csc305.gymnasticsApp.data.Card;
import csc305.gymnasticsApp.data.CardDatabase;
import csc305.gymnasticsApp.data.LessonPlan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

//import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;

/**
 * The MainEditDisplayController class is responsible for handling user interactions and events on the main edit display page of the application.
 */
public class MainEditDisplayController implements Initializable {

    @FXML
    private TextField lessonTitle;
    @FXML
    private VBox filterMenu;
    @FXML
    private TextField searchBar;
    @FXML
    private FlowPane cardFlowPane;
    @FXML
    private TreeView treeView;
    @FXML public VBox filterVBox;
    @FXML private TextField equipmentTextfield;

    @FXML
    private ChoiceBox<String> genderCB;
    @FXML
    private ChoiceBox<String> modelGenderCB;
    @FXML
    private ChoiceBox<String> eventCB;
    @FXML public CheckBox levelABCheckBox;
    @FXML public CheckBox levelAdvancedCheckBox;
    @FXML public CheckBox levelBeginnerCheckBox;
    @FXML public CheckBox levelIntermediateCheckBox;
    private LevelFilter levelFilter = new LevelFilter();
    private EquipmentFilter equipmentFilter = new EquipmentFilter();
    private List<CardFilter> filterList = new ArrayList<>();
    public static TreeItem<String> rootItem = new TreeItem<>("Root");


    public static List<TreeItem<String>> events = new ArrayList<>();
    private static final List<CardButton> currentFilteredCards = new ArrayList<>();
    private static List<CardButton> allCards = new ArrayList<>();

    public static boolean isInitialized = false;

    public static LessonPlan lessonPlan;
    private static List<ButtonType> eventButtonList = new ArrayList<>();

    /**
     * Initializes the tree view on the main edit display screen.
     *
     * @param arg0 The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param arg1 The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lessonPlan = GymnasticsAppBeta.getLessonPlan();
        if(lessonPlan.getEventNames().isEmpty()){
            lessonPlan.addEventName("Event 1");
        }
        lessonPlan.printEverything();
        eventButtonList.clear();
        for(int i = 0; i < lessonPlan.getEventNames().size(); i++){
            eventButtonList.add(new ButtonType(lessonPlan.getEventNames().get(i)));
        }
        initializeTreeView();
        addCardsToFlowPane();
        allCards = getAllCardButtons();
        resetFlowPane();
        initFilterList();
        List<Card> eventCards = new ArrayList<>();
        lessonPlan.addToEventList(eventCards);
        System.out.println("initializing");
        lessonTitle.setText(GymnasticsAppBeta.getLessonPlan().getLessonPlanTitle());

    }

    private void initializeTreeView(){
        if (rootItem.getChildren().isEmpty()) {
            if(events.isEmpty()){
                events.add(new TreeItem<String>("Event 1"));
                if(eventButtonList.isEmpty()) {
                    eventButtonList.add(createEventButton(1));
                }
                System.out.println(eventButtonList.size());
            }
            for(TreeItem<String> event: events){
                rootItem.getChildren().add(event);
            }
        }
        treeView.setShowRoot(false);
        treeView.setRoot(rootItem);
    }

    public void createCardButtons(){
        if(!isInitialized) {
            allCards.clear();
            for (Card card : CardDatabase.getAllCards()) {
                CardButton cardButton = new CardButton(card);
                cardButton.setOnAction(event -> addCardToTreeView(cardButton));
                allCards.add(cardButton);
            }
            isInitialized = true;
        }
    }


    //Need to reset currentFilteredCards at some point
    /**
     * Adds cards to the FlowPane for display.
     */
    public void addCardsToFlowPane(){
        cardFlowPane.getChildren().clear();
        cardFlowPane.getChildren().addAll(allCards);
    }

    public void resetFlowPane(){
        cardFlowPane.getChildren().clear();
        filterCardsByCheckbox();
        equipmentTextfield.clear();
        equipmentTextfield.setPromptText("Equipment Keyword");

    }

    private void initFilterList(){
//        genderFilter.getDesiredGenders().clear();
//        filterList.add(genderFilter);
//        modelGenderFilter.getSelectedModelGender().clear();
//        filterList.add(modelGenderFilter);
//        eventFilter.reset();
//        filterList.add(eventFilter);
//        levelFilter.reset();
//        filterList.add(levelFilter);
//        equipmentFilter.reset();
//        filterList.add(equipmentFilter);
//        searchBarFilter.reset();
//        filterList.add(searchBarFilter);
        genderCB.getItems().addAll(CardDatabase.getGenderList());
        genderCB.setValue("N");
        modelGenderCB.getItems().add("ALL");
        modelGenderCB.getItems().addAll(CardDatabase.getModelGenderList());
        modelGenderCB.setValue("ALL");
        eventCB.setValue("ALL");
        eventCB.getItems().addAll(CardDatabase.getEventList());

        genderCB.valueProperty().addListener((obs, oldVal, newVal) -> updateFilteredVisibleCards());
        modelGenderCB.valueProperty().addListener((obs, oldVal, newVal) -> updateFilteredVisibleCards());
        eventCB.valueProperty().addListener((obs, oldVal, newVal) -> updateFilteredVisibleCards());

        searchBar.textProperty().addListener((obs, oldVal, newVal) -> updateFilteredVisibleCards());

    }

    void updateFilteredVisibleCards() {
        CardFilter genderFilter = new GenderFilter(genderCB.getValue());
        CardFilter modelGenderFilter = new ModelGenderFilter(modelGenderCB.getValue());
        CardFilter eventFilter = new EventFilter(eventCB.getValue());

        CardFilter searchBarFilter = new SearchBarFilter(searchBar.getText());

        CardFilter combineAndFilter = new CombineAndFilter(genderFilter, modelGenderFilter, eventFilter, searchBarFilter);

        for (CardButton card : allCards) {
            boolean includeThisCard = combineAndFilter.matches(card.getCard());
            card.setVisible(includeThisCard);
            card.setManaged(includeThisCard);
        }
    }


    public static void addTreeCardItems(LessonPlan lessonPlan) {
        rootItem.getChildren().clear();
        List<TreeItem<String>> listOfNewEvents = new ArrayList<>();
        for(int i = 0; i < lessonPlan.getEventNames().size(); i++){
            String eventName = lessonPlan.getEventNames().get(i);
            TreeItem<String> newEvent = new TreeItem<String>();
            for (int j = 0; j < lessonPlan.getEventCards(i).size(); j++) {
                TreeItem<String> newCard = new TreeItem<String>(lessonPlan.getEventList().get(i).get(j).getTitle());
                newEvent.getChildren().add(newCard);
                newEvent.setValue(eventName);
            }
            listOfNewEvents.add(newEvent);
        }
        events.clear();
        events.addAll(listOfNewEvents);

        System.out.println(events);

        rootItem.getChildren().addAll(events);
    }

    public static void clearTreeCardItems() {
        rootItem.getChildren().clear();
        for(TreeItem<String> event: events){
            event.getChildren().clear();
        }
    }

    public void eventAdderHandle(){
        int eventNum = events.size() + 1;
        events.add(new TreeItem<>("Event " + eventNum));
        rootItem.getChildren().clear();
        rootItem.getChildren().addAll(events);
        List<Card> eventCards = new ArrayList<>();
        lessonPlan.addToEventList(eventCards);
        lessonPlan.addEventName("Event" + eventNum);
        ButtonType eventButton = createEventButton(eventNum);
        eventButtonList.add(eventButton);
    }

    private ButtonType createEventButton(int eventNum){
        ButtonType eventButton = new ButtonType("Event" + eventNum);
        return eventButton;
    }

    /**
     * Handles the action when the "Back" button is clicked, allowing the user to return to the lesson plan page.
     *
     * @param event - The ActionEvent triggered by clicking the "Back" button.
     */
    @FXML
    void backButtonHandle(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Caution");
        alert.setHeaderText("Are you sure you want to exit to the home page? Any unsaved lessons will be lost.");
        alert.setContentText("Please select an option.");
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            if(result.get() == yesButton) {
                MainEditDisplayController.clearTreeCardItems();
                MainEditDisplayController.events.clear();
                MainEditDisplayController.resetButtons();
                GymnasticsAppBeta.setLessonPlan(new LessonPlan());
                GymnasticsAppBeta.switchToHomePage();
            }
        }
    }

    /**
     * Handles the action when the "Preview" button is clicked, allowing the user to preview the lesson.
     *
     * @param event - The ActionEvent triggered by clicking the "Preview" button.
     */
    @FXML
    void previewButtonHandle(ActionEvent event) {
        if (lessonPlan.getLessonPlanTitle() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("Please add a Title before proceeding.");
            ButtonType ok = new ButtonType("Ok");
            alert.getButtonTypes().setAll(ok);
            alert.showAndWait();
        } else {
            GymnasticsAppBeta.switchToPreviewPage();
            lessonPlan.printEverything();
        }
    }

    @FXML
    void setLessonTitle(KeyEvent event) {
        String title = lessonTitle.getText();
        lessonPlan.setLessonPlanTitle(title);
    }


    /**
     * Handles the action when the filter menu is opened.
     *
     * @param event - The ActionEvent triggered when opening the filter menu.
     */
    @FXML
    void openFilterMenu(ActionEvent event){
        filterMenu.setVisible(true);
    }
    /**
     * Handles the action when the filter menu is closed.
     *
     * @param event - The ActionEvent triggered when closing the filter menu.
     */
    @FXML
    void closeFilterMenu(ActionEvent event){
        filterMenu.setVisible(false);
    }

    /* DELETE EVENTUALLY IF NOT NEEDED

    private CardButton createCardButton(Card card, Image image) {
        ImageView iv = new ImageView(image);
        iv.setFitHeight(198.0);
        iv.setFitWidth(198.0);
        CardButton cardButton = new CardButton("", iv, card);
        cardButton.setMinHeight(Double.MIN_VALUE);
        cardButton.setMinWidth(Double.MIN_VALUE);
        cardButton.setPrefHeight(200.0);
        cardButton.setPrefWidth(200.0);
        cardButton.setMnemonicParsing(false);
        cardButton.setStyle("-fx-border-color: black;");
        cardButton.setId(card.getUniqueID());
        cardButton.setOnMouseClicked(event -> addCardToTreeView(cardButton));
        return cardButton;
    }

     */

    /**
     * Retrieves all card buttons from the FlowPane.
     *
     * @return A list of all card buttons in the FlowPane.
     */
    private List<CardButton> getAllCardButtons() {
        List<CardButton> cardButtons = new ArrayList<>();
        for (int i = 0; i < cardFlowPane.getChildren().size(); i++) {
            if (cardFlowPane.getChildren().get(i) instanceof CardButton) {
                cardButtons.add((CardButton) cardFlowPane.getChildren().get(i));
            }
        }
        return cardButtons;
    }



    //DELETES CARD

    public void selectItem(MouseEvent event){
        TreeItem<String> selectedItem = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            TreeItem<String> parent = selectedItem.getParent();

            //checks if not parent (event)
            if (rootItem.getChildren().contains(parent)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Caution");
                alert.setHeaderText("Are you sure you want to delete this card?");
                alert.setContentText("Please select an option.");
                ButtonType yesButton = new ButtonType("Yes");
                ButtonType noButton = new ButtonType("No");
                alert.getButtonTypes().setAll(yesButton, noButton);
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == yesButton) {
                    int index = rootItem.getChildren().indexOf(parent);
                    int cardNum = parent.getChildren().indexOf(selectedItem);
                    lessonPlan.deleteFromEvent(index, cardNum);
                    deleteCardFromTreeView(event);
                }
            } else { //is event, so shows text box
                Alert choose = new Alert(Alert.AlertType.WARNING);
                choose.setTitle("Caution");
                choose.setHeaderText("Are you sure you want to delete this card?");
                choose.setContentText("Please select an option.");
                ButtonType renameButton = new ButtonType("Rename Event");
                ButtonType deleteButton = new ButtonType("Delete Event");
                ButtonType cancelButton = new ButtonType("Cancel");
                choose.getButtonTypes().setAll(renameButton, deleteButton, cancelButton);
                Optional<ButtonType> initialResult = choose.showAndWait();
                if(initialResult.isPresent()) {
                    if (initialResult.get() == renameButton) {
                        TextInputDialog renameDialog = new TextInputDialog();
                        renameDialog.setTitle("Rename " + selectedItem.getValue());
                        renameDialog.setHeaderText("What do you want to rename this event to?");
                        renameDialog.setContentText("New Event Name: ");

                        Optional<String> result = renameDialog.showAndWait();
                        result.ifPresent(newName -> {
                            int index = rootItem.getChildren().indexOf(selectedItem);
                            lessonPlan.setEventName(newName, index);
                            selectedItem.setValue(newName);
                            eventButtonList.set(index, new ButtonType(newName));
                        });
                    } else if(initialResult.get() == deleteButton){//deletes event
                        int index = rootItem.getChildren().indexOf(selectedItem);
                        lessonPlan.getEventList().remove(index);
                        lessonPlan.getEventNames().remove(index);
                        eventButtonList.remove(index);
                        events.remove(index);
                        rootItem.getChildren().remove(index);
                    } else{
                        choose.close();
                    }
                }
            }
        }
        treeView.getSelectionModel().clearSelection();
    }
    public void deleteCardFromTreeView(MouseEvent event){
        TreeItem<String> selectedItem = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        TreeItem<String> parent = selectedItem.getParent();
        parent.getChildren().remove(selectedItem);
    }

    public static void resetButtons(){
        eventButtonList.clear();
    }


    public void addCardToTreeView(CardButton cardButton) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Add Card to Event");
        alert.setHeaderText("Select the event to add the card to");
        Card card = cardButton.getCard();
        try {
            Image image = new Image(new FileInputStream("GymSoftwarePics/" +
                    card.getPackFolder() + "/" +
                    card.getImage()));
            ImageView iv = new ImageView(image);
            iv.setFitHeight(400.0);
            iv.setFitWidth(400.0);
            alert.setGraphic(iv);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        System.out.println(eventButtonList.size());
        alert.getButtonTypes().setAll(eventButtonList);
        alert.getButtonTypes().add(cancelButton);


        for(ButtonType buttonType:alert.getButtonTypes()){
            Button buttonTest = (Button) alert.getDialogPane().lookupButton(buttonType);
            buttonTest.setStyle("-fx-font-size: 30px; -fx-min-width: 150px; -fx-min-height: 40px; -fx-translate-y: -20px");
        }


        Optional<ButtonType> result = alert.showAndWait();
        //equiv to mouse press action
        result.ifPresent(buttonType -> {
            for(int i =0; i<eventButtonList.size(); i++){
                ButtonType eventButton = eventButtonList.get(i);
                if(buttonType == eventButton){
                    if(!(lessonPlan.getEventList().get(i).size() >= 8)) {//checks to make sure no more than 8 cards are added to an event
                        TreeItem<String> newCard = new TreeItem<>(card.getCode() + " " + card.getTitle());
                        events.get(i).getChildren().add(newCard);
                        lessonPlan.addToEvent(card, i);
                    } else {
                        Alert maxCardAlert = new Alert(Alert.AlertType.WARNING);
                        maxCardAlert.setTitle("Caution");
                        maxCardAlert.setHeaderText("You cannot add any more cards to this event");
                        ButtonType yesButton = new ButtonType("Ok");
                        maxCardAlert.getButtonTypes().setAll(yesButton);
                        Optional<ButtonType> newResult = maxCardAlert.showAndWait();
                        if (newResult.isPresent()) {
                            maxCardAlert.close();
                        }
                    }
                }
            }
        });
        for(TreeItem<String> event : events){
            event.setExpanded(true);
        }
    }
//    /**
//     * Filters the displayed cards based on the input text in the search bar.
//     *
//     * @param inputText - The text to filter cards by.
//     */
//    private void filterCards(String inputText, filters filter) {
//        inputText = inputText.replaceAll("\\s+", "");
//        List<Button> cardButtons = getAllCardButtons();
//    }

    public static LessonPlan getLessonPlan() {
        return lessonPlan;
    }

    public void filterCardsByCheckbox() {
        List<CardButton> visibleButtons = new ArrayList<>();
        List<CardButton> hiddenButtons = new ArrayList<>();
        for (CardButton cardButton : allCards) {
            boolean isVisible = true;
            for(CardFilter filter : filterList) {
                if (!filter.matches(cardButton.getCard())) {
                    isVisible = false;
                    break;
                }
            }
            if (isVisible) {
                visibleButtons.add(cardButton);
                cardButton.setVisible(true);
            } else {
                hiddenButtons.add(cardButton);
                cardButton.setVisible(false);
            }

        }

        //System.out.println(filterList.get(filterList.indexOf(equipmentFilter)));
        cardFlowPane.getChildren().clear();
        cardFlowPane.getChildren().addAll(visibleButtons);
        currentFilteredCards.clear();
        currentFilteredCards.addAll(visibleButtons);
    }


    //************
    //LEVEL FILTER
    //************


    @FXML
    void levelCheckBoxHandle(ActionEvent event) {
        if (event.getSource() == levelABCheckBox) {
            levelFilter.add("AB");
        } else if (event.getSource() == levelAdvancedCheckBox) {
            levelFilter.add("A");
        } else if (event.getSource() == levelIntermediateCheckBox) {
            levelFilter.add("I");
        } else if (event.getSource() == levelBeginnerCheckBox) {
            levelFilter.add("B");
        } else {
        }
        filterCardsByCheckbox();
    }


    //****************
    //Equipment Filter
    //****************

    @FXML
    void setEquipmentTextField(KeyEvent event) {
        String desiredEquipment = equipmentTextfield.getText();
        equipmentFilter.add(desiredEquipment);
        filterCardsByCheckbox();
    }

    @FXML
    void resetButton(ActionEvent event) {
        for(Node nodeOut : filterVBox.getChildren()){
            if(nodeOut instanceof VBox) {
                for (Node nodeIn : ((VBox) nodeOut).getChildren()){
                    if(nodeIn instanceof CheckBox){
                        ((CheckBox) nodeIn).setSelected(false);
                    }
                }
            }
        }
        resetFlowPane();
    }
}