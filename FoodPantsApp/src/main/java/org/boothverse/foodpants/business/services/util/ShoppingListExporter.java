package org.boothverse.foodpants.business.services.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.business.services.exceptions.PantsExportShoppingListException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.List;

public class ShoppingListExporter {

    private static Logger logger = LogManager.getLogger(ShoppingListExporter.class);
    private static final String FONT = FontFactory.HELVETICA_BOLD;

    public record ShoppingListItem(String name, String quantity) {}

    public void export(Path path, List<ShoppingListItem> items) throws PantsExportShoppingListException {
        logger.info("Exporting shopping list to " + path);
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(path.toString()));
            document.open();

            logger.info("Setting up exported file header");
            // Title
            Font titleFont = FontFactory.getFont(FONT, 18, BaseColor.BLACK);
            Paragraph title = new Paragraph("Shopping List", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));

            // Content
            PdfPTable table = new PdfPTable(new float[] { 20, 80 });
            addRows(table, items);
            document.add(table);

            document.close();
        } catch (FileNotFoundException | DocumentException e) {
            logger.warn("Could not export shopping list to " + path);
            throw new PantsExportShoppingListException(e);
        }
    }

    private void addRows(PdfPTable table, List<ShoppingListItem> items) {
        logger.info("Adding shopping list items to file");
        Font font = FontFactory.getFont(FONT, 12, BaseColor.BLACK);
        items.forEach(item -> {
            PdfPCell quantity = new PdfPCell();
            quantity.setBorderColor(BaseColor.WHITE);
            Phrase p1 = new Phrase(item.quantity);
            p1.setFont(font);
            quantity.setPhrase(p1);
            table.addCell(quantity);

            PdfPCell name = new PdfPCell();
            name.setBorderColor(BaseColor.WHITE);
            Phrase p2 = new Phrase(item.name);
            p2.setFont(font);
            name.setPhrase(p2);
            table.addCell(name);
            logger.info("Added " + item.name + " to file");
        });
    }

}
