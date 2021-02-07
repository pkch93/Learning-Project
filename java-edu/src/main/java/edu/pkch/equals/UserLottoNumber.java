package edu.pkch.equals;

import java.util.Objects;

public class UserLottoNumber implements LottoNumber {
    private final int number;

    public UserLottoNumber(int number) {
        this.number = number;
    }

    public boolean isSame(LottoNumber lottoNumber) {
        return this.equals(lottoNumber);
    }

    public boolean equals(UserLottoNumber userLottoNumber) {
        if (this == userLottoNumber) return true;
        if (userLottoNumber == null || getClass() != userLottoNumber.getClass()) return false;
        UserLottoNumber that = userLottoNumber;
        return number == that.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLottoNumber that = (UserLottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
