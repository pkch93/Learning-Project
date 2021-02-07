package edu.pkch.function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PartialFuncTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("[Java] 부분함수 테스트")
    void partialFunction(int n, int expected) {
        // given
        Predicate<Integer> condition = (i) -> 1 <= i && i <= 10;
        Function<Integer, Integer> func = (i) -> i * i;

        PartialFunc<Integer, Integer> partialFunction = new PartialFunc<>(condition, func);

        // when
        int actual = partialFunction.invoke(n);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> partialFunction() {
        return Stream.of(
                Arguments.of(1, 1),
                Arguments.of(2, 4),
                Arguments.of(3, 9),
                Arguments.of(4, 16),
                Arguments.of(5, 25),
                Arguments.of(6, 36),
                Arguments.of(7, 49),
                Arguments.of(8, 64),
                Arguments.of(9, 81),
                Arguments.of(10, 100)
        );
    }
}