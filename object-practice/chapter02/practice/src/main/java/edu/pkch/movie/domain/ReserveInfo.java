package edu.pkch.movie.domain;

import java.util.Objects;

public class ReserveInfo {

    private final MovieSchedule movieSchedule;

    private PriceInfo priceInfo;

    private int totalPeople;

    private ReserveStatus reserveStatus;

    public ReserveInfo(MovieSchedule movieSchedule, PriceInfo priceInfo, int totalPeople) {
        this.movieSchedule = movieSchedule;
        this.priceInfo = priceInfo;
        this.totalPeople = totalPeople;
        this.reserveStatus = ReserveStatus.RESERVED;
    }

    public MovieSchedule getMovieSchedule() {
        return movieSchedule;
    }

    public PriceInfo getPriceInfo() {
        return priceInfo;
    }

    public int getTotalPeople() {
        return totalPeople;
    }

    public ReserveStatus getReserveStatus() {
        return reserveStatus;
    }
}
