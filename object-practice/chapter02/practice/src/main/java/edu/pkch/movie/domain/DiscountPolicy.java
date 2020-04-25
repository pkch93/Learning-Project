package edu.pkch.movie.domain;

public interface DiscountPolicy {
    double discount(int totalAmount);
}
