package programmers;

import java.util.Arrays;
import java.util.Comparator;

public class FailureRate {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] answer = solution.solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3});
        System.out.println(answer);
    }

    private static class Solution {
        public int[] solution(int n, int[] stages) {
            int total = stages.length;
            Result[] results = new Result[n];
            Arrays.sort(stages);

            int index = 1;
            int count = 0;

            for (int i = 0; i < n; i++) {
                if (stages[i] == index) {
                    count += 1;
                    continue;
                }

                results[index - 1] = new Result(index, (double) count / total);
                for (;index + 1 < stages[i]; index++) {
                    results[index] = new Result(index, 0);
                }
                total -= count;
                count = 1;
            }

            return Arrays.stream(results)
                    .sorted(Comparator.comparingDouble(Result::getFailureRate))
                    .mapToInt(Result::getStage)
                    .toArray();
        }
    }

    private static class Result {
        private int stage;
        private double failureRate;

        public Result(int stage, double failureRate) {
            this.stage = stage;
            this.failureRate = failureRate;
        }

        public int getStage() {
            return stage;
        }

        public double getFailureRate() {
            return failureRate;
        }
    }
}
