package csc305.gymnasticsApp;

import csc305.gymnasticsApp.CardFilter.CombineAndFilter;
import csc305.gymnasticsApp.CardFilter.EventFilter;
import csc305.gymnasticsApp.CardFilter.GenderFilter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

//import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Array;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;


public class MainEditDisplayController implements Initializable {
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

    public String[] cardParentEvent = {"Event1", "Event2"};



    @FXML
    void backButtonHandle(ActionEvent event) {GymnasticsAppBeta.switchToLessonPlan();
    }


    @FXML
    void previewButtonHandle(ActionEvent event) {GymnasticsAppBeta.switchToPreviewPage();
    }

    @FXML
    void goButtonHandle(ActionEvent event) {
        filterCards(drillSearchBar.getText().toLowerCase());
    }

    @FXML
    void openFilterMenu(ActionEvent event){
        filterMenu.setVisible(true);
    }

    @FXML
    void closeFilterMenu(ActionEvent event){
        filterMenu.setVisible(false);
    }


    private void filterCards(String inputText) {
        inputText = inputText.replaceAll("\\s+", "");
        List<Button> cardButtons = getAllCardButtons();
        List<Button> visibleButtons = new ArrayList<>();
        List<Button> hiddenButtons = new ArrayList<>();

        for (Button cardButton : cardButtons) {
            String buttonText = cardButton.getId().toLowerCase();
            if (buttonText.contains(inputText)) {
                visibleButtons.add(cardButton);
                cardButton.setVisible(true);
            } else {
                hiddenButtons.add(cardButton);
                cardButton.setVisible(false);
            }
        }

        cardFlowPane.getChildren().clear();
        cardFlowPane.getChildren().addAll(visibleButtons);
        cardFlowPane.getChildren().addAll(hiddenButtons);
    }

    private void addCardsToFlowPane(){
        try {
            cardFlowPane.getChildren().clear();
            for(Card card : CardDatabase.getAllCards()) {
                Image image = new Image(new FileInputStream("src/main/resources/GymSoftwarePics" + "/" +
                        card.getPackFolder().toUpperCase() + "Pack/" +
                        card.getImage()));
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
                cardButton.setOnMouseClicked(event -> addCardToTreeView(cardButton, card));
                cardFlowPane.getChildren().add(cardButton);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    private List<Button> getAllCardButtons() {
        List<Button> cardButtons = new ArrayList<>();
        for (int i = 0; i < cardFlowPane.getChildren().size(); i++) {
            if (cardFlowPane.getChildren().get(i) instanceof Button) {
                cardButtons.add((Button) cardFlowPane.getChildren().get(i));
            }
        }
        return cardButtons;
    }


    // Initializes the treemap on the mainEditDisplay screen
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        TreeItem<String> rootItem = new TreeItem<>("Root");
        rootItem.getChildren().addAll(eventOneItems, eventTwoItems);

        treeView.setShowRoot(false);
        treeView.setRoot(rootItem);
        addCardsToFlowPane();
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
                if(parent.toString().equals(cardParentEvent[0])){
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

    //*************
    //GENDER FILTER
    //*************
    public static String gender;
    @FXML
    void setChangedTextBoxFemale(ActionEvent event) {
        gender = "F";
        new GenderFilter().add(gender);
    }

    @FXML
    void setChangedTextBoxMale(ActionEvent event) {
        gender = "M";
        new GenderFilter().add(gender);    }

    @FXML
    void setChangedTextBoxNeut(ActionEvent event) {
        gender = "N";
        new GenderFilter().add(gender);    }


    //************
    //EVENT FILTER
    //************
    @FXML
    void allCheckBox(ActionEvent event) {
        new EventFilter().add("ALL");
    }

    @FXML
    void beamCheckBox(ActionEvent event) {
        new EventFilter().add("Beam");
    }

    @FXML
    void floorCheckBox(ActionEvent event) {
        new EventFilter().add("Floor");
    }

    @FXML
    void strengthCheckBox(ActionEvent event) {
        new EventFilter().add("Strength");
    }

    @FXML
    void trampCheckBox(ActionEvent event) {
        new EventFilter().add("Tramp");
    }

    @FXML
    void unevenBarsCheckBox(ActionEvent event) {
        new EventFilter().add("Uneven Bars");
    }

    @FXML
    void vaultCheckBox(ActionEvent event) {
        new EventFilter().add("Vault");
    }




    @FXML
    void setFilterController(ActionEvent event) {
        new CombineAndFilter();
    }

}
