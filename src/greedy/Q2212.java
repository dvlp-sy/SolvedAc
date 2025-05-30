package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2212 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static class Pair {
        int sensor1;
        int sensor2;

        public Pair(int s1, int s2) {
            sensor1 = s1;
            sensor2 = s2;
        }
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        // 센서 위치 정보 저장
       List<Integer> location = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            int input = Integer.parseInt(st.nextToken());
            if (!location.contains(input)) {
                location.add(input);
            }
        }

        // 센서 위치 정보 오름차순 정렬
        location.sort((a, b) -> a - b);

        // 센서간 거리 배열
        int length = location.size();
        Pair[] pairs = new Pair[length-1];
        for (int i=0; i<length-1; i++) {
            pairs[i] = new Pair(location.get(i), location.get(i+1));
        }

        // 센서간 거리 배열 내림차순 정렬
        Arrays.sort(pairs, (a, b) ->
                (b.sensor2 - b.sensor1) - (a.sensor2 - a.sensor1));

        // 가장 먼 K-1개의 거리를 제외한 나머지 거리 합산
        int sum = 0;
        for (int i=K-1; i<length-1; i++) {
            sum += pairs[i].sensor2 - pairs[i].sensor1;
        }

        System.out.println(sum);
    }
}
