package csc305.gymnasticsApp;

import javafx.event.ActionEvent;
import javafx.print.PrinterJob;
import javafx.scene.layout.AnchorPane;

public class PrintLessonPlan {

    void printLessonPlan(ActionEvent event){
        AnchorPane previewPane = new AnchorPane();
        PrinterJob job = PrinterJob.createPrinterJob();
        //previewPane.getChildren().addAll();
        if(job != null /*&& job.showPrintDialog(previewPane.getScene().getWindow())*/) {
            boolean success = job.showPrintDialog(null);
            if(success) {
                job.endJob();
            }
        }
    }


}
