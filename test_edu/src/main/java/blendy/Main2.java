package blendy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main2 {
    // 상, 하, 좌, 우
    private static final int[] X_DELTA = {0, 0, -1, 1};
    private static final int[] Y_DELTA = {-1, 1, 0, 0};
    private static final int WATER = -1;
    private static final int SAFE = 0;
    private static final int WALL = 1;
    private static final int ANSWER = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];

        int i = 0;
        while (true) {
            String line = br.readLine();

            if (line == null) {
                break;
            }

            map[i] = Arrays.stream(line.split(" ")).map(Integer::parseInt).mapToInt(Integer::intValue).toArray();
        }

        int[] water = {0, 0};
        int[] position = {n - 1, n - 1};


        // 물이 먼저 지난다고 가정. 물이 지나는 곳은 물로 마킹, 홍현이가 지나는 곳은 지날때마다 해당 위치를 막을때 안전한 공간 계산
    }

    private static void moveWater(int[][] map, int[] water, int n) {
        for (int i = 0; i < 4; i++) {
            int nextX = water[0] + X_DELTA[i];
            int nextY = water[1] + Y_DELTA[i];

            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) continue;

            map[nextY][nextX] = WATER;
        }
    }

    private static void movePerson() {

    }
}
