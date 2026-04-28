package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Q14232 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static long k;
    private static List<Long> result = new ArrayList<>();

    public static int recur(long k) {
        // k의 약수 중 트리를 가장 고르게 분배할 수 있는 약수 찾기
        long nextK = 1;
        for (long i = 2 ; i <= Math.sqrt(k) ; i++) {
            if (k % i == 0) {
                nextK = i;
            }
        }

        // nextK == 1인 경우는 소수를 의미하므로, 소수를 리스트에 추가하고 1을 반환한다
        if (nextK == 1) {
            result.add(k);
            return 1;
        }
        return recur(nextK) + recur(k / nextK);
    }

    public static void main(String[] args) throws IOException {
        long k = Long.parseLong(br.readLine());

        System.out.println(recur(k));

        result.sort(Long::compareTo);
        for (long num : result) {
            System.out.print(num + " ");
        }
    }
}
