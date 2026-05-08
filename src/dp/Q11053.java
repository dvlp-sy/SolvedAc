package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11053 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int[] seq;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        // 입력 및 초기화
        init();

        // 증가하는 부분 수열의 최대 길이 저장
        int max = 0;
        for (int i = 0 ; i < N ; i++) {
            dp[i] = 1;
            for (int j = 0 ; j < i ; j++) {
                if (seq[i] > seq[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[i] > max) {
                max = dp[i];
            }
        }

        // 결과 출력
        System.out.println(max);
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        seq = new int[N];
        dp = new int[N];
        for (int i = 0 ; i < N ; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
    }
}
