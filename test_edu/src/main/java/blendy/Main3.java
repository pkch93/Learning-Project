package blendy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main3 {
    private static final int MAX = 1000000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] init = Arrays.stream((br.readLine().split(" "))).map(Integer::parseInt).mapToInt(Integer::intValue).toArray();
        int n = init[0];
        int m = init[1];
        int[][] adjustMatrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                adjustMatrix[i][j] = MAX;
            }
        }

        while (true) {
            String line = br.readLine();

            if (line == null || "".equals(line)) {
                break;
            }

            int[] input = Arrays.stream(line.split(" ")).map(Integer::parseInt).mapToInt(Integer::intValue).toArray();
            adjustMatrix[input[0] - 1][input[1] - 1] = input[2];
        }

        floyd(adjustMatrix, n);
        print(adjustMatrix, n);
    }

    private static void floyd(int[][] adjustMatrix, int n) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (adjustMatrix[i][k] + adjustMatrix[k][j] < adjustMatrix[i][j]) {
                        adjustMatrix[i][j] = adjustMatrix[i][k] + adjustMatrix[k][j];
                    }
                }
            }
        }
    }

    private static void print(int[][] adjustMatrix, int n) {
        for (int i = 0; i < n; i++) {
            if (adjustMatrix[i][i] < 0) {
                System.out.println("bug");
                return;
            }
        }

        int result = adjustMatrix[0][n - 1];

        System.out.println(result);
    }
}
