package csc305.gymnasticsApp;

import csc305.gymnasticsApp.CardFilter.CombineAndFilter;
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
    public TreeItem<String> eventOneItems = new TreeItem<>("Event 1");

    @FXML
    public TreeItem<String> eventTwoItems = new TreeItem<>("Event 2");



    @FXML
    void backButtonHandle(ActionEvent event) {GymnasticsAppBeta.switchToLessonPlan();
    }

    @FXML
    void testButtonHandle(ActionEvent event) {
        addCardsToFlowPane();
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
    }

    //Manages
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
                deleteCardFromTreeView(event);
            }
        }
    }

    public void deleteCardFromTreeView(MouseEvent event){
        TreeItem<String> selectedItem = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        TreeItem<String> parent = selectedItem.getParent();
        parent.getChildren().remove(selectedItem);
    }

    public void addCardToTreeView(MouseEvent event) {
        Button cardButton = (Button) event.getSource();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Add Card to Event");
        alert.setHeaderText("Select the event to add the card to");

        ButtonType eventOneButton = new ButtonType("Event 1");
        ButtonType eventTwoButton = new ButtonType("Event 2");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(eventOneButton, eventTwoButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();

        result.ifPresent(buttonType -> {
            if (buttonType == eventOneButton) {
                TreeItem<String> newCard = new TreeItem<>(cardButton.getId().substring(cardButton.getId().length() - 5));
                eventOneItems.getChildren().add(newCard);
            } else if (buttonType == eventTwoButton) {
                TreeItem<String> newCard = new TreeItem<>(cardButton.getId().substring(cardButton.getId().length() - 5));
                eventTwoItems.getChildren().add(newCard);
            }
        });
        eventOneItems.setExpanded(true);
        eventTwoItems.setExpanded(true);

    }

    private CheckBox genderCheckBox;
    @FXML
    private CheckBox checkBoxFemale;

    @FXML
    private CheckBox checkBoxMale;

    @FXML
    private CheckBox checkBoxNeutral;

    @FXML
    void setChangedTextBox(ActionEvent event) {
        CheckBox selectedCheckBox = (CheckBox) event.getSource();
        genderCheckBox = selectedCheckBox;

        if (selectedCheckBox.isSelected()) {
            checkBoxFemale.setSelected(false);
            checkBoxNeutral.setSelected(false);
            checkBoxMale.setSelected(false);
            selectedCheckBox.setSelected(true);
            new GenderFilter(getGenderCheckBox());
        }
    }

    public String getGenderCheckBox() {
        if(genderCheckBox == checkBoxFemale) {
            return "F";
        } else if (genderCheckBox == checkBoxNeutral) {
            return "N";
        } else {
            return "M";
        }
    }

    @FXML
    void setFilterController(ActionEvent event) {
        new CombineAndFilter();
    }

}
