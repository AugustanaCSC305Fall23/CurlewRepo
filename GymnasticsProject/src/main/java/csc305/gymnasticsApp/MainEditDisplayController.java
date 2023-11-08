package csc305.gymnasticsApp;

import csc305.gymnasticsApp.CardFilter.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        TreeItem<String> rootItem = new TreeItem<>("Root");
        rootItem.getChildren().addAll(eventOneItems, eventTwoItems);
        treeView.setShowRoot(false);
        treeView.setRoot(rootItem);
        addCardsToFlowPane();
        allCards = getAllCardButtons();
        resetFlowPane();
    }


    /**
     * Handles the action when the "Back" button is clicked, allowing the user to return to the lesson plan page.
     *
     * @param event - The ActionEvent triggered by clicking the "Back" button.
     */
    @FXML
    void backButtonHandle(ActionEvent event) {GymnasticsAppBeta.switchToHomePage();
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
            alert.setHeaderText("Course title can't be blank.");
            alert.setContentText("Please enter a course title name.");
            alert.showAndWait();
        } else {
            Course.setCourseTitle(courseTitle.getText());
            if(!(CardDatabase.getEventOneTreeCards().isEmpty())) {
                Course.setEventOneCards(CardDatabase.getEventOneTreeCards());
            }
            if(!(CardDatabase.getEventTwoTreeCards().isEmpty())) {
                Course.setEventTwoCards(CardDatabase.getEventTwoTreeCards());
            }
            Course.setEventOneName("Event 1 Test");
            Course.setEventTwoName("Event 2 Test");
            GymnasticsAppBeta.switchToPreviewPage();
            Course.printEverything();
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
                    Card card = CardDatabase.getCardFromTreeItem(selectedItem.getValue(), 1);
                    CardDatabase.deleteEventOneTreeCard(card);
                } else{
                    Card card = CardDatabase.getCardFromTreeItem(selectedItem.getValue(), 2);
                    CardDatabase.deleteEventTwoTreeCard(card);
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
                CardDatabase.addEventOneTreeCard(card);
            } else if (buttonType == eventTwoButton) {
                TreeItem<String> newCard = new TreeItem<>(card.getTitle());
                eventTwoItems.getChildren().add(newCard);
                CardDatabase.addEventTwoTreeCard(card);
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
        for(Button card : allCards){
            card.setVisible(true);
        }
        cardFlowPane.getChildren().addAll(allCards);
    }

    private void filterCardsFromSearch(String inputText) {
        List<Button> visibleButtons = new ArrayList<>();
        List<Button> hiddenButtons = new ArrayList<>();
        for (Button cardButton : allCards) {
            String buttonText = cardButton.getId().toLowerCase();
            if (buttonText.contains(inputText.toLowerCase())) {
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

    public void filterCardsByCheckbox(CardFilter filter) {
        List<Button> visibleButtons = new ArrayList<>();
        List<Button> hiddenButtons = new ArrayList<>();
        for (Button cardButton : allCards) {
            if (filter.matches(CardDatabase.getCardByID(cardButton.getId()))) {
                visibleButtons.add(cardButton);
                cardButton.setVisible(true);
            } else {
                hiddenButtons.add(cardButton);
                cardButton.setVisible(false);
            }
        }

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
    @FXML public CheckBox checkBoxNeutral;
    @FXML
    void setChangedGenderBox(ActionEvent event){
        GenderFilter genderFilter = new GenderFilter();
        if(event.getSource() == checkBoxMale){
            genderFilter.add("M");
        }else if(event.getSource() == checkBoxFemale){
            genderFilter.add("F");
        }else{
            genderFilter.add("N");
        }
        System.out.println(genderFilter.getDesiredGenders().toString());
        filterCardsByCheckbox(genderFilter);
    }


    //************
    //EVENT FILTER
    //************
    @FXML public CheckBox checkBoxAll;
    @FXML public CheckBox checkBoxFloor;
    @FXML public CheckBox checkBoxUnevenBars;
    @FXML public CheckBox checkBoxBeam;
    @FXML public CheckBox checkBoxVault;
    @FXML public CheckBox checkBoxTramp;
    @FXML public CheckBox checkBoxStrength;

    @FXML
    void eventCheckBoxHandle(ActionEvent event){
       EventFilter eventFilter = new EventFilter();
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
           eventFilter.add("ALL");
        }
       filterCardsByCheckbox(eventFilter);
    }




    //****************
    //MODEL SEX FILTER
    //****************

    public static String modelGender;
    @FXML
    void modelCheckBoxFemale(ActionEvent event) {
        new ModelGenderFilter().add("F");
    }

    @FXML
    void modelCheckBoxMale(ActionEvent event) {
        new ModelGenderFilter().add("M");
    }


    //************
    //LEVEL FILTER
    //************

    @FXML
    void levelCheckBoxAB(ActionEvent event) {
        new LevelFilter().add("AB");
    }

    @FXML
    void levelCheckBoxAdvanced(ActionEvent event) {
        new LevelFilter().add("A");
    }

    @FXML
    void levelCheckBoxAll(ActionEvent event) {
        new LevelFilter().add("ALL");
    }

    @FXML
    void levelCheckBoxBeginner(ActionEvent event) {
        new LevelFilter().add("B");
    }

    @FXML
    void levelCheckBoxIntermediate(ActionEvent event) {
        new LevelFilter().add("I");
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
