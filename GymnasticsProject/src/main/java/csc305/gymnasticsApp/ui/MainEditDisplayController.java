package csc305.gymnasticsApp.ui;

import csc305.gymnasticsApp.data.*;
import csc305.gymnasticsApp.filters.*;
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
 * The MainEditDisplayController class is responsible for handling user interactions and events on the main edit display page of the application.\
 * It manages the display of lesson plans, card filtering, tree view for events, and undo/redo functionality
 *
 * This class also facilitates the interaction between the graphical user interface (GUI) and the underlying data model
 */
public class MainEditDisplayController implements Initializable {
    @FXML
    private Button doneButton;
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
    @FXML
    private ChoiceBox<String> levelCB;
    @FXML
    private CheckBox favCheckBox;
    private List<CardFilter> filterList = new ArrayList<>();
    public static TreeItem<String> rootItem = new TreeItem<>("Root");


    public static List<TreeItem<String>> events = new ArrayList<>();
    private static final List<CardButton> currentFilteredCards = new ArrayList<>();
    private static List<CardButton> allCards = new ArrayList<>();

    public static boolean isInitialized = false;

    public static LessonPlan lessonPlan;
    private static List<ButtonType> eventButtonList = new ArrayList<>();

    private static LessonPlanUndoRedoHandler undoRedoHandler;

    /**
     * Initializes the tree view on the main edit display screen.
     *
     * @param arg0 The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param arg1 The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lessonPlan = GymnasticsAppBeta.getLessonPlan().getThePlan();
        if(lessonPlan.getEventNames().isEmpty()){
            lessonPlan.getThePlan().addEventName("Event 1");
        }
        clearAndResetAlertButtons();
        initializeTreeView();
        addCardsToFlowPane();
        allCards = getAllCardButtons();
        resetFlowPane();
        initFilterList();
        List<Card> eventCards = new ArrayList<>();
        lessonPlan.getThePlan().addToEventList(eventCards);
        lessonTitle.setText(lessonPlan.getThePlan().getLessonPlanTitle());
        undoRedoHandler = new LessonPlanUndoRedoHandler(lessonPlan);
        GymnasticsAppBeta.lessonPlanURHandler = undoRedoHandler;
    }

    /**
     * Clears and resets alert buttons related to events
     */
    public static void clearAndResetAlertButtons(){
        eventButtonList.clear();
        for(int i = 0; i < lessonPlan.getThePlan().getEventNames().size(); i++){
            eventButtonList.add(new ButtonType(lessonPlan.getThePlan().getEventNames().get(i)));
        }
    }

    /**
     * Initializes the tree view on the main edit display screen. Populates the tree view with events if empty,
     * sets the display properties, and establishes the root item for the tree view
     */
    private void initializeTreeView(){
        System.out.println("size of root items: " + rootItem.getChildren().size());
        if (rootItem.getChildren().isEmpty()) {
            if(events.isEmpty()){
                events.add(new TreeItem<String>("Event 1"));
                if(eventButtonList.isEmpty()) {
                    eventButtonList.add(createEventButton(1));
                }
            }
            for(TreeItem<String> event: events){
                rootItem.getChildren().add(event);
            }
        }
        treeView.setShowRoot(false);
        treeView.setRoot(rootItem);
    }

