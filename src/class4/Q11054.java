package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class Q11054 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int[] list;
    private static int[] dpIncrease;
    private static int[] dpDecrease;

    private static void increase() {
        for (int i = 0 ; i < N ; i++) {
            dpIncrease[i] = 1;
            for (int j = 0; j < i; j++) {
                if (list[j] < list[i]) {
                    dpIncrease[i] = max(dpIncrease[i], dpIncrease[j] + 1);
                }
            }
        }
    }

    private static void decrease() {
        for (int i = N - 1 ; i >= 0 ; i--) {
            dpDecrease[i] = 1;
            for (int j = N - 1; j > i ; j--) {
                if (list[j] < list[i]) {
                    dpDecrease[i] = max(dpDecrease[i], dpDecrease[j] + 1);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력
        N = Integer.parseInt(br.readLine());
        list = new int[N];
        dpIncrease = new int[N];
        dpDecrease = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N ; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        // 증가하는 부분 수열 계산
        increase();

        // 감소하는 부분 수열 계산
        decrease();

        // 결과 계산
        int result = 0;
        for (int i = 0 ; i < N ; i++) {
            result = max(result, dpIncrease[i] + dpDecrease[i] - 1);
        }
        System.out.println(result);

    }
}
