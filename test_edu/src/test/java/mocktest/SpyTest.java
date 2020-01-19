package mocktest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class SpyTest {

    private List<String> spyWords;

    @BeforeEach
    void setUp() {
        ArrayList<String> words = new ArrayList<>();
        spyWords = spy(words);
    }

    @Test
    @DisplayName("spy 객체 활용")
    void spy_test() {
        when(spyWords.size()).thenReturn(100);
        spyWords.add("hello");
        spyWords.add("world");

        assertThat(spyWords.get(0)).isEqualTo("hello");
        assertThat(spyWords.get(1)).isEqualTo("world");
        assertThat(spyWords.size()).isEqualTo(100);
    }
}
