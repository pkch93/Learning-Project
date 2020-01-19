package mocktest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VerifyCountTest {

    @Mock
    private List<String> words;

    @Test
    @DisplayName("times로 mock 객체의 메서드 실행 횟수를 체크할 수 있다.")
    void times_test() {
        words.add("hello");
        words.add("world");

        verify(words, times(2)).add(anyString());
    }

    @Test
    @DisplayName("never는 한번도 호출되지 않음을 체크할 수 있다.")
    void never_test() {
        verify(words, never()).add(anyString());
    }

    @Test
    @DisplayName("atMost는 인자에 해당하는 값을 최대값으로 사용하여 횟수를 체크한다.")
    void atMost_test() {
        words.add("hello");
        words.add("world");

        verify(words, atMost(2)).add(anyString());
    }

    @Test
    @DisplayName("atMostOnce는 한번 호출되었거나 호출되지 않았는지 체크한다.")
    void atMostOnce_test() {
        words.add("hello");

        verify(words, atMostOnce()).add(anyString()); // atMost(1)과 동일
    }

    @Test
    @DisplayName("atLeast는 인자에 해당하는 값을 최솟값으로 사용하여 횟수를 체크한다.")
    void atLeast_test() {
        words.add("hello");
        words.add("world");

        verify(words, atLeast(1)).add(anyString());
    }

    @Test
    @DisplayName("atLeastOnce는 한번 호출되었거나 호출되지 않았는지 체크한다.")
    void atLeastOnce_test() {
        words.add("hello");

        verify(words, atLeastOnce()).add(anyString()); // atMost(1)과 동일
    }



}
