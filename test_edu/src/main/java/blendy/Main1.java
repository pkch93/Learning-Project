package blendy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Main1 {
    private static int MAX_HEART_BEAT = 220;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> inputs = br.lines().map(Integer::parseInt).collect(Collectors.toList());

        int age = inputs.get(0);
        List<Integer> heartBeats = inputs.subList(1, inputs.size());

        Map<String, Long> statics = heartBeats.stream()
                .map(heartBeat -> BeatType.calculate(age, heartBeat))
                .collect(groupingBy(BeatType::name, Collectors.counting()));

        String result = String.format("%d %d %d %d %d %d",
                isNull(statics.get(BeatType.PEAK_STRENGTH.name())),
                isNull(statics.get(BeatType.HIGH_STRENGTH.name())),
                isNull(statics.get(BeatType.MEDIUM_STRENGTH.name())),
                isNull(statics.get(BeatType.INTENSIVE.name())),
                isNull(statics.get(BeatType.WARMING_UP.name())),
                isNull(statics.get(BeatType.RELEX.name()))
        );

        System.out.println(result);
    }

    private static long isNull(Long number) {
        return number == null ? 0 : number;
    }

    enum BeatType {
        RELEX(0, 60),
        WARMING_UP(60, 68),
        INTENSIVE(68, 75),
        MEDIUM_STRENGTH(75, 80),
        HIGH_STRENGTH(80, 90),
        PEAK_STRENGTH(90, 101);

        private final int min;
        private final int max;

        BeatType(int min, int max) {
            this.min = min;
            this.max = max;
        }

        static BeatType calculate(int age, int heartBeat) {
            int adjustedMaxHeartBeat = MAX_HEART_BEAT - age;

            double heartBeatRatio = (double) heartBeat / adjustedMaxHeartBeat * 100;

            for (BeatType beatType : BeatType.values()) {
                if (beatType.min <= heartBeatRatio && heartBeatRatio < beatType.max) {
                    return beatType;
                }
            }
            return PEAK_STRENGTH;
        }
    }
}
