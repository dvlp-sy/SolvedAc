package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1407 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    // 1부터 N까지 2의 거듭제곱 꼴이면서 가장 큰 약수들의 총합 계산
    private static long getSum(long N) {
        if (N == 0) return 0;

        long sum = 0;
        long previousK = 1;         // k의 이전 거듭제곱 (2^0부터 시작)
        long previousCount = N;     // k의 배수 개수

        for (long i = 1 ; Math.pow(2, i) <= N ; i++) {
            long k = (long) Math.pow(2, i);
            long count = N / k;

            sum += (previousCount - count) * previousK;

            previousK = k;
            previousCount = count;
        }

        sum += previousK;
        return sum;
    }

    public static void main(String[] args) throws IOException {
        // 입력
        st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        System.out.println(getSum(B) - getSum(A-1));
    }
}
