package com.apress.prospring4.ch2;

import java.io.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class MessageSupportFactory {
    private static MessageSupportFactory instance;

    private Properties props;
    private MessageRenderer renderer;
    private MessageProvider provider;

    private MessageSupportFactory() {
        props = new Properties();

        try {


            // ok
            //props.load(ClassLoader.getSystemResourceAsStream("com/apress/prospring4/ch2/msf.properties"));


            // ok 在MessageSupportFactory所在目录找msf1.properties
            // props.load(this.getClass().getResourceAsStream("msf1.properties"));
            // props.load(MessageSupportFactory.class.getResourceAsStream("msf1.properties"));


            String rendererClass = props.getProperty("renderer.class");
            String providerClass = props.getProperty("provider.class");

            ResourceBundle bundle = ResourceBundle.getBundle("com/apress/prospring4/ch2/msf");
            //ResourceBundle bundle = ResourceBundle.getBundle("msf");
            rendererClass = bundle.getString("renderer.class");
            providerClass = bundle.getString("provider.class");

            renderer = (MessageRenderer) Class.forName(rendererClass).newInstance();
            provider = (MessageProvider) Class.forName(providerClass).newInstance();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static {
        instance = new MessageSupportFactory();
    }

    public static MessageSupportFactory getInstance() {
        return instance;
    }

    public MessageRenderer getMessageRenderer() {
        return renderer;
    }

    public MessageProvider getMessageProvider() {
        return provider;
    }
}
