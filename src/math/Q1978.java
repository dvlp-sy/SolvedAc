package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1978 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int count = 0;
        for (int i = 0 ; i < N ; i++) {
            int number = Integer.parseInt(st.nextToken());

            // 1은 소수가 아니다
            if (number == 1) continue;

            // 1이 아닌 경우 소수인지 확인한다
            boolean isPrime = true;
            for (int mod = 2 ; mod <= Math.sqrt(number) ; mod++) {
                // 소수인 인수를 발견하면 소수가 아니다
                if (number % mod == 0) {
                    isPrime = false;
                }
            }
            if (isPrime) {
                count++;
            }
        }

        System.out.println(count);
    }
}
