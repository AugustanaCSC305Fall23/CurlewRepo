package csc305.gymnasticsApp.data;

import javafx.print.PageOrientation;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.print.PageLayout;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.SnapshotParameters;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.Writer;

/**
 * The PrintLessonPlan class provides functionality for printing lesson plans
 */
public class PrintLessonPlan {
    /**
     * Prints the specified node representing a lesson plan
     *
     * @param printNode The node to be printed
     * @param mainScrollPane The ScrollPane containing the lesson plan
     */
    public static void printPlan(Node printNode, ScrollPane mainScrollPane, Boolean isTextOnly) {
        Printer printer = Printer.getDefaultPrinter();
        PrinterJob job = PrinterJob.createPrinterJob(printer);

        if (job != null && job.showPrintDialog(mainScrollPane.getScene().getWindow())) {
            PageLayout pageLayout = job.getJobSettings().getPageLayout();

            // Assuming that the FlowPane is the first child of the ScrollPane
            FlowPane mainFlowPane = (FlowPane) mainScrollPane.getContent();

            // Iterate through the children of FlowPane and find VBoxes
            for (Node flowPaneChild : mainFlowPane.getChildren()) {
                if (flowPaneChild instanceof VBox) {
                    VBox eventPreviewVBox = (VBox) flowPaneChild;
                    double originalRotation = eventPreviewVBox.getRotate();

                    double scaleX = pageLayout.getPrintableWidth() / eventPreviewVBox.getBoundsInParent().getWidth();
                    double scaleY = pageLayout.getPrintableHeight() / eventPreviewVBox.getBoundsInParent().getHeight();
                    double scale = Math.min(scaleX, scaleY);
                    if(!isTextOnly) {
                        eventPreviewVBox.setRotate(90);
                        eventPreviewVBox.getTransforms().add(new javafx.scene.transform.Scale(scale, scale));
                    } else{
                        eventPreviewVBox.setStyle(
                                "-fx-border-color: transparent; " +  // Makes the border transparent
                                "-fx-background-color: transparent; " +  // Makes the background transparent
                                "-fx-padding: 0; " +  // Removes any padding
                                "-fx-border-width: 0;" + // Ensures the border width is zero
                                "-fx-border-radius: 0;" // Resetting border radius
                        );

                    }
                    // Create an image of the VBox with higher DPI for better resolution
                    SnapshotParameters snapshotParams = new SnapshotParameters();
                    snapshotParams.setFill(Color.WHITE);
                    snapshotParams.setTransform(javafx.scene.transform.Scale.scale(scale, scale));
                    double dpi = 300;//changed from 300
                    snapshotParams.setTransform(javafx.scene.transform.Scale.scale(dpi / 72, dpi / 72));
                    WritableImage writableImage = eventPreviewVBox.snapshot(snapshotParams, null);


                    ImageView imageView = new ImageView(writableImage);
                    imageView.setFitWidth(pageLayout.getPrintableWidth());
                    imageView.setFitHeight(pageLayout.getPrintableHeight());


                    // Print the ImageView
                    boolean success = job.printPage(imageView);

                    if(!isTextOnly) {
                        eventPreviewVBox.setRotate(originalRotation);
                        eventPreviewVBox.getTransforms().clear(); // Clear any transformations applied after printing
                    }
                    if (!success) {
                        break; // Break the loop if printing is unsuccessful
                    }
                }
            }

            job.endJob();
        }
    }
}
