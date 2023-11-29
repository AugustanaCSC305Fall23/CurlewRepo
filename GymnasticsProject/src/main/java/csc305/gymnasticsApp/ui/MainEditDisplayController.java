package csc305.gymnasticsApp.ui;

import csc305.gymnasticsApp.data.Card;
import csc305.gymnasticsApp.data.CardButton;
import csc305.gymnasticsApp.data.CardDatabase;
import csc305.gymnasticsApp.data.LessonPlan;
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
 * The MainEditDisplayController class is responsible for handling user interactions and events on the main edit display page of the application.
 */
public class MainEditDisplayController implements Initializable {

    @FXML
    private TextField lessonTitle;
    @FXML
    private VBox filterMenu;
    @FXML
    private TextField drillSearchBar;
    @FXML
    private FlowPane cardFlowPane;
    @FXML
    private TreeView treeView;
    @FXML public VBox filterVBox;
    @FXML private TextField equipmentTextfield;
    @FXML public CheckBox modelCheckboxMale;
    @FXML public CheckBox checkBoxFloor;
    @FXML public CheckBox checkBoxUnevenBars;
    @FXML public CheckBox checkBoxBeam;
    @FXML public CheckBox checkBoxVault;
    @FXML public CheckBox checkBoxTramp;
    @FXML public CheckBox checkBoxStrength;
    @FXML public CheckBox checkBoxMale;
    @FXML public CheckBox checkBoxFemale;
    @FXML public CheckBox levelABCheckBox;
    @FXML public CheckBox levelAdvancedCheckBox;
    @FXML public CheckBox levelBeginnerCheckBox;
    @FXML public CheckBox levelIntermediateCheckBox;
    private GenderFilter genderFilter = new GenderFilter();
    private ModelGenderFilter  modelGenderFilter = new ModelGenderFilter();
    private EventFilter eventFilter = new EventFilter();
    private LevelFilter levelFilter = new LevelFilter();
    private EquipmentFilter equipmentFilter = new EquipmentFilter();
    private List<CardFilter> filterList = new ArrayList<>();

    private SearchBarFilter searchBarFilter = new SearchBarFilter();


    public static TreeItem<String> rootItem = new TreeItem<>("Root");
    @FXML
    public static TreeItem<String> eventOneItems = new TreeItem<>("Event 1");

    @FXML
    public static TreeItem<String> eventTwoItems = new TreeItem<>("Event 2");

    private static final List<CardButton> currentFilteredCards = new ArrayList<>();
    private static List<CardButton> allCards = new ArrayList<>();

    public static String[] cardParentEvents = {"Event 1", "Event 2"};

    public static boolean isInitialized = false;

    public static LessonPlan lessonPlan;

