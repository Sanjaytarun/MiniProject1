package utils;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;

public class PdfReportUtil {

    static String path = "reports/TestReport.pdf";

    static PdfWriter writer;
    static PdfDocument pdf;
    static Document document;

    // Create PDF
    public static void startReport() {
        try {
            File dir = new File("reports");
            if (!dir.exists()) {
                dir.mkdir();
            }

            writer = new PdfWriter(path);
            pdf = new PdfDocument(writer);
            document = new Document(pdf);

            document.add(new Paragraph("ROYAL CARIBBEAN TEST REPORT"));
            document.add(new Paragraph("===================================="));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  Add Step Log
    public static void log(String text) {
        document.add(new Paragraph(text));
    }

    //  Close PDF
    public static void endReport() {
        document.close();
    }
}
