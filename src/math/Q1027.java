package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1027 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int n;
    private static long[] buildings;

    public static void main(String[] args) throws IOException {
        // 입력
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        buildings = new long[n];
        for (int i = 0 ; i < n ; i++) {
            buildings[i] = Long.parseLong(st.nextToken());
        }

        // 보이는 최대 빌딩 개수
        int maxCnt = 0;

        for (int i = 0 ; i < n ; i++) {

            // 보이는 빌딩 개수
            int cnt = 0;

            // 좌측 빌딩
            double minGrad = Long.MAX_VALUE;
            for (int j = i - 1 ; j >= 0 ; j--) {
                // 현재 기울기
                double grad = (buildings[i] - buildings[j]) / (double) (i - j);
                if (grad < minGrad) {
                    cnt++;
                    minGrad = grad;
                }
            }

            // 우측 빌딩
            double maxGrad = Long.MIN_VALUE;
            for (int j = i + 1; j < n ; j++) {
                // 현재 기울기
                double grad = (buildings[j] - buildings[i]) / (double) (j - i);
                if (grad > maxGrad) {
                    cnt++;
                    maxGrad = grad;
                }
            }

            // maxCnt 업데이트
            maxCnt = Math.max(maxCnt, cnt);
        }

        System.out.println(maxCnt);
    }
}
