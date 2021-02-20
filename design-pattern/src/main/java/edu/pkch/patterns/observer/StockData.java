package edu.pkch.patterns.observer;

public class StockData {
    private final double kospi;
    private final double kosdaq;

    public StockData(double kospi, double kosdaq) {
        this.kospi = kospi;
        this.kosdaq = kosdaq;
    }

    public double getKospi() {
        return kospi;
    }

    public double getKosdaq() {
        return kosdaq;
    }
}
