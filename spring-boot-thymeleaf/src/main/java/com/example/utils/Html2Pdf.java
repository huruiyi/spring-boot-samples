package com.example.utils;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Base64Utils;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

public class Html2Pdf {

  public static String FONT = "src/main/resources/fonts/微软雅黑Bbold.ttf";

  public static String getLogoBase64() throws IOException {
    ClassPathResource resource = new ClassPathResource("templates/logo/cosco-shipping-lines.png");
    InputStream inputStream = resource.getInputStream();
    return "data:image/png;base64,".concat(Base64Utils.encodeToString(inputStream.readAllBytes()));
  }

  public static void generator(ByteArrayOutputStream outputStream, String htmlStr) throws DocumentException, IOException {
    ITextRenderer renderer = new ITextRenderer();
    SharedContext sharedContext = renderer.getSharedContext();
    sharedContext.setPrint(true);
    sharedContext.setInteractive(false);

    ITextFontResolver fontResolver = renderer.getFontResolver();
    fontResolver.addFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

    renderer.setDocumentFromString(htmlStr);
    renderer.layout();
    renderer.createPDF(outputStream);
    renderer.finishPDF();
  }

  public static void generator(OutputStream outputStream, String htmlStr) throws DocumentException, IOException {
    ITextRenderer renderer = new ITextRenderer();
    SharedContext sharedContext = renderer.getSharedContext();
    sharedContext.setPrint(true);
    sharedContext.setInteractive(false);

    ITextFontResolver fontResolver = renderer.getFontResolver();
    fontResolver.addFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

    renderer.setDocumentFromString(htmlStr);
    renderer.layout();
    renderer.createPDF(outputStream);
    renderer.finishPDF();
  }

}
