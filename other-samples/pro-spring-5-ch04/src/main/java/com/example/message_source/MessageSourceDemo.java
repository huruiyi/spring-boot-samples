package com.example.message_source;

import java.util.Locale;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MessageSourceDemo {

  public static void main(String... args) {
    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    ctx.load("classpath:spring/message-source-context.xml");
    ctx.refresh();

    Locale english = Locale.ENGLISH;
    Locale german = new Locale("de", "DE");

    System.out.println(ctx.getMessage("msg", null, english));
    System.out.println(ctx.getMessage("msg", null, german));

    System.out.println(ctx.getMessage("nameMsg", new Object[]{"John", "Mayer"}, english));
    System.out.println(ctx.getMessage("nameMsg", new Object[]{"John", "Mayer"}, german));

    ctx.close();
  }
}
