package csc305.gymnasticsApp.ui;

import csc305.gymnasticsApp.data.CardDatabase;
import csc305.gymnasticsApp.data.FavoriteCollection;
import csc305.gymnasticsApp.data.LessonPlan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

/**
 * Controller class for the template page, responsible for handling template selections.
 */
public class TemplatePageController {
    @FXML
    private Button templateOneButton;
    @FXML
    private Button templateTwoButton;
    @FXML
    private Button templateThreeButton;
    @FXML
    private Button templateFourButton;
    @FXML
    private Button templateFiveButton;

    private static String FILE_PATH;


    private void handleTemplateButton(File templateFile){
        LessonPlan.resetBoolean();
        HomePageController.loadPlan = new LessonPlan();
        GymnasticsAppBeta.setSelectedFileBool(templateFile);
        GymnasticsAppBeta.readFile();
        ArrayList<String> loadedLessonPlan = GymnasticsAppBeta.readFile();
        HomePageController.loadPlan.loadPlanFromFile(loadedLessonPlan);
        GymnasticsAppBeta.setLessonPlan(HomePageController.loadPlan);
        if (GymnasticsAppBeta.getLoaded()) {
            GymnasticsAppBeta.switchToPreviewPage();
        }
    }

    private boolean isRunningFromJar() {
        return CardDatabase.class.getResource("CardDatabase.class").toString().startsWith("jar:");
    }

    /**
     * Handles the action event for loading the all floor lesson plan.
     *
     * @param event The action event triggered by the button.
     */

    @FXML
    void handleTemplateOneButton(ActionEvent event) {
        if(!isRunningFromJar()) {
            File templateFile;
            String fileName = TemplatePageController.class.getResource("/templatePlans/templateOne.GymPlanFile").toString();
            templateFile = new File(fileName.substring(5));
            if (templateFile != null) {
                handleTemplateButton(templateFile);
            }
        }else{
            InputStream initialStream = this.getClass().getResourceAsStream("/templatePlans/templateOne.GymPlanFile");
            File targetFile = new File("src/main/resources/targetFile.tmp");

            try {
                java.nio.file.Files.copy(
                        initialStream,
                        targetFile.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                initialStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            handleTemplateButton(targetFile);
        }
    }

    @FXML
    void handleTemplateTwoButton(ActionEvent event) {
        if(!isRunningFromJar()) {
            File templateFile;
            String fileName = TemplatePageController.class.getResource("/templatePlans/templateTwo.GymPlanFile").toString();
            templateFile = new File(fileName.substring(5));
            if (templateFile != null) {
                handleTemplateButton(templateFile);
            }
        }else{
            InputStream initialStream = this.getClass().getResourceAsStream("/templatePlans/templateTwo.GymPlanFile");
            File targetFile = new File("src/main/resources/targetFile.tmp");

            try {
                java.nio.file.Files.copy(
                        initialStream,
                        targetFile.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                initialStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            handleTemplateButton(targetFile);
        }
    }

    @FXML
    void handleTemplateThreeButton(ActionEvent event) {
        if(!isRunningFromJar()) {
            File templateFile;
            String fileName = TemplatePageController.class.getResource("/templatePlans/templateThree.GymPlanFile").toString();
            templateFile = new File(fileName.substring(5));
            if (templateFile != null) {
                handleTemplateButton(templateFile);
            }
        }else{
            InputStream initialStream = this.getClass().getResourceAsStream("/templatePlans/templateThree.GymPlanFile");
            File targetFile = new File("src/main/resources/targetFile.tmp");

            try {
                java.nio.file.Files.copy(
                        initialStream,
                        targetFile.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                initialStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            handleTemplateButton(targetFile);
        }
    }

    @FXML
    void handleTemplateFourButton(ActionEvent event) {
        if(!isRunningFromJar()) {
            File templateFile;
            String fileName = TemplatePageController.class.getResource("/templatePlans/templateFour.GymPlanFile").toString();
            templateFile = new File(fileName.substring(5));
            if (templateFile != null) {
                handleTemplateButton(templateFile);
            }
        }else{
            InputStream initialStream = this.getClass().getResourceAsStream("/templatePlans/templateFour.GymPlanFile");
            File targetFile = new File("src/main/resources/targetFile.tmp");

            try {
                java.nio.file.Files.copy(
                        initialStream,
                        targetFile.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                initialStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            handleTemplateButton(targetFile);
        }
    }

    @FXML
    void handleTemplateFiveButton(ActionEvent event) {
        if(!isRunningFromJar()) {
            File templateFile;
            String fileName = TemplatePageController.class.getResource("/templatePlans/templateFive.GymPlanFile").toString();
            templateFile = new File(fileName.substring(5));
            if (templateFile != null) {
                handleTemplateButton(templateFile);
            }
        }else{
            InputStream initialStream = this.getClass().getResourceAsStream("/templatePlans/templateFive.GymPlanFile");
            File targetFile = new File("src/main/resources/targetFile.tmp");

            try {
                java.nio.file.Files.copy(
                        initialStream,
                        targetFile.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                initialStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            handleTemplateButton(targetFile);
        }
    }

    /**
     * Handles the action event for switching to the home page
     *
     * @param event The action event triggered by the button
     */
    @FXML
    void homeButtonController(ActionEvent event) {
        MainEditDisplayController.clearTreeCardItems();
        MainEditDisplayController.events.clear();
        MainEditDisplayController.resetButtons();
        GymnasticsAppBeta.setLessonPlan(new LessonPlan());
        GymnasticsAppBeta.switchToHomePage();
    }

    private String getFilePath() throws URISyntaxException {
        String filePath = null;
        if (isRunningFromJar()) {
            // Running from JAR
            filePath = new File(FavoriteCollection.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
        }
        return filePath;
    }

    /**
     * Initializes the controller
     */
    public void initialize(){
        try {
            FILE_PATH = getFilePath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        Image templateOneImage = new Image(TemplatePageController.class.getResource("/templatePlans/templateOneSS.png").toString());
        initializeImages(templateOneImage, templateOneButton);
        Image templateTwoImage = new Image(TemplatePageController.class.getResource("/templatePlans/templateTwoSS.png").toString());
        initializeImages(templateTwoImage, templateTwoButton);
        Image templateThreeImage = new Image(TemplatePageController.class.getResource("/templatePlans/templateThreeSS.png").toString());
        initializeImages(templateThreeImage, templateThreeButton);
        Image templateFourImage = new Image(TemplatePageController.class.getResource("/templatePlans/templateFourSS.png").toString());
        initializeImages(templateFourImage, templateFourButton);
        Image templateFiveImage = new Image(TemplatePageController.class.getResource("/templatePlans/templateFiveSS.png").toString());
        initializeImages(templateFiveImage, templateFiveButton);
    }

    /**
     * Initializes the background images for buttons
     *
     * @param image The image to be set as the background
     * @param button The button for which the background is set
     */
    private void initializeImages(Image image, Button button){
        button.setStyle("-fx-background-image: url('" + image.getUrl() + "'); -fx-background-size: cover; -fx-background-position: center;");
    }
}
