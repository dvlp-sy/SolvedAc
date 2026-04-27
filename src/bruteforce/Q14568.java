package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q14568 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        // 택희가 i개의 사탕, 영훈이가 j개의 사탕, 남규가 N-i-j개의 사탕을 가진다고 가정
        int count = 0;
        for (int i = 1 ; i < N ; i++) {
            for (int j = 1 ; j < N ; j++) {
                int k = N - i - j;
                // k가 1보다 작은 경우 조건을 위배하므로 건너뛴다
                if (k < 1) continue;
                // 조건1. 남규는 영훈이보다 2개 이상 많은 사탕을 가져야 한다
                if (k < j + 2) continue;
                // 조건2. 택희는 짝수 개의 사탕을 가져야 한다
                if (i % 2 != 0) continue;
                // 모든 조건을 만족하는 경우 카운트 증가
                count++;
            }
        }

        System.out.println(count);
    }
}
