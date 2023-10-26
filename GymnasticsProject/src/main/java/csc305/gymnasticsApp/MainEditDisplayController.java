package csc305.gymnasticsApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;


public class MainEditDisplayController implements Initializable {
    @FXML
    private Button backButton;

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
    private TreeItem<String> eventOneItems = new TreeItem<>("Event 1");

    @FXML
    private TreeItem<String> eventTwoItems = new TreeItem<>("Event 2");



    @FXML
    void backButtonHandle(ActionEvent event) {GymnasticsAppBeta.switchToLessonPlan();
    }

    @FXML
    void previewButtonHandle(ActionEvent event) {GymnasticsAppBeta.switchToPreviewPage();
    }

    @FXML
    void goButtonHandle(ActionEvent event) {filterCards(drillSearchBar.getText().toLowerCase());
    }



    private void filterCards(String inputText) {
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


    private List<Button> getAllCardButtons() {
        List<Button> cardButtons = new ArrayList<>();
        for (int i = 0; i < cardFlowPane.getChildren().size(); i++) {
            if (cardFlowPane.getChildren().get(i) instanceof Button) {
                cardButtons.add((Button) cardFlowPane.getChildren().get(i));
            }
        }
        return cardButtons;
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        TreeItem<String> rootItem = new TreeItem<>("Root");
        rootItem.getChildren().addAll(eventOneItems, eventTwoItems);

        treeView.setShowRoot(false);
        treeView.setRoot(rootItem);
    }

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

    public void addCardToTreeView(MouseEvent event){
        Button cardButton = (Button) event.getSource();
        TreeItem<String> newCard = new TreeItem<>(cardButton.getId());
        eventOneItems.getChildren().addAll(newCard);

    }

}
