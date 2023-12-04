package csc305.gymnasticsApp.data;

import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.print.PageLayout;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.SnapshotParameters;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PrintLessonPlan {

    public static void printPlan(Node printNode, ScrollPane mainScrollPane) {
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

                    double scaleX = pageLayout.getPrintableWidth() / eventPreviewVBox.getBoundsInParent().getWidth();
                    double scaleY = pageLayout.getPrintableHeight() / eventPreviewVBox.getBoundsInParent().getHeight();
                    double scale = Math.min(scaleX, scaleY);

                    eventPreviewVBox.getTransforms().add(new javafx.scene.transform.Scale(scale, scale));

                    // Create an image of the VBox with higher DPI for better resolution
                    SnapshotParameters snapshotParams = new SnapshotParameters();
                    snapshotParams.setFill(Color.WHITE);
                    snapshotParams.setTransform(javafx.scene.transform.Scale.scale(scale, scale));
                    double dpi = 300;
                    snapshotParams.setTransform(javafx.scene.transform.Scale.scale(dpi / 72, dpi / 72));
                    WritableImage writableImage = eventPreviewVBox.snapshot(snapshotParams, null);

                    ImageView imageView = new ImageView(writableImage);
                    imageView.setFitWidth(pageLayout.getPrintableWidth());
                    imageView.setFitHeight(pageLayout.getPrintableHeight());

                    // Print the ImageView
                    boolean success = job.printPage(imageView);

                    eventPreviewVBox.getTransforms().clear(); // Clear any transformations applied after printing

                    if (!success) {
                        break; // Break the loop if printing is unsuccessful
                    }
                }
            }

            job.endJob();
        }
    }
}
