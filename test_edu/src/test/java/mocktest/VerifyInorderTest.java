package mocktest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class VerifyInorderTest {

    @Mock
    private List<String> mockWords;

    @Test
    @DisplayName("순서대로 mock이 동작하는지 테스트")
    void verify_inorder_test() {
        mockWords.add("first");
        mockWords.add("second");

        InOrder inOrder = inOrder(mockWords);

        inOrder.verify(mockWords).add("first");
        inOrder.verify(mockWords).add("second");
    }

    @Test
    @DisplayName("순서대로 mock을 검증하지 않는 경우 테스트")
    void verify_inorder_whenNotInorderExecute_test() {
        mockWords.add("first");
        mockWords.add("second");

        InOrder inOrder = inOrder(mockWords);

        // first, second 순서로 verify 하지 않았으므로 test fail
        inOrder.verify(mockWords).add("second");
        inOrder.verify(mockWords).add("first");
    }

    @Test
    @DisplayName("순서대로 여러가지의 mock을 검증하는 테스트")
    void verify_multimock_inorder_whenNotInorderExecute_test() {
        List<Integer> mockNumbers = mock(List.class);

        mockWords.add("first");
        mockNumbers.add(0);
        mockWords.add("second");

        InOrder inOrder = inOrder(mockWords, mockNumbers);

        inOrder.verify(mockWords).add("first");
        inOrder.verify(mockNumbers).add(0);
        inOrder.verify(mockWords).add("second");
    }
}
