package edu.pkch.movie.domain;

public class AmountDiscountPolicy implements DiscountPolicy {
    private static final int DISCOUNT_AMOUNT = 2000;

    @Override
    public double discount(int totalAmount) {
        return totalAmount - DISCOUNT_AMOUNT;
    }
}
