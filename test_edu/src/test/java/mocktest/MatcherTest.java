package mocktest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MatcherTest {

    @Mock
    private List<String> words;

    @Test
    @DisplayName("mockito matcher 사용 테스트")
    void matcher_test() {
        String word = "hello world";

        when(words.get(anyInt())).thenReturn(word);

        assertThat(words.get(0)).isEqualTo(word);
        assertThat(words.get(1)).isEqualTo(word);
        assertThat(words.get(2)).isEqualTo(word);

        verify(words).get(anyInt());
    }
}