    /**
     * Initializes the tree view on the main edit display screen.
     *
     * @param arg0 The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param arg1 The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lessonPlan = GymnasticsAppBeta.getLessonPlan();
        lessonPlan.printEverything();
        initializeTreeView();
        addCardsToFlowPane();
        allCards = getAllCardButtons();
        resetFlowPane();
        initFilterList();
        System.out.println("initializing");
    }

    private void initializeTreeView(){
        if (rootItem.getChildren().isEmpty()) {
            rootItem.getChildren().addAll(eventOneItems, eventTwoItems);
        } else if (rootItem.getChildren().size() != 2) {
            System.out.println("Duplicating");
        }
        lessonTitle.setText(GymnasticsAppBeta.getLessonPlan().getLessonPlanTitle());
        treeView.setShowRoot(false);
        treeView.setRoot(rootItem);
    }

    public void createCardButtons(){
        if(!isInitialized) {
            allCards.clear();
            for (Card card : CardDatabase.getAllCards()) {
                Image image = null;
                try {
                    image = new Image(new FileInputStream("GymSoftwarePics/" +
                            card.getPackFolder().toUpperCase() + "Pack/" +
                            card.getImage()));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                CardButton cardButton = createCardButton(card, image);
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
        /*if(!isInitialized) {
            try {
                cardFlowPane.getChildren().clear();
                allCards.clear();
                for (Card card : CardDatabase.getAllCards()) {
                    Image image = new Image(new FileInputStream("src/main/resources/GymSoftwarePics" + "/" +
                            card.getPackFolder().toUpperCase() + "Pack/" +
                            card.getImage()));
                    Button cardButton = createCardButton(card, image);
                    cardFlowPane.getChildren().add(cardButton);
                    allCards.add(cardButton);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            isInitialized = true;
        }else{

         */
            cardFlowPane.getChildren().clear();
            cardFlowPane.getChildren().addAll(allCards);
    }

    public void resetFlowPane(){
        cardFlowPane.getChildren().clear();
        initFilterList();
        filterCardsByCheckbox();
        equipmentTextfield.clear();
        equipmentTextfield.setPromptText("Equipment Keyword");

    }

    private void initFilterList(){
        genderFilter.getDesiredGenders().clear();
        filterList.add(genderFilter);
        modelGenderFilter.getSelectedModelGender().clear();
        filterList.add(modelGenderFilter);
        eventFilter.reset();
        filterList.add(eventFilter);
        levelFilter.reset();
        filterList.add(levelFilter);
        equipmentFilter.reset();
        filterList.add(equipmentFilter);
        searchBarFilter.reset();
        filterList.add(searchBarFilter);
    }


    public static void addTreeCardItem(List<Card> eventOneCards, List<Card> eventTwoCards) {
        rootItem.getChildren().clear();
        eventOneItems.getChildren().clear();
        eventTwoItems.getChildren().clear();
        for (Card card : eventOneCards) {
            TreeItem<String> newCard = new TreeItem<String>(card.getTitle());
            eventOneItems.getChildren().add(newCard);
        }
        for (Card card : eventTwoCards) {
            TreeItem<String> newCard = new TreeItem<String>(card.getTitle());
            eventTwoItems.getChildren().add(newCard);
        }
        rootItem.getChildren().addAll(eventOneItems, eventTwoItems);
    }

    public static void clearTreeCardItems() {
        rootItem.getChildren().clear();
        eventOneItems.getChildren().clear();
        eventTwoItems.getChildren().clear();

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

        if (result.get() == yesButton) {
            lessonPlan.resetLessonPlan();
            MainEditDisplayController.clearTreeCardItems();
            MainEditDisplayController.eventOneItems.setValue("Event 1");
            MainEditDisplayController.eventTwoItems.setValue("Event 2");
            MainEditDisplayController.cardParentEvents[0] = "Event 1";
            MainEditDisplayController.cardParentEvents[1] = "Event 2";
            GymnasticsAppBeta.switchToHomePage();
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
     * Handles the action when the "Go" button is clicked to filter cards based on the search bar input.
     *
     * @param event - The ActionEvent triggered by clicking the "Go" button.
     */
//    @FXML
//    void goButtonHandle(ActionEvent event) {
//        filterCardsFromSearch(drillSearchBar.getText().toLowerCase());
//    }

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
        TreeItem<String> parent = selectedItem.getParent();

        //checks if not parent (event)
        if(parent.equals(eventOneItems) || parent.equals(eventTwoItems)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Caution");
            alert.setHeaderText("Are you sure you want to delete this card?");
            alert.setContentText("Please select an option.");
            ButtonType yesButton = new ButtonType("Yes");
            ButtonType noButton = new ButtonType("No");
            alert.getButtonTypes().setAll(yesButton, noButton);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == yesButton) {
                if(parent.getValue().equals(cardParentEvents[0])){
                    Card card = lessonPlan.getCardFromTreeItem(selectedItem.getValue(), 1);
                    lessonPlan.deleteFromEventOne(card);
                } else{
                    Card card = lessonPlan.getCardFromTreeItem(selectedItem.getValue(), 2);
                    lessonPlan.deleteFromEventTwo(card);
                }
                deleteCardFromTreeView(event);
            }
        } else{ //is event, so shows text box
            TextInputDialog renameDialog= new TextInputDialog();
            renameDialog.setTitle("Rename " + selectedItem.getValue());
            renameDialog.setHeaderText("What do you want to rename this event to?");
            renameDialog.setContentText("New Event Name: ");

            Optional<String> result = renameDialog.showAndWait();
            result.ifPresent(newName -> {
                if(selectedItem.equals(eventOneItems)){
                    LessonPlan.setEventOneName(newName);
                    cardParentEvents[0] = newName;
                } else{
                    LessonPlan.setEventTwoName(newName);
                    cardParentEvents[1] = newName;
                }
                selectedItem.setValue(newName);
            });
        }
    }
    public void deleteCardFromTreeView(MouseEvent event){
        TreeItem<String> selectedItem = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        TreeItem<String> parent = selectedItem.getParent();
        parent.getChildren().remove(selectedItem);
    }

    public void addCardToTreeView(CardButton cardButton) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Add Card to Event");
        alert.setHeaderText("Select the event to add the card to");
        Card card = cardButton.getAssociatedCard();
        try {
            Image image = new Image(new FileInputStream("GymSoftwarePics/" +
                    card.getPackFolder().toUpperCase() + "Pack/" +
                    card.getImage()));
            ImageView iv = new ImageView(image);
            iv.setFitHeight(400.0);
            iv.setFitWidth(400.0);
            alert.setGraphic(iv);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ButtonType eventOneButton = new ButtonType(cardParentEvents[0]);
        ButtonType eventTwoButton = new ButtonType(cardParentEvents[1]);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(eventOneButton, eventTwoButton, cancelButton);

        for(ButtonType buttonType:alert.getButtonTypes()){
            Button buttonTest = (Button) alert.getDialogPane().lookupButton(buttonType);
            buttonTest.setStyle("-fx-font-size: 30px; -fx-min-width: 150px; -fx-min-height: 40px; -fx-translate-y: -20px");
        }


        Optional<ButtonType> result = alert.showAndWait();

        result.ifPresent(buttonType -> {
            if (buttonType == eventOneButton) {
                TreeItem<String> newCard = new TreeItem<>(card.getCode() + " " + card.getTitle());
                eventOneItems.getChildren().add(newCard);
                lessonPlan.addToEventOne(card);
            } else if (buttonType == eventTwoButton) {
                TreeItem<String> newCard = new TreeItem<>(card.getCode() + " " + card.getTitle());
                eventTwoItems.getChildren().add(newCard);
                lessonPlan.addToEventTwo(card);
            }
        });
        eventOneItems.setExpanded(true);
        eventTwoItems.setExpanded(true);
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
                if (!filter.matches(cardButton.getAssociatedCard())) {
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

    @FXML
    void filterCardsFromSearch(KeyEvent event) {
        String keyword = drillSearchBar.getText();
        searchBarFilter.add(keyword);
        filterCardsByCheckbox();
    }


    //*************
    //GENDER FILTER
    //*************


    @FXML
    void setChangedGenderBox(ActionEvent event){
        if(event.getSource() == checkBoxMale){
            genderFilter.add("M");
        }else if(event.getSource() == checkBoxFemale){
            genderFilter.add("F");
        }else{
            genderFilter.add("N");
        }
        if(!genderFilter.getDesiredGenders().isEmpty()) {
            if (!filterList.contains(genderFilter)) {
                filterList.add(genderFilter);
            }
        }else{
            if (filterList.contains(genderFilter)) {
                filterList.remove(genderFilter);
            }
        }
        filterCardsByCheckbox();
    }

    //************
    //EVENT FILTER
    //************

    @FXML
    void eventCheckBoxHandle(ActionEvent event){
       if(event.getSource() == checkBoxFloor){
           eventFilter.add("floor");
       }else if(event.getSource() == checkBoxUnevenBars){
           eventFilter.add("Uneven Bars");
       }else if(event.getSource() == checkBoxBeam){
           eventFilter.add("beam");
       }else if(event.getSource() == checkBoxVault){
           eventFilter.add("vault");
       }else if(event.getSource() == checkBoxTramp){
           eventFilter.add("tramp");
       }else if(event.getSource() == checkBoxStrength){
           eventFilter.add("strength");
       }else{
        }
       filterCardsByCheckbox();
    }



    //****************
    //MODEL SEX FILTER
    //****************
    @FXML
    void modelGenderCheckBoxHandle(ActionEvent event){
        if(event.getSource() == modelCheckboxMale){
            modelGenderFilter.add("M");
        }else{
            modelGenderFilter.add("F");
        }
        filterCardsByCheckbox();
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
    void setFilterController(ActionEvent event) {
        new CombineAndFilter();
        filterMenu.setVisible(false);
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
