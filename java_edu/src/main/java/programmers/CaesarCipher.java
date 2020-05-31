package programmers;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CaesarCipher {

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.solution("a B z", 4));
    }

    private static class Solution {
        private static final int UPPERCASE_A = 65;
        private static final int UPPERCASE_Z = 90;
        private static final int LOWERCASE_A = 97;
        private static final int LOWERCASE_Z = 122;

        public String solution(String s, int n) {
            return Arrays.stream(s.split(""))
                    .map(sUnit -> sUnit.charAt(0))
                    .map(unit -> transform(unit, n))
                    .collect(Collectors.joining());
        }

        private String transform(char unit, int n) {
            if (unit == ' ') {
                return " ";
            }

            return isUppercase(unit) ?
                    transformUppercase(unit, n) :
                    transformLowercase(unit, n);
        }

        private boolean isUppercase(char unit) {
            return UPPERCASE_A <= unit && unit <= UPPERCASE_Z;
        }

        private String transformUppercase(char unit, int n) {
            int i = (int) unit + n;
            return i > UPPERCASE_Z ?
                    (char) (UPPERCASE_A + (i - 1) % UPPERCASE_Z) + "" :
                    (char) i + "";
        }

        private String transformLowercase(char unit, int n) {
            int i = (int) unit + n;
            return i > LOWERCASE_Z ?
                    (char) (LOWERCASE_A + (i - 1) % LOWERCASE_Z) + "" :
                    (char) i + "";
        }
    }
}
