package edu.pkch.patterns.strategy;

import edu.pkch.patterns.strategy.domain.Item;

public interface DiscountStrategy {
    int calculate(Item item);
}
