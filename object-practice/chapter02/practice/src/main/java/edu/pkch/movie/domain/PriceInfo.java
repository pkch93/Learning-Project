package edu.pkch.movie.domain;

public class PriceInfo {

    private final int originalPrice;

    private final double discountedPrice;

    public PriceInfo(int originalPrice, double discountedPrice) {
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
    }

    public PriceInfo extraDiscount(DiscountCondition discountCondition) {
        return new PriceInfo(originalPrice, discountCondition.discount(discountedPrice));
    }
}
