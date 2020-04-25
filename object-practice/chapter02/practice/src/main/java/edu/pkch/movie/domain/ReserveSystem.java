package edu.pkch.movie.domain;

import java.util.List;

public class ReserveSystem {

    private final MovieTable movieTable;

    private List<DiscountCondition> discountConditions;

    public ReserveSystem(MovieTable movieTable) {
        this.movieTable = movieTable;
    }

    public ReserveInfo reserve(String title, Turn turn, int totalPeople) {
        MovieSchedule pickedSchedule = movieTable.pick(title, turn);

        if (pickedSchedule.isReserved()) {
            throw new IllegalStateException();
        }

        return new ReserveInfo(pickedSchedule, extraDiscount(pickedSchedule.reserve(totalPeople)), totalPeople);
    }

    private PriceInfo extraDiscount(PriceInfo priceInfo) {
        PriceInfo discountedPriceInfo = priceInfo;

        for (DiscountCondition discountCondition : discountConditions) {
            discountedPriceInfo = discountedPriceInfo.extraDiscount(discountCondition);
        }

        return discountedPriceInfo;
    }
}
