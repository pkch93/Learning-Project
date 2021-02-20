package edu.pkch.patterns.observer;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class AmericaStockExchange implements Observer<StockData> {
    private double kospi;
    private double kosdaq;

    @Override
    public void update(Subject<StockData> subject, StockData stockData) {
        this.kospi = stockData.getKospi();
        this.kosdaq = stockData.getKosdaq();

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/New_York"));
        String currentTimeDisplay = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:dd:ss"));

        System.out.printf("%s: kospi - %.1f / kosdaq - %.1f%n", currentTimeDisplay, this.kospi, this.kosdaq);
    }
}