    /**
     * Creates card button for all cards in the CardDatabase. Each card button is associated with a Card object,
     * and an action event listener is set to add the card to the tree view when clicked
     */
    public void createCardButtons(){
        if(!isInitialized) {
            allCards.clear();
            for (Card card : CardDatabase.getAllCards()) {
                if(FavoriteCollection.getInstance().getFavoriteSet().contains(card.getUniqueID())){
                    card.setFavorite(true);
                }
                CardButton cardButton = new CardButton(card);
                cardButton.setOnMouseClicked(event -> addCardToTreeView(cardButton));
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
        for(CardButton card : allCards){
            card.setVisible(true);
        }
        cardFlowPane.getChildren().addAll(allCards);
    }

    /**
     * Resets the FlowPane by clearing all card buttons, filtering cards, and resetting the equipment text field
     */
    public void resetFlowPane(){
        cardFlowPane.getChildren().clear();
        cardFlowPane.getChildren().addAll(allCards);
        equipmentTextfield.clear();
        equipmentTextfield.setPromptText("Equipment Keyword");
    }

    /**
     * Initializes the filter options including gender, model gender, event, level, and equipment filters
     * Sets up listeners for filter changes to update the displayed cards accordingly
     */
    private void initFilterList(){
        genderCB.getItems().addAll(CardDatabase.getGenderList());
        genderCB.setValue("N");
        modelGenderCB.getItems().add("ALL");
        modelGenderCB.getItems().addAll(CardDatabase.getModelGenderList());
        modelGenderCB.setValue("ALL");
        eventCB.setValue("ALL");
        eventCB.getItems().addAll(CardDatabase.getEventList());
        levelCB.setValue("ALL");
        levelCB.getItems().addAll(CardDatabase.getLevelList());
        favCheckBox.setSelected(false);

        genderCB.valueProperty().addListener((obs, oldVal, newVal) -> updateFilteredVisibleCards());
        modelGenderCB.valueProperty().addListener((obs, oldVal, newVal) -> updateFilteredVisibleCards());
        eventCB.valueProperty().addListener((obs, oldVal, newVal) -> updateFilteredVisibleCards());
        levelCB.valueProperty().addListener((obs, oldVal, newVal) -> updateFilteredVisibleCards());

        favCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> updateFilteredVisibleCards());

        searchBar.textProperty().addListener((obs, oldVal, newVal) -> updateFilteredVisibleCards());
        equipmentTextfield.textProperty().addListener((obs, oldVal, newVal) -> updateFilteredVisibleCards());

    }

    private void resetFilterList(){
        favCheckBox.setSelected(false);
        genderCB.setValue("N");
        modelGenderCB.setValue("ALL");
        levelCB.setValue("ALL");
        eventCB.setValue("ALL");

    }


    /**
     * Updates the visibility of card buttons based on selected filter criteria such as gender, model gender, event, level,
     * search bar text, and equipment. Applies combined filtering and sets card visibility accordingly.
     */
    void updateFilteredVisibleCards() {
        CardFilter genderFilter = new GenderFilter(genderCB.getValue());
        CardFilter modelGenderFilter = new ModelGenderFilter(modelGenderCB.getValue());
        CardFilter eventFilter = new EventFilter(eventCB.getValue());
        CardFilter levelFilter = new LevelFilter(levelCB.getValue());

        CardFilter favFilter = new FavFilter(favCheckBox.isSelected());

        CardFilter searchBarFilter = new SearchBarFilter(searchBar.getText());
        CardFilter equipmentFilter = new EquipmentFilter(equipmentTextfield.getText());

        CardFilter combineAndFilter = new CombineAndFilter(genderFilter, modelGenderFilter, eventFilter, searchBarFilter,
                                                            levelFilter, equipmentFilter, favFilter);

        for (CardButton card : allCards) {
            boolean includeThisCard = combineAndFilter.matches(card.getCard());
            card.setVisible(includeThisCard);
            card.setManaged(includeThisCard);
        }
    }

    /**
     * Adds tree items representing events and associated cards to the root item in the tree view
     *
     * @param lessonPlan The LessonPlan object containing events and cards
     */
    public static void addTreeCardItems(LessonPlan lessonPlan) {
        rootItem.getChildren().clear();
        List<TreeItem<String>> listOfNewEvents = new ArrayList<>();
        for(int i = 0; i < lessonPlan.getEventNames().size(); i++){
            String eventName = lessonPlan.getEventNames().get(i);
            TreeItem<String> newEvent = new TreeItem<String>(eventName);
            newEvent.setExpanded(true);
            for (int j = 0; j < lessonPlan.getEventCards(i).size(); j++) {
                TreeItem<String> newCard = new TreeItem<String>(lessonPlan.getEventList().get(i).get(j).getCode() + " " + lessonPlan.getEventList().get(i).get(j).getTitle());
                newEvent.getChildren().add(newCard);
            }
            listOfNewEvents.add(newEvent);
        }
        events.clear();
        events.addAll(listOfNewEvents);

        rootItem.getChildren().addAll(events);

    }

    /**
     * Clears all tree items, both events and cards, from the root item in the tree view
     */
    public static void clearTreeCardItems() {
        rootItem.getChildren().clear();
        for(TreeItem<String> event: events){
            event.getChildren().clear();
        }
    }

    /**
     * Handles the action when the "Add Event" button is clicked. Adds a new event to the tree view,
     * updates the lesson plan, and saves the current state for undo/redo functionality
     */
    public void eventAdderHandle(){
        int eventNum = events.size() + 1;
        events.add(new TreeItem<>("Event " + eventNum));
        rootItem.getChildren().clear();
        rootItem.getChildren().addAll(events);
        List<Card> eventCards = new ArrayList<>();
        lessonPlan.getThePlan().addToEventList(eventCards);
        lessonPlan.getThePlan().addEventName("Event " + eventNum);
        ButtonType eventButton = createEventButton(eventNum);
        eventButtonList.add(eventButton);
        undoRedoHandler.saveState();
    }

    /**
     * Creates a ButtonType object representing an event button with a specified event number.
     *
     * @param eventNum The event number to be included in the event button label
     * @return The created ButtonType object
     */
    private ButtonType createEventButton(int eventNum){
        ButtonType eventButton = new ButtonType("Event " + eventNum);
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
        if (lessonPlan.getThePlan().getLessonPlanTitle() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("Please add a Title before proceeding.");
            ButtonType ok = new ButtonType("Ok");
            alert.getButtonTypes().setAll(ok);
            alert.showAndWait();
        } else {
            GymnasticsAppBeta.switchToPreviewPage();
            lessonPlan.getThePlan().printEverything();
        }
    }

    /**
     * Handles the action when the "Undo" button is clicked, allowing the user to undo an action
     *
     * @param event The ActionEvent triggered by clicking the "Undo" button
     */
    public void undoButtonHandle(ActionEvent event){
        undoRedoHandler.undo();
    }

    /**
     * Handles the action when the "Redo" button is clicked, allowing the user to restore an action
     *
     * @param event The ActionEvent triggered by clicking the "Redo" button
     */
    public void redoButtonHandle(ActionEvent event){
        undoRedoHandler.redo();
    }

    /**
     * Sets the lessonPlan title
     * @param event the name of the lesson plan title
     */
    @FXML
    void setLessonTitle(KeyEvent event) {
        String title = lessonTitle.getText();
        lessonPlan.getThePlan().setLessonPlanTitle(title);
    }

    /**
     * Handles the action when the filter menu is opened.
     *
     * @param event - The ActionEvent triggered when opening the filter menu.
     */
    @FXML
    void openFilterMenu(MouseEvent event){
        filterMenu.setVisible(true);
    }


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


    /**
     * Handles the selection of an item in the tree view. Depending on whether the selected item
     * is an event or a card, it prompts the user with options to rename, delete, or cancel the action
     * If a card is deleted, it also removes it from the tree view
     *
     * @param event The MouseEvent triggering the item selection
     */
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
                    lessonPlan.getThePlan().deleteFromEvent(index, cardNum);
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
                            lessonPlan.getThePlan().setEventName(newName, index);
                            selectedItem.setValue(newName);
                            eventButtonList.set(index, new ButtonType(newName));
                        });
                    } else if(initialResult.get() == deleteButton){//deletes event
                       int index = rootItem.getChildren().indexOf(selectedItem);
                       lessonPlan.getThePlan().getEventList().remove(index);
                       lessonPlan.getThePlan().getEventNames().remove(index);
                       eventButtonList.remove(index);
                       events.remove(index);
                       rootItem.getChildren().remove(index);
                    } else{
                        choose.close();
                    }
                }
            }
        }
        undoRedoHandler.saveState();
        treeView.getSelectionModel().clearSelection();
    }

    /**
     * Deletes the selected card from the tree view. Assumes the selected item is a card
     *
     * @param event The MouseEvent triggering the item selection
     */
    public void deleteCardFromTreeView(MouseEvent event){
        TreeItem<String> selectedItem = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        TreeItem<String> parent = selectedItem.getParent();
        parent.getChildren().remove(selectedItem);
    }

    /**
     * Resets the list of event buttons
     */
    public static void resetButtons(){
        eventButtonList.clear();
    }

    /**
     * Adds a card to the tree view based on user selection of an event
     *
     * @param cardButton The CardButton representing the card to be added
     */
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
                    if(!(lessonPlan.getThePlan().getEventList().get(i).size() >= 8)) {//checks to make sure no more than 8 cards are added to an event
                        TreeItem<String> newCard = new TreeItem<>(card.getCode() + " " + card.getTitle());
                        events.get(i).getChildren().add(newCard);
                        lessonPlan.getThePlan().addToEvent(card, i);
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
        undoRedoHandler.saveState();
    }

    /**
     * Retrieves the current LessonPlan instance
     *
     * @return The LessonPlan instance
     */
    public static LessonPlan getLessonPlan() {
        return lessonPlan.getThePlan();
    }


    /**
     * Handles the event when the user clicks the "Set Filter" button in the UI
     *
     * @param event The ActionEvent triggered by the button click
     */
    @FXML
    void setFilterController(ActionEvent event) {
        new CombineAndFilter();
        filterMenu.setVisible(false);
    }

    /**
     * Resets all checkboxes in the filter UI
     *
     * @param event The ActionEvent triggered by the button click
     */
    @FXML
    void resetButton(ActionEvent event) {
        resetFilterList();
        resetFlowPane();
    }

    /**
     * Handles the event when the user clicks the "Done" button in the filter UI
     */
    @FXML
    void doneButtonHandle(){
        filterMenu.setVisible(false);
    }

    @FXML
    void mouseExitHandle(MouseEvent event){
        if(filterMenu.getLayoutX()+filterMenu.getWidth() <= event.getX() || filterMenu.getLayoutY()+filterMenu.getHeight() <= event.getY()) {
            filterMenu.setVisible(false);
        }
    }

}