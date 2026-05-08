package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9251 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static char[] str1;
    private static char[] str2;

    private static int N;
    private static int M;

    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        // 입력 및 초기화
        str1 = br.readLine().toCharArray();
        str2 = br.readLine().toCharArray();

        N = str1.length;
        M = str2.length;

        dp = new int[N + 1][M + 1];

        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= M ; j++) {
                if (str1[i-1] == str2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        // 결과 출력
        System.out.println(dp[N][M]);
    }
}
