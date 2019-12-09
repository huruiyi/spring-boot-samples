package com.example.Unfiled;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class PdfDemo1 {

    public static String getPdfFileText(String fileName) throws IOException {
        PdfReader reader = new PdfReader(fileName);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        StringBuffer buff = new StringBuffer();
        TextExtractionStrategy strategy;
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i,
                    new SimpleTextExtractionStrategy());
            buff.append(strategy.getResultantText());
        }
        return buff.toString();
    }


    public static void main(String[] args) throws IOException {

        System.out.println(getPdfFileText("bb.pdf"));

        System.out.println("********************************");
        //获取 PdfReader 对象,文件名称要是在classpath中的文件
        String src = "bb.pdf";
        PdfReader reader = new PdfReader(src);

        System.out.println(reader.getFileLength());

        //获取pdf中页数
        int pageCount = reader.getNumberOfPages();
        System.out.println("page count : " + pageCount);


        int totalSize = 0;
        int flag = 0;
        for (int i = 1; i <= pageCount; i++) {

            int length = reader.getPageContent(i).length;
            ArrayList<Chunk> chunks = reader.getPageSize(i).getChunks();

            System.out.println(length);
            totalSize += length;

         /*   PrintWriter w = new PrintWriter(new File("abcabc.txt"));
            PdfContentReaderTool.listContentStreamForPage(reader,i,w);
*/

           /* int pageLength = reader.getPageContent(i).length;

            System.out.println("PageContent:----" + new String(reader.getPageContent(i)));
            System.out.println("PageContentSize:----" + pageLength);
            totalSize += pageLength;

            //得到pdf每一页的字典对象
            PdfDictionary dictionary = reader.getPageN(i);
            //通过RESOURCES得到对应的字典对象
            PdfDictionary res = (PdfDictionary) PdfReader.getPdfObject(dictionary.get(PdfName.RESOURCES));
            //得到XOBJECT图片对象
            PdfDictionary xobj = (PdfDictionary) PdfReader.getPdfObject(res.get(PdfName.XOBJECT));

            if (xobj != null) {

                for (Iterator it = xobj.getKeys().iterator(); it.hasNext(); ) {

                    flag++;
                    PdfObject obj = xobj.get((PdfName) it.next());
                    PdfObject object = reader.getPdfObject(obj);
                    if (object.isStream()) {
                        PRStream prstream = (PRStream) object;
                        System.out.println("Stream:----" + prstream.getLength());
                        totalSize += prstream.getLength();
                    }


                }
            }*/

//            String content = PdfTextExtractor.getTextFromPage(reader, i);
//            System.out.println(content.length());
//
//            int length = PdfTextExtractor.getTextFromPage(reader, i).getBytes().length;
//            System.out.println("*************************" + length + "************************");
//
//            totalSize += length;


        }


        System.out.println(totalSize);
        System.out.println("diff:----" + (reader.getFileLength() - totalSize));
        // System.out.println(":flag----" + flag);

    }
}
