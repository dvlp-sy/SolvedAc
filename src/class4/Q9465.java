package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q9465 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());

        for (int t=0; t<T; t++) {
            int n = Integer.parseInt(br.readLine());

            // 원본 배열
            int[][] A = new int[2][n];

            // dp 배열
            int[][] S = new int[2][n];

            for (int j=0; j<2; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k=0; k<n; k++) {
                    A[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            // dp 배열 초기화
            S[0][0] = A[0][0];
            S[1][0] = A[1][0];
            if (n > 1) {
                S[0][1] = A[1][0] + A[0][1];
                S[1][1] = A[0][0] + A[1][1];
            }

            // dp 배열 계산
            for (int i=2; i<n; i++) {
                // 1. S[1][i-1] + A[0][i])
                // 2. max(S[0][i-2], S[1][i-2]) + A[0][i]
                int s01 = S[1][i-1] + A[0][i];
                int s02 = Math.max(S[0][i-2], S[1][i-2]) + A[0][i];
                S[0][i] = Math.max(s01, s02);

                // 1. S[0][i-1] + A[1][i]
                // 2. max(S[0][i-2], S[1][i-2]) + A[1][i]
                int s11 = S[0][i-1] + A[1][i];
                int s12 = Math.max(S[0][i-2], S[1][i-2]) + A[1][i];
                S[1][i] = Math.max(s11, s12);
            }

            System.out.println(Math.max(S[0][n-1], S[1][n-1]));
        }

    }
}
