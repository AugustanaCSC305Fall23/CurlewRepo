package csc305.gymnasticsApp;

import csc305.gymnasticsApp.CardFilter.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
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
    private TextField courseTitle;
    @FXML
    private Button backButton;
    @FXML
    private Button testButton;

    @FXML
    private VBox filterMenu;

    @FXML
    private Button previewButton;

    @FXML
    private TextField drillSearchBar;

    @FXML
    private ScrollBar scrollBar;

    @FXML
    private Button drillSearchButton;

    @FXML
    private ScrollPane cardScrollPane;

    @FXML
    private FlowPane cardFlowPane;

    @FXML
    private TreeView treeView;

    public static TreeItem<String> rootItem = new TreeItem<>("Root");
    @FXML
    public static TreeItem<String> eventOneItems = new TreeItem<>("Event 1");

    @FXML
    public static TreeItem<String> eventTwoItems = new TreeItem<>("Event 2");

    private static final List<Button> currentFilteredCards = new ArrayList<>();
    private static List<Button> allCards = new ArrayList<>();

    public String[] cardParentEvent = {"Event 1", "Event 2"};

    public static boolean isInitialized = false;

    /**
     * Initializes the tree view on the main edit display screen.
     *
     * @param arg0      The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param arg1      The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        if(rootItem.getChildren().isEmpty()) {
            rootItem.getChildren().addAll(eventOneItems, eventTwoItems);
        }else if(rootItem.getChildren().size() != 2){
            System.out.println("Duplicating");
        }
        treeView.setShowRoot(false);
        treeView.setRoot(rootItem);
        addCardsToFlowPane();
        allCards = getAllCardButtons();
        resetFlowPane();
        initFilterList();
        if (LessonPlan.getLessonPlanTitle() != null){
            courseTitle.setText(LessonPlan.getLessonPlanTitle());
        }
    }



    public static void addTreeCardItem(List<Card> eventOneCards,List<Card> eventTwoCards){
        //for(Card card : eventOneCards){
        //    System.out.println(card.getTitle());
        //}
        rootItem.getChildren().clear();
        eventOneItems.getChildren().clear();
        eventTwoItems.getChildren().clear();
        for(Card card : eventOneCards){
            TreeItem<String> newCard = new TreeItem<String>(card.getTitle());
            //System.out.println(card.getTitle());
            eventOneItems.getChildren().add(newCard);
        }
        for(Card card : eventTwoCards){
            TreeItem<String> newCard = new TreeItem<String>(card.getTitle());
            eventTwoItems.getChildren().add(newCard);
        }
        rootItem.getChildren().addAll(eventOneItems, eventTwoItems);
    }

    public static void clearTreeCardItems(){
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
            LessonPlan.resetLessonPlan();
            MainEditDisplayController.clearTreeCardItems();
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
        if(courseTitle.getText() == "") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("LessonPlan title can't be blank.");
            alert.setContentText("Please enter a course title name.");
            alert.showAndWait();
        } else {
            LessonPlan.setLessonPlanTitle(courseTitle.getText());
            LessonPlan.setEventOneName("Event 1 Test");
            LessonPlan.setEventTwoName("Event 2 Test");
            GymnasticsAppBeta.switchToPreviewPage();
            LessonPlan.printEverything();
        }

    }

    /**
     * Handles the action when the "Go" button is clicked to filter cards based on the search bar input.
     *
     * @param event - The ActionEvent triggered by clicking the "Go" button.
     */
    @FXML
    void goButtonHandle(ActionEvent event) {
        filterCardsFromSearch(drillSearchBar.getText().toLowerCase());
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



    //Need to reset currentFilteredCards at some point
    /**
     * Adds cards to the FlowPane for display.
     */
    private void addCardsToFlowPane(){
        if(!isInitialized) {
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
            cardFlowPane.getChildren().clear();
            cardFlowPane.getChildren().addAll(allCards);
        }
    }
    private Button createCardButton(Card card, Image image) {
        ImageView iv = new ImageView(image);
        iv.setFitHeight(198.0);
        iv.setFitWidth(198.0);
        Button cardButton = new Button("", iv);
        cardButton.setMinHeight(Double.MIN_VALUE);
        cardButton.setMinWidth(Double.MIN_VALUE);
        cardButton.setPrefHeight(200.0);
        cardButton.setPrefWidth(200.0);
        cardButton.setMnemonicParsing(false);
        cardButton.setStyle("-fx-border-color: black;");
        cardButton.setId(card.getUniqueID());
        cardButton.setOnMouseClicked(event -> addCardToTreeView(cardButton, card));
        return cardButton;
    }

    /**
     * Retrieves all card buttons from the FlowPane.
     *
     * @return A list of all card buttons in the FlowPane.
     */
    private List<Button> getAllCardButtons() {
        List<Button> cardButtons = new ArrayList<>();
        for (int i = 0; i < cardFlowPane.getChildren().size(); i++) {
            if (cardFlowPane.getChildren().get(i) instanceof Button) {
                cardButtons.add((Button) cardFlowPane.getChildren().get(i));
            }
        }
        return cardButtons;
    }



    //DELETES CARD

    public void selectItem(MouseEvent event){
        TreeItem<String> selectedItem = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        TreeItem<String> parent = selectedItem.getParent();


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
                if(parent.getValue().equals(cardParentEvent[0])){
                    Card card = LessonPlan.getCardFromTreeItem(selectedItem.getValue(), 1);
                    LessonPlan.deleteFromEventOne(card);
                } else{
                    Card card = LessonPlan.getCardFromTreeItem(selectedItem.getValue(), 2);
                    LessonPlan.deleteFromEventTwo(card);
                }
                deleteCardFromTreeView(event);
            }
        }
    }
    public void deleteCardFromTreeView(MouseEvent event){
        TreeItem<String> selectedItem = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        TreeItem<String> parent = selectedItem.getParent();
        parent.getChildren().remove(selectedItem);
    }

    public void addCardToTreeView(Button cardButton, Card card) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Add Card to Event");
        alert.setHeaderText("Select the event to add the card to");

        ButtonType eventOneButton = new ButtonType(cardParentEvent[0]);
        ButtonType eventTwoButton = new ButtonType(cardParentEvent[1]);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(eventOneButton, eventTwoButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();

        result.ifPresent(buttonType -> {
            if (buttonType == eventOneButton) {
                TreeItem<String> newCard = new TreeItem<>(card.getTitle());
                eventOneItems.getChildren().add(newCard);
                LessonPlan.addToEventOne(card);
            } else if (buttonType == eventTwoButton) {
                TreeItem<String> newCard = new TreeItem<>(card.getTitle());
                eventTwoItems.getChildren().add(newCard);
                LessonPlan.addToEventTwo(card);
            }
        });
        eventOneItems.setExpanded(true);
        eventTwoItems.setExpanded(true);
    }
    /**
     * Filters the displayed cards based on the input text in the search bar.
     *
     * @param inputText - The text to filter cards by.
     */
    private void filterCards(String inputText, CardFilter filter) {
        inputText = inputText.replaceAll("\\s+", "");
        List<Button> cardButtons = getAllCardButtons();
    }

    public void resetFlowPane(){
        cardFlowPane.getChildren().clear();
        initFilterList();
        filterCardsByCheckbox();
        equipmentTextfield.clear();
        equipmentTextfield.setPromptText("Equipment Keyword");

    }

    private GenderFilter genderFilter = new GenderFilter();
    private ModelGenderFilter  modelGenderFilter = new ModelGenderFilter();
    private EventFilter eventFilter = new EventFilter();
    private LevelFilter levelFilter = new LevelFilter();
    private EquipmentFilter equipmentFilter = new EquipmentFilter();
    private List<CardFilter> filterList = new ArrayList<>();

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
    }


    private void filterCardsFromSearch(String inputText) {
        List<Button> visibleButtons = new ArrayList<>();
        List<Button> hiddenButtons = new ArrayList<>();
        for (Button cardButton : allCards) {
            String buttonText = cardButton.getId().toLowerCase();
            if (buttonText.contains(inputText.toLowerCase()) && currentFilteredCards.contains(cardButton)) {
                visibleButtons.add(cardButton);
                cardButton.setVisible(true);
            } else {
                hiddenButtons.add(cardButton);
                cardButton.setVisible(false);
            }
        }
        cardFlowPane.getChildren().clear(); // Clear the cardFlowPane before adding visible buttons
        cardFlowPane.getChildren().addAll(visibleButtons);
        currentFilteredCards.addAll(visibleButtons);
    }

    public void filterCardsByCheckbox() {
        List<Button> visibleButtons = new ArrayList<>();
        List<Button> hiddenButtons = new ArrayList<>();
        for (Button cardButton : allCards) {
            boolean isVisible = true;
            for(CardFilter filter : filterList) {
                if (!filter.matches(CardDatabase.getCardByID(cardButton.getId()))) {
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
    //*************
    //GENDER FILTER
    //*************
    @FXML public CheckBox checkBoxMale;
    @FXML public CheckBox checkBoxFemale;

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
    @FXML public CheckBox checkBoxFloor;
    @FXML public CheckBox checkBoxUnevenBars;
    @FXML public CheckBox checkBoxBeam;
    @FXML public CheckBox checkBoxVault;
    @FXML public CheckBox checkBoxTramp;
    @FXML public CheckBox checkBoxStrength;

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
    @FXML public CheckBox modelCheckboxMale;
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
    @FXML public CheckBox levelABCheckBox;

    @FXML public CheckBox levelAdvancedCheckBox;

    @FXML public CheckBox levelBeginnerCheckBox;

    @FXML public CheckBox levelIntermediateCheckBox;


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

    @FXML private TextField equipmentTextfield;

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

    @FXML public VBox filterVBox;
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
