package com.example.main;

import com.example.util.ProducerUtil;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main{

    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        while(true){
//            System.out.println ("生产者生产了一条消息------------");
            logger.info (new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss").format (new Date ()).toString () + "生产者生产了一条消息------------");
            ProducerUtil.producer ("my kafka send message : "+(new SimpleDateFormat ("yyyyMMddHHmmss").format (new Date ()).toString ()));
            try {
                Thread.sleep (3000L);
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
        }


    }

}
