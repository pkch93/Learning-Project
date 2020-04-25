package edu.pkch.movie.domain;

import java.util.Objects;

public class Movie {

    private final String title;

    private final int runningTime;

    private final int price;

    private DiscountPolicy discountPolicy;

    public Movie(String title, int runningTime, int price) {
        this(title, runningTime, price, null);
    }

    public Movie(String title, int runningTime, int price, DiscountPolicy discountPolicy) {
        this.title = title;
        this.runningTime = runningTime;
        this.price = price;
        this.discountPolicy = discountPolicy;
    }

    public PriceInfo calculatePrice(int totalPeople) {
        int originalPrice = price * totalPeople;
        return new PriceInfo(originalPrice, discountPolicy.discount(originalPrice));
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return runningTime == movie.runningTime &&
                price == movie.price &&
                Objects.equals(title, movie.title) &&
                Objects.equals(discountPolicy, movie.discountPolicy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, runningTime, price, discountPolicy);
    }

}
