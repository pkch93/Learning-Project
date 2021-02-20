package edu.pkch.patterns.observer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class ObserverTest {

    @Test
    void observer() throws InterruptedException {
        Observer<StockData> americaStockExchange = new AmericaStockExchange();
        Observer<StockData> koreaStockExchange = new KoreaStockExchange();

        Subject<StockData> stockPublisher = new StockDataSubject(Arrays.asList(
                americaStockExchange, koreaStockExchange
        ));

        stockPublisher.update(new StockData(3000.0, 928.0));
        Thread.sleep(1000);
        stockPublisher.update(new StockData(3010.0, 930.0));
        Thread.sleep(1000);
        stockPublisher.update(new StockData(3012.0, 910.0));
        Thread.sleep(1000);
        stockPublisher.update(new StockData(3015.0, 915.0));
        Thread.sleep(1000);
        stockPublisher.update(new StockData(3010.0, 910.0));
    }
}
