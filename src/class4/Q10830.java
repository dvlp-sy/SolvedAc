package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q10830 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static final int MOD = 1000;

    private static int N;
    private static long B;

    private static long[][] A;
    private static long[][] result;

    private static long[][] pow(long[][] A, long exp) {
        if (exp == 1) {
            return A;
        }

        long[][] half = pow(A, exp / 2);

        long[][] res = multiply(half, half);

        if (exp % 2 != 0) {
            res = multiply(res, A);
        }

        return res;
    }

    private static long[][] multiply(long[][] m1, long[][] m2) {
        long[][] temp = new long[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    temp[i][j] = (temp[i][j] + (m1[i][k] * m2[k][j])) % MOD;
                }
            }
        }
        return temp;
    }

    public static void main(String[] args) throws IOException {
        // 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        A = new long[N][N];
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N; j++) {
                A[i][j] = Long.parseLong(st.nextToken()) % 1000;
            }
        }

        // 행렬 제곱 연산
        result = new long[N][N];
        result = pow(A, B);

        // 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }

    }
}
