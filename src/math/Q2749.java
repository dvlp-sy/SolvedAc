package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2749 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int MOD = 1000000;
    private static final int t = 1500000; // MOD로 나누었을 때 피사노 주기

    private static long n;
    private static int[] fibo;

    private static void fibonacci(int n) {
        fibo = new int[n+1];
        fibo[0] = 0;
        fibo[1] = 1;
        for (int i = 2 ; i <= n ; i++) {
            fibo[i] = (fibo[i-1] + fibo[i-2]) % MOD;
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력
        n = Long.parseLong(br.readLine());

        // 피사노 주기로 나누었을 때의 나머지 계산
        int updatedN = Math.toIntExact(n % t);
        fibonacci(updatedN);

        System.out.println((fibo[updatedN]));
    }
}
