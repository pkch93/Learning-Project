package blendy2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Main {
    private static final int[] X_DELTA = {0, 0, -1, 1};
    private static final int[] Y_DELTA = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> meta = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int n = meta.get(0);
        int[][] mineMap = new int[n][n];

        for (int i = 0; i < n; i++) {
            mineMap[i] = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .toArray();
        }

        int result = bfs(n, meta.get(1), mineMap);
        System.out.println(result == Integer.MIN_VALUE ? -1 : result);
    }

    private static int bfs(int n, int durability, int[][] mineMap) {
        boolean[][] visit = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, durability - mineMap[0][0]});
        int max = Integer.MIN_VALUE;

        while(!queue.isEmpty()) {
            int[] poped = queue.remove();
            visit[poped[0]][poped[1]] = true;

            for (int i = 0; i < 4; i++) {
                int nextX = poped[1] + X_DELTA[i];
                int nextY = poped[0] + Y_DELTA[i];

                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || visit[nextY][nextX]) {
                    continue;
                }

                if (nextX == n - 1 && nextY == n - 1) {
                    max = Math.max(max, poped[2] - mineMap[nextY][nextX]);
                    continue;
                }

                int damagedDurability = poped[2] - mineMap[nextY][nextX];
                if (damagedDurability < 0) {
                    continue;
                }

                queue.add(new int[]{nextY, nextX, damagedDurability});
            }
        }

        return max;
    }
}
