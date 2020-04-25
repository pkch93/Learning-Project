package edu.pkch.movie.domain;

public class PercentageDiscountPolicy implements DiscountPolicy {
    private static final double DISCOUNT_PERCENTAGE = 20;

    @Override
    public double discount(int totalAmount) {
        return totalAmount / (DISCOUNT_PERCENTAGE / 100);
    }
}
