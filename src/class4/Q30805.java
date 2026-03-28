package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q30805 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int M;

    private static int[] A;
    private static int[] B;

    private static Queue<Number> pq;
    private static List<Integer> result;

    private static class Number implements Comparable<Number> {
        int index;
        int value;

        Number(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Number o) {
            if (this.value == o.value) {
                return Integer.compare(this.index, o.index);
            }
            return Integer.compare(o.value, this.value);
        }
    }

    public static void main(String[] args) throws IOException {
        // A 수열을 값이 큰 순서(내람차순)대로 저장할 큐 정의
        pq = new PriorityQueue<>();
        // 입력
        N = Integer.parseInt(br.readLine());
        A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N ; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            pq.offer(new Number(i, A[i]));
        }

        M = Integer.parseInt(br.readLine());
        B = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < M ; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        result = new ArrayList<>();
        calculate();

        // 출력
        System.out.println(result.size());
        for (Integer value : result) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    private static void calculate() {
        // 시작 인덱스
        int startA = -1;
        int startB = -1;

        // 최장 공통 부분 수열 확인
        while (!pq.isEmpty()) {
            Number start = pq.poll();
            // 다음 시작 인덱스는 항상 이전 시작 인덱스보다 커야 한다
            if (start.index <= startA) continue;

            for (int i = 0; i < M; i++) {
                if (B[i] == start.value && i > startB) {
                    startA = start.index;
                    startB = i;
                    result.add(start.value);
                    break;
                }
            }
        }
    }
}
