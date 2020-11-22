package unittest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MostBigNumber {

    public List<String> solution(int[] numbers) {
        Comparator<String> comparator = (i1, i2) -> {
            int min = Math.min(i1.length(), i2.length());

            for (int i = 0; i < min; i++) {
                if (i1.charAt(i) < i2.charAt(i)) {
                    return 1;
                } else if (i1.charAt(i) != i2.charAt(i)) {
                    if (i1.length() > i2.length()) {
                        return i1.charAt(i) < i1.charAt(i + 1) ? 1 : -1;
                    } else if (i1.length() < i2.length()) {
                        return i2.charAt(i) < i2.charAt(i + 1) ? 1 : -1;
                    }
                }
            }
            return -1;
        };

        return Arrays.stream(numbers)
                .mapToObj(number -> number + "")
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}
