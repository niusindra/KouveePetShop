package com.kel1.kouveepetshop;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyPdf {
    //write method takes two parameter pdf name and content
    //return true if pdf successfully created
    public Boolean write(String fname) {
        try {
            //Create file path for Pdf
            String fpath = "/SD card/" + fname + ".pdf";
            File file = new File(fpath);
            if (!file.exists()) {
                file.createNewFile();
            }
            // To customise the text of the pdf
            // we can use FontFamily
            Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN,
                    12, Font.BOLD, new BaseColor(0, 0, 0));
            Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN,
                    12);
            // create an instance of itext document
            Document document = new Document();
            PdfWriter.getInstance(document,
                    new FileOutputStream(file.getAbsoluteFile()));
            document.open();
            //using add method in document to insert a paragraph
            document.add(new Paragraph("My First Pdf !"));
            document.add(new Paragraph("Hello World"));
            // close document
            document.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (DocumentException e) {
            e.printStackTrace();
            return false;
        }
    }}