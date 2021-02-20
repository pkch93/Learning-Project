package edu.pkch.patterns.observer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class KoreaStockExchange implements Observer<StockData> {
    private double kospi;
    private double kosdaq;

    @Override
    public void update(Subject<StockData> subject, StockData stockData) {
        this.kospi = stockData.getKospi();
        this.kosdaq = stockData.getKosdaq();

        LocalDateTime now = LocalDateTime.now();
        String currentTimeDisplay = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:dd:ss"));

        System.out.printf("%s: 코스피 - %f / 코스닥 - %f%n", currentTimeDisplay, this.kospi, this.kosdaq);
    }
}
