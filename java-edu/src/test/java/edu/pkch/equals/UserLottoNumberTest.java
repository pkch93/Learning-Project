package edu.pkch.equals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserLottoNumberTest {

    @Test
    @DisplayName("equals 인자로 구현클래스 받는 경우")
    void equals() {
        // given
        UserLottoNumber lottoNumber = new UserLottoNumber(1);

        // when
        boolean actual = lottoNumber.equals(new UserLottoNumber(1));

        // then
        assertThat(actual).isEqualTo(true);
    }

//    @Test
//    void isSame() {
//        // given
//        UserLottoNumber lottoNumber = new UserLottoNumber(1);
//
//        // when
//        boolean actual = lottoNumber.isSame(new UserLottoNumber(1));
//
//        // then
//        assertThat(actual).isEqualTo(false);
//    }
}