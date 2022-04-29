package org.boothverse.foodpants.business.services.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.boothverse.foodpants.business.services.exceptions.PantsExportShoppingListException;
import org.boothverse.foodpants.persistence.FoodInstance;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class ShoppingListExporter {

    public record ShoppingListItem(String name, String quantity) {}

    public void export(Path path, List<FoodInstance> items) throws PantsExportShoppingListException {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(path.toString()));
            document.open();

            // Title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            Paragraph title = new Paragraph("Shopping List", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));

            // Content
            //PdfPTable table = new PdfPTable(2);
            //addRows(table);
            //addRows(table);
            //document.add(table);

            document.close();
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
            throw new PantsExportShoppingListException(e);
        }
    }

    private void addRows(PdfPTable table, List<ShoppingListItem> items) {
        items.forEach(item -> {
            PdfPCell quantity = new PdfPCell();
            quantity.setBorderColor(BaseColor.WHITE);
            quantity.setPhrase(new Phrase(item.quantity));
            table.addCell(quantity);

            PdfPCell name = new PdfPCell();
            quantity.setBorderColor(BaseColor.WHITE);
            quantity.setPhrase(new Phrase(item.name));
            table.addCell(name);
        });
    }

}
