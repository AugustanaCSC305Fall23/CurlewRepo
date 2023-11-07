package csc305.gymnasticsApp;

import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.print.PageLayout;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.SnapshotParameters;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PrintLessonPlan {

    public static void printPlan(Node printNode, VBox eventPreviewVBox) {
        Printer printer = Printer.getDefaultPrinter();
        PrinterJob job = PrinterJob.createPrinterJob(printer);

        if (job != null && job.showPrintDialog(eventPreviewVBox.getScene().getWindow())) {
            PageLayout pageLayout = job.getJobSettings().getPageLayout();

            double scaleX = pageLayout.getPrintableWidth() / printNode.getBoundsInParent().getWidth();
            double scaleY = pageLayout.getPrintableHeight() / printNode.getBoundsInParent().getHeight();
            double scale = Math.min(scaleX, scaleY);

            printNode.getTransforms().add(new javafx.scene.transform.Scale(scale, scale));

            // Create an image of the AnchorPane with higher DPI for better resolution
            SnapshotParameters snapshotParams = new SnapshotParameters();
            snapshotParams.setFill(Color.WHITE); // Set the background color if needed
            snapshotParams.setTransform(javafx.scene.transform.Scale.scale(scale, scale));
            double dpi = 300; // Adjust the DPI value as needed for better resolution
            snapshotParams.setTransform(javafx.scene.transform.Scale.scale(dpi / 72, dpi / 72));
            WritableImage writableImage = printNode.snapshot(snapshotParams, null);

            ImageView imageView = new ImageView(writableImage);
            imageView.setFitWidth(pageLayout.getPrintableWidth());
            imageView.setFitHeight(pageLayout.getPrintableHeight());

            // Print the ImageView
            boolean success = job.printPage(imageView);
            if (success) {
                job.endJob();
            }

            printNode.getTransforms().clear(); // Clear any transformations applied after printing
        }
    }
}
