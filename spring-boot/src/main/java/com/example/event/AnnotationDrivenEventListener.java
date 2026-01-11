package com.example.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AnnotationDrivenEventListener {

  @EventListener(condition = "#event.success")
  public void handleContextStart(GenericSpringEvent<String> event) {
    //ContextStartedEvent
    System.out.println("Handling context started event.");
  }
}
