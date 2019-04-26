package com.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

/**
 * 公司：TBK
 * 作者：vic
 * 文件名：PdfSplit
 * 日期：2019/4/22 16:02
 **/
public class PdfSplit {
    public static void main(String[] args) throws IOException, DocumentException {

        File outputFile = new File("C:\\Users\\vic\\Downloads\\Test\\output2.pdf");
        PdfReader reader = new PdfReader("C:\\Users\\vic\\Downloads\\Test\\1.pdf");

        int numberOfPages = reader.getNumberOfPages();

        int total = 0;
        Document document = new Document(reader.getPageSizeWithRotation(1));
        PdfCopy writer = null;
        PdfImportedPage page = null;
        int limit = 400 * 1024;
        for (int i = 1; i <= numberOfPages; i++) {
            writer = new PdfCopy(document, new FileOutputStream(outputFile));
            document.open();
            page = writer.getImportedPage(reader, i);
            writer.addPage(page);
            int currentDocumentSize = writer.getCurrentDocumentSize();
            if (currentDocumentSize > limit) {
                System.out.println(currentDocumentSize + "，超出限制。。");
            } else {
                System.out.println(currentDocumentSize);
            }
            total += currentDocumentSize;

            document.close();
        }
        if (writer != null) {
            writer.close();
        }
        outputFile.delete();
        System.out.println("总页数：" + numberOfPages);
        System.out.println("实际大小：" + reader.getFileLength());
        System.out.println("拆分大小：" + total);
    }


}
