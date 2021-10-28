package com.apress.prospring5.ch3.renderer;

import com.apress.prospring5.ch3.provider.MessageProvider;

public interface MessageRenderer {
    void render();
    void setMessageProvider(MessageProvider provider);
    MessageProvider getMessageProvider();
}
