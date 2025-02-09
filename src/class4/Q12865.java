package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q12865 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] p = new int[N+1];
        int[] we = new int[N+1];
        int[] dp = new int[K+1];

        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            we[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        // dp[w][i] = max(dp[w][i-1], dp[w-we[i]][i-1]+p[i])
        // dp[w] = max(dp[w], dp[w-we[i]]+p[i])
        for (int i=1; i<=N; i++) {
            for (int w=K; w>=0; w--) {
                if (w-we[i] >= 0) {
                    dp[w] = Math.max(dp[w], dp[w-we[i]] + p[i]);
                }
            }
        }

        System.out.println(dp[K]);
    }
}
