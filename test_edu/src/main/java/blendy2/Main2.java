package blendy2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> meta = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int[] strength = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue)
                .toArray();
        int sectionMeta = meta.get(1);
        int[][] sections = new int[sectionMeta][2];

        for (int i = 0; i < sectionMeta; i++) {
            sections[i] = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .toArray();
        }

        Comparator<int[]> comparator = (a, b) -> {
            if (a[0] < b[0]) {
                return 1;
            }

            int i = a[1] - a[0];
            int j = b[1] - b[0];

            if (i < j) {
                return 1;
            }
            
            return -1;
        };

        List<int[]> sorted = Arrays.stream(sections).sorted(comparator).collect(Collectors.toList());

    }

    private static int calculate(int[] strength, int[] section) {
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;

        for (int i = section[0] - 1; i < section[1]; i++) {
            if (!map.containsKey(strength[i])) {
                map.put(strength[i], 1);
                result += strength[i];
            } else {
                int count = map.get(strength[i]);
                map.put(strength[i], count + 1);
                result += (count + 1) * strength[i];
            }
        }

        return result;
    }
}
