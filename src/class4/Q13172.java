package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q13172 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static final long MOD = 1000000007;

    private static int M;
    private static long result = 0;


    private static Pair[] numList;

    private static class Pair {
        long Ni;
        long Si;

        public Pair(int Ni, int Si) {
            this.Ni = Ni;
            this.Si = Si;
        }
    }

    private static long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            // 지수가 홀수인 경우 결과에 base를 먼저 곱한다
            if (exp % 2 == 1) {
                res = (res * base) % MOD;
            }
            // 지수를 짝수로 맞춘 상태에서 반으로 줄인다
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res;
    }

    private static void calculate() {
        for (int i = 0 ; i < M ; i++) {
            long Ni = numList[i].Ni;
            long Si = numList[i].Si;

            // 1,000,000,007에 대한 Ni의 역원 계산
            // Ni * (Ni^{-1})을 1,000,000,007으로 나누었을 때 나머지가 1이어야 함
            // 페르마의 소정리에 의해 Ni의 역원은 Ni의 1,000,000,005제곱이다
            long inv = power(Ni, MOD - 2);

            // (Si * 역원)에 대한 모듈로 연산
            result = (result + (Si * inv) % MOD) % MOD;
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력
        M = Integer.parseInt(br.readLine());
        numList = new Pair[M];
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            numList[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 계산
        calculate();

        // 출력
        System.out.println(result);
    }
}
