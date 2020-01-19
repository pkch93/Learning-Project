package mocktest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MockitoBasicTest {

    private List<String> mockWords;

    @Test
    @DisplayName("mockito 기본 사용, mock은 mock으로 수행한 행동을 모두 기억한다.")
    void mockito_basic() {
        mockWords = mock(List.class);

        mockWords.add("hello");
        mockWords.clear();

        verify(mockWords).add("hello");
        verify(mockWords).clear();
    }

    @Test
    @DisplayName("mockito mock 객체가 하지 않은 행위 검증")
    void mockito_basic_notBehavior_test() {
        mockWords = mock(List.class);

        mockWords.add("hello");

        verify(mockWords).add("hello");
        /**
         *  하지 않은 행위로 오류가 나타남
         *  verify는 기본적으로 해당 mock 객체가 다음에 나타나는 행위를 했는지 검증한다.
         */
        verify(mockWords).clear();
    }
}