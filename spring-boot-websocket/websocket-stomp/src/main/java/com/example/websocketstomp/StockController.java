package com.example.websocketstomp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * handle incoming messages (@MessageMapping) and
 * publish outgoing messages (convertAndSend())
 *
 * SimpMessagingTemplate: provides methods for sending messages to a user
 *
 * @MessageMapping: Handler methods which are annotated with this
 * annotation are allowed to have flexible signatures.
 */
@Controller
public class StockController {

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private List<Stock> stocks = new ArrayList<>();

    public StockController() {
        stocks.add(new Stock("VMW", 1.00));
        stocks.add(new Stock("EMC", 1.00));
        stocks.add(new Stock("GOOG", 1.00));
        stocks.add(new Stock("IBM", 1.00));
    }

    @MessageMapping("/addStock")
    public void addStock(final Stock stock) {
        stocks.add(stock);
        broadcastUpdatedPrices();
    }

    private void broadcastUpdatedPrices() {
        for (Stock stock : stocks) {
            stock.setPrice(updateStockPrice(stock.getPrice()));
            stock.setDateTime(LocalDateTime.now());
        }

        // send stocks to all subscribers of /topic/price
        simpMessagingTemplate.convertAndSend("/topic/price", stocks);
    }

    private double updateStockPrice(final double oldPrice) {
        double random = new Random().nextDouble() - 0.5;
        return oldPrice + random;
    }

    // call brodcast of stocks every  second
    @PostConstruct
    private void broadcastPeriodically() {
        taskScheduler.scheduleAtFixedRate(this::broadcastUpdatedPrices, 2000);
    }
}
