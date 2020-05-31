package kakao;

import java.util.*;
import java.util.stream.Collectors;

public class Question2 {

    public static void main(String[] args) {
        Question2 question2 = new Question2();

        System.out.println(question2.solution("100-200*300-500+20"));
    }

    private static final int PLUS_INDEX = 0;
    private static final int MINUS_INDEX = 1;
    private static final int MULTIPLE_INDEX = 2;

    private static final Map<String, Integer> PRIORITY_MAPPER = new HashMap<>();
    private static final int[][] OPERATOR_PRIORITY = {
            {1, 2, 3},
            {1, 3, 2},
            {2, 1, 3},
            {2, 3, 1},
            {3, 1, 2},
            {3, 2, 1}
    };

    static {
        PRIORITY_MAPPER.put("+", PLUS_INDEX);
        PRIORITY_MAPPER.put("-", MINUS_INDEX);
        PRIORITY_MAPPER.put("*", MULTIPLE_INDEX);
    }

    public long solution(String expression) {
        long answer = Long.MIN_VALUE;
        for (int i = 0; i < 6; i++) {
            Stack<Long> numberStack = new Stack<>();
            Stack<String> operatorStack = new Stack<>();
            long[] numbers = Arrays.stream(expression.split("[+|\\-|*]"))
                    .mapToLong(Integer::parseInt)
                    .toArray();

            for (int x = numbers.length - 1; x >= 0; x--) {
                numberStack.push(numbers[x]);
            }

            List<String> operators = Arrays.stream(expression.split("[0-9]*"))
                    .filter(operator -> !operator.equals(""))
                    .collect(Collectors.toList());

            for (String operator : operators) {
                int currentOperatorPriority = OPERATOR_PRIORITY[i][PRIORITY_MAPPER.get(operator)];
                if (operatorStack.empty()) {
                    operatorStack.push(operator);
                    continue;
                }

                if (currentOperatorPriority == 2) {
                    numberStack.push(calculate(operator, numberStack.pop(), numberStack.pop()));
                } else if (OPERATOR_PRIORITY[i][PRIORITY_MAPPER.get(operatorStack.peek())] < currentOperatorPriority) {
                    numberStack.push(calculate(operator, numberStack.pop(), numberStack.pop()));
                } else if (OPERATOR_PRIORITY[i][PRIORITY_MAPPER.get(operatorStack.peek())] > currentOperatorPriority){
                    numberStack.push(calculate(operatorStack.pop(), numberStack.pop(), numberStack.pop()));
                } else {
                    operatorStack.push(operator);
                }
            }

            while (!operatorStack.empty()) {
                numberStack.push(calculate(operatorStack.pop(), numberStack.pop(), numberStack.pop()));
            }

            Long result = Math.abs(numberStack.pop());

            answer = result > answer ? result : answer;
        }

        return answer;
    }

    private long calculate(String operator, long a, long b) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            default:
                return a + b;
        }
    }
}
