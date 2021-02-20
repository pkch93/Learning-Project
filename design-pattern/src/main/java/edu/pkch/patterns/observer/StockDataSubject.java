package edu.pkch.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class StockDataSubject implements Subject<StockData> {
    private final List<Observer<StockData>> stockExchanges;
    private StockData stockData;

    public StockDataSubject() {
        this(new ArrayList<>());
    }

    public StockDataSubject(List<Observer<StockData>> stockExchanges) {
        this.stockExchanges = stockExchanges;
    }

    @Override
    public void addObserver(Observer<StockData> observer) {
        stockExchanges.add(observer);
    }

    @Override
    public void removeObserver(Observer<StockData> observer) {
        stockExchanges.remove(observer);
    }

    @Override
    public void notifyObservers() {
        stockExchanges.forEach(
                stockExchange -> stockExchange.update(this, stockData)
        );
    }

    @Override
    public void update(StockData stockData) {
        this.stockData = stockData;
        notifyObservers();
    }
}
