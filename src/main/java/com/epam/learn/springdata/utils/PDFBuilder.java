package com.epam.learn.springdata.utils;


import com.epam.learn.springdata.model.User;
import com.epam.learn.springdata.utils.AbstractITextPdfView;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

public class PDFBuilder extends AbstractITextPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
                                    PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
        List<User> listUsers = (List<User>) model.get("listUsers");

        doc.add(new Paragraph("Recommended books for Spring framework"));

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {3.0f, 2.0f});
        table.setSpacingBefore(10);

        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(5);

        // write table header
        cell.setPhrase(new Phrase("User Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("User Email", font));
        table.addCell(cell);

        // write table row data
        for (User aUser : listUsers) {
            table.addCell(aUser.getName());
            table.addCell(aUser.getEmail());
        }

        doc.add(table);

    }

}