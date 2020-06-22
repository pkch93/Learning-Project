package mocktest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class BDDMockitoTest {

    @Mock
    private List<String> mockWords;

    @Test
    @DisplayName("BDDMockito void Method stubbing")
    void bddMockito_voidMethod() {
        // given
        willDoNothing().given(mockWords).add(0, "hello");

        // when
        mockWords.add(0, "hello");

        // then
        then(mockWords).should().add(0, "hello");
    }

    @Test
    @DisplayName("BDDMockito void Method stubbing")
    void bddMockito_voidMethod_throwException() {
        // given
        willThrow(RuntimeException.class).given(mockWords).add(0, "hello");

        // when & then
        assertThatThrownBy(() -> mockWords.add(0, "hello"))
                .isInstanceOf(RuntimeException.class);
    }
}
