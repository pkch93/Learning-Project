package blendy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main4 {
    private static final int[] X_DELTA = {0, 0, -1, 1};
    private static final int[] Y_DELTA = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] init = Arrays.stream((br.readLine().split(" "))).map(Integer::parseInt).mapToInt(Integer::intValue).toArray();
        int n = init[0];
        int d = init[1];
        int[][] map = new int[n][n];

        int i = 0;
        while (true) {
            String line = br.readLine();

            if (line == null || "".equals(line)) {
                break;
            }

            map[i] = Arrays.stream(line.split(" ")).map(Integer::parseInt).mapToInt(Integer::intValue).toArray();
            i += 1;
        }

        System.out.println(solution(map, n, d));
    }

    private static int solution(int[][] map, int n, int d) {
        int result = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) {
                    result = Math.max(result, bfs(map, new int[]{i, j}, n, d));
                }
            }
        }

        return result;
    }

    private static int bfs(int[][] map, int[] init, int n, int d) {
        boolean[][] isMove = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        int result = 0;
        queue.add(new int[]{init[0], init[1], d});

        while (!queue.isEmpty()) {
            int[] position = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = position[1] + X_DELTA[i];
                int nextY = position[0] + Y_DELTA[i];

                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || isMove[nextY][nextX]) continue;

                if (map[nextY][nextX] != 1) {
                    int distance = Math.abs(nextX - init[1]) + Math.abs(nextY - init[0]);

                    boolean canMove = false;
                    for (int j = 1; j <= position[2]; j++) {
                        int i1 = nextY + Y_DELTA[j];
                        int i2 = nextX + X_DELTA[j];

                        if (i2 < 0 || i2 >= n || i1 < 0 || i1 >= n || isMove[i1][i2] || map[i1][i2] == 1) {
                            break;
                        }

                        canMove = true;
                    }

                    if (canMove && distance <= d) {
                        queue.add(new int[]{nextY, nextX, position[2] - 1});
                        result += 1;
                        isMove[nextY][nextX] = true;
                    }
                }
            }
        }

        return result;
    }

//6 2
//0 0 0 0 0 0
//0 0 0 0 0 0
//0 0 0 0 1 0
//0 0 0 0 0 0
//0 0 1 0 0 0
//0 0 0 0 0 0
}
