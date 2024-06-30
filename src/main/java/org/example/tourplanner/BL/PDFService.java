package org.example.tourplanner.BL;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import org.example.tourplanner.BL.models.LogModel;
import org.example.tourplanner.BL.models.TourModel;
import org.example.tourplanner.DAL.repositories.LogDAO;
import org.example.tourplanner.DefaultInjector;
import org.example.tourplanner.Injectable;

import java.util.List;
import java.util.Objects;

public class PDFService implements IPDFService, Injectable {
    private final LogDAO logDAO;

    public PDFService() {
        this.logDAO = DefaultInjector.getService(LogDAO.class);
    }

    @Override
    public void createTourListPDF(String filePath, TourModel tour) throws Exception {
        try (PdfWriter writer = new PdfWriter(filePath);
             PdfDocument pdfDoc = new PdfDocument(writer);
             Document document = new Document(pdfDoc)) {

            // Title
            document.add(new Paragraph("Tour Information:").setBold().setFontSize(14).setTextAlignment(TextAlignment.LEFT));

            // Table
            Table table = new Table(8);

            // Table Header
            addTableHeaderTour(table);

            // Table Rows
            addTableRowTour(table, tour);

            document.add(new Paragraph("Logs:").setBold().setFontSize(12).setTextAlignment(TextAlignment.LEFT));

            addLogsSection(document, tour);

            // Add table to document
            document.add(table);
        }
    }

    private void addLogsSection(Document document, TourModel tour) {
        List<LogModel> logs = logDAO.findByTour(tour);

        if (logs.isEmpty()) {
            document.add(new Paragraph("No logs available for this tour."));
        } else {
            // Table
            Table table = new Table(7);
            addTableHeaderLog(table);

            // Table Rows
            for (LogModel log : logs) {
                addTableRowLog(table, log);
            }

            // Add table to document
            document.add(table);
        }
    }

    private void addTableHeaderTour(Table table) {
        table.addHeaderCell(createHeaderCell("Name"));
        table.addHeaderCell(createHeaderCell("To"));
        table.addHeaderCell(createHeaderCell("From"));
        table.addHeaderCell(createHeaderCell("Time"));
        table.addHeaderCell(createHeaderCell("Route Information"));
        table.addHeaderCell(createHeaderCell("Tour Description"));
        table.addHeaderCell(createHeaderCell("Transport Type"));
        table.addHeaderCell(createHeaderCell("Distance"));
    }

    private void addTableHeaderLog(Table table) {
        table.addHeaderCell(createHeaderCell("Id"));
        table.addHeaderCell(createHeaderCell("Comment"));
        table.addHeaderCell(createHeaderCell("Datetime"));
        table.addHeaderCell(createHeaderCell("Difficulty"));
        table.addHeaderCell(createHeaderCell("Rating"));
        table.addHeaderCell(createHeaderCell("Total Distance"));
        table.addHeaderCell(createHeaderCell("Total Time"));
    }

    private Cell createHeaderCell(String text) {
        return new Cell().add(new Paragraph(text).setBold()
                .setTextAlignment(TextAlignment.CENTER).setBackgroundColor(ColorConstants.LIGHT_GRAY));
    }

    private void addTableRowTour(Table table, TourModel tourModel) {
        table.addCell(createCell(tourModel.getName()));
        table.addCell(createCell(tourModel.getTo()));
        table.addCell(createCell(tourModel.getFrom()));
        table.addCell(createCell(tourModel.getTime()));
        table.addCell(createCell(tourModel.getRouteInformation()));
        table.addCell(createCell(tourModel.getTourDescription()));
        table.addCell(createCell(tourModel.getTransportType()));
        table.addCell(createCell(String.valueOf(tourModel.getDistance())));
    }

    private void addTableRowLog(Table table, LogModel logModel) {
        table.addCell(createCell(String.valueOf(logModel.getId())));
        table.addCell(createCell(logModel.getComment()));
        table.addCell(createCell(logModel.getDateTime()));
        table.addCell(createCell(String.valueOf(logModel.getDifficulty())));
        table.addCell(createCell(String.valueOf(logModel.getRating())));
        table.addCell(createCell(String.valueOf(logModel.getTotalDistance())));
        table.addCell(createCell(logModel.getTotalTime()));
    }

    private Cell createCell(String content) {
        return new Cell().add(new Paragraph(Objects.toString(content, "")).setTextAlignment(TextAlignment.CENTER));
    }
}

