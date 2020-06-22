package mocktest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StubBasicTest {

    @Mock
    private List<String> mockWords;

    @Test
    @DisplayName("stub 기본 사용법")
    void stub_basic() {
        when(mockWords.get(0)).thenReturn("hello");

        assertThat(mockWords.get(0)).isEqualTo("hello");
    }

    @Test
    @DisplayName("doThrow()로 exception stub 하기")
    void stub_exception() {
        doThrow(IllegalArgumentException.class).when(mockWords).get(0);

        assertThatThrownBy(() -> mockWords.get(0)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("연속하여 메서드 stub 하기")
    void stubbing_consecutive() {
        when(mockWords.get(anyInt()))
                .thenReturn("hello")
                .thenReturn("world")
                .thenThrow(new RuntimeException());

        assertThat(mockWords.get(0)).isEqualTo("hello");
        assertThat(mockWords.get(0)).isEqualTo("world");
        assertThatThrownBy(() -> mockWords.get(0))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("연속하여 메서드 stub 하기")
    void stubbing_consecutive_shortcut() {
        when(mockWords.get(anyInt()))
                .thenReturn("hello", "world")
                .thenThrow(new RuntimeException());

        assertThat(mockWords.get(0)).isEqualTo("hello");
        assertThat(mockWords.get(0)).isEqualTo("world");
        assertThatThrownBy(() -> mockWords.get(0))
                .isInstanceOf(RuntimeException.class);
    }

    /**
     * 같은 인자로 stubbing을 하는 경우 stubbing override가 가능하다.
     */
    @Test
    @DisplayName("stubbing한 메서드 덮어쓰기")
    void stubbing_override() {
        when(mockWords.add("1")).thenReturn(true);
        when(mockWords.add("1")).thenReturn(false);
//        doReturn(false).when(mockWords).add("1");

        assertFalse(mockWords.add("1"));

    }

    @Test
    @DisplayName("thenAnswer로 callback stub 하기")
    void stubbing_callback() {
        when(mockWords.get(0))
                .thenAnswer(invocation -> "hello");

        assertThat(mockWords.get(0)).isEqualTo("hello");
    }
}
