package mocktest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VerifyInteractTest {

    @Mock
    private List<String> mockWords;

    @Test
    @DisplayName("mock 객체가 한번도 메서드 호출이 없다면 verifyNoInteractions로 검증할 수 있다.")
    void verifyNoInteractions_test() {
        verifyNoInteractions(mockWords);
    }

    @Test
    @DisplayName("mock 객체에 메서드 호출이 있다면 verifyNoInteractions는 실패한다.")
    void verifyNoInteractions_fail_test() {
        mockWords.add("hello");

        verify(mockWords).add("hello");
        verifyNoInteractions(mockWords);
    }

    @Test
    @DisplayName("mock 객체에 메서드 호출을 검증한 후 더이상의 메서드 호출이 없음을 검증한다면 verifyNoMoreInteractions를 사용할 수 있다.")
    void verifyNoMoreInteractions_test() {
        mockWords.add("hello");

        verify(mockWords).add("hello");
        verifyNoMoreInteractions(mockWords);
    }
}
