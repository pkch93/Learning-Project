package edu.pkch.function

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class FunctionsKtTest {
    @Test
    internal fun useFunction() {
        // given & when
        val actual = declare("hello")

        // then
        assertThat(actual).isEqualTo("hello")
    }

    /*
     * function 정의할때 body를 주는 경우 리턴타입을 명시해야함.
     * 리턴타입은 추론이 불가능한듯...
     */
    private fun declare(arg: String): String {
        return arg
    }

    @Test
    internal fun defaultArguments() {
        // given & when
        val actual = declareDefaultArguments()

        // then
        assertThat(actual).isEqualTo("")
    }

    private fun declareDefaultArguments(arg: String = ""): String {
        return arg
    }

    @Test
    internal fun multiDefaultArguments() {
        // given & when
        val actual = declareMultiDefaultArguments(arg1 = "")
        // 위와 같이 인자를 줄때도 이름을 명시하여 줄 수 있다.

        // then
        assertThat(actual).isEqualTo("")
    }

    @Test
    @DisplayName("""
        default 인자가 앞에 먼저 선언이 되어 있는 경우 
        이름 없이 인자를 전달할 떄 결국 모든 인자를 전달해야한다.
    """)
    internal fun multiDefaultArgumentsException() {
        /*
         * declareMultiDefaultArguments("") compile error!
         * 되도록 default 인자를 줄때는 뒤에서부터 주는 것이 좋을듯.
         * python에서는 언어 자체에서 이를 강제하지만 코틀린은 강제하지는 않는것으로 보임.
         */

        // given & when
        val actual = declareMultiDefaultArguments("", "hello")

        // then
        assertThat(actual).isEqualTo("hello")
    }

    private fun declareMultiDefaultArguments(arg2: String = "", arg1: String): String {
        return arg1 + arg2
    }

    @Test
    @DisplayName("lambda가 마지막 인자인 경우")
    internal fun multiDefaultArgumentsWithLambda() {
        /*
         * declareMultiDefaultArguments("") compile error!
         * 되도록 default 인자를 줄때는 뒤에서부터 주는 것이 좋을듯.
         * python에서는 언어 자체에서 이를 강제하지만 코틀린은 강제하지는 않는것으로 보임.
         */

        // given & when
        val actual = declareMultiDefaultArgumentsWithLambda("pkch", { println("hello") }, { name -> "hello ${name}" })

        // then
        assertThat(actual).isEqualTo("hello pkch")
    }

    private fun declareMultiDefaultArgumentsWithLambda(arg: String = "", lambda2: () -> Unit, lambda: (str: String) -> String): String {
        return lambda(arg)
    }

    @Test
    @DisplayName("확장함수 테스트")
    internal fun stringExtensionFunction() {
        // given
        val target = "Hello World!"

        // when
        val actual = target.lastChar()

        // then
        assertThat(actual).isEqualTo('!')
    }

    @Test
    @DisplayName("n만큼 제곱한다.")
    internal fun pow() {
        // given
        val target = 2

        // when
        val actual = target pow 5

        // then
        assertThat(actual).isEqualTo(32)
    }
}
