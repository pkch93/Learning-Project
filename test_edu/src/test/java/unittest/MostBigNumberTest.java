package unittest;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MostBigNumberTest {

    MostBigNumber dut = new MostBigNumber();

    @Test
    void name() {
        int[] numbers = {10, 2};

        List<String> result = dut.solution(numbers);

        System.out.println(result);
    }
}