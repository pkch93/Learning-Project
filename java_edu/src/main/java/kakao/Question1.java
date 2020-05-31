package kakao;

public class Question1 {

    public static void main(String[] args) {
        Question1 question1 = new Question1();

        String answer = question1.solution(new int[]{0, 0, 0, 0, 0}, "left");
        System.out.println(answer);
    }

    private static final String LEFT = "L";
    private static final String RIGHT = "R";

    private static final int[] boards = new int[]{2, 5, 8};

    public String solution(int[] numbers, String hands) {
        String answer = "";
        int[] leftPosition = new int[] {3, 0};
        int[] rightPosition = new int[] {3, 2};

        for (int number : numbers) {
            if (isLeft(number)) {
                answer += LEFT;
                leftPosition[0] = number / 3;
                leftPosition[1] = 0;
            } else if (isRight(number)) {
                answer += RIGHT;
                rightPosition[0] = number / 3 - 1;
                rightPosition[1] = 2;
            } else {
                int[] foundPosition = findPosition(number);
                int leftDistance = Math.abs(foundPosition[0] - leftPosition[0]) + Math.abs(foundPosition[1] - leftPosition[1]);
                int rightDistance = Math.abs(foundPosition[0] - rightPosition[0]) + Math.abs(foundPosition[1] - rightPosition[1]);

                if (leftDistance < rightDistance) {
                    answer += LEFT;
                    if (number == 0) {
                        leftPosition[0] = 3;
                        leftPosition[1] = 1;
                    } else {
                        leftPosition[0] = number / 3;
                        leftPosition[1] = 1;
                    }
                } else if (rightDistance < leftDistance) {
                    answer += RIGHT;
                    if (number == 0) {
                        rightPosition[0] = 3;
                        rightPosition[1] = 1;
                    } else {
                        rightPosition[0] = number / 3;
                        rightPosition[1] = 1;
                    }
                } else {
                    if (hands.equals("right")) {
                        answer += RIGHT;
                        if (number == 0) {
                            rightPosition[0] = 3;
                            rightPosition[1] = 1;
                        } else {
                            rightPosition[0] = number / 3;
                            rightPosition[1] = 1;
                        }
                    } else {
                        answer += LEFT;
                        if (number == 0) {
                            leftPosition[0] = 3;
                            leftPosition[1] = 1;
                        } else {
                            leftPosition[0] = number / 3;
                            leftPosition[1] = 1;
                        }
                    }
                }
            }
        }

        return answer;
    }

    private int[] findPosition(int number) {
        for (int i = 0; i < 3; i++) {
            if (boards[i] == number) {
                return new int[] {i, 1};
            }
        }

        return new int[] {3, 1};
    }

    private boolean isLeft(int number) {
        return number == 1 || number == 4 || number == 7;
    }

    private boolean isRight(int number) {
        return number == 3 || number == 6 || number == 9;
    }
}
