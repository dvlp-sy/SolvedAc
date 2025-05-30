package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1082 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static int N;
    public static int M;
    public static int[] price;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine()); // 숫자의 개수

        price = new int[N];                      // 숫자 가격 배열
        int minPrice = Integer.MAX_VALUE;             // 0을 제외하고 가장 낮은 숫자 가격
        int minNum = -1;                         // 가격이 가장 낮은 숫자

        // 숫자 가격 배열 생성
        st = new StringTokenizer(br.readLine());
        for (int num = 0; num < N; num++) {
            price[num] = Integer.parseInt(st.nextToken());
            if (num != 0 && price[num] < minPrice) {
                minPrice = price[num];
                minNum = num;
            }
        }

        M = Integer.parseInt(br.readLine());    // 최대 금액


        // 0 외의 다른 숫자가 없거나 min > M 인 경우
        if (minNum == -1 || minPrice > M) {
            // 0의 가격 <= M인 경우 0 출력
            if (price[0] <= M) {
                System.out.println(0);
            } else {
                System.out.println();
            }
            return ;
        }

        // 최대 자릿수 계산
        int maxDigit = (M - price[minNum])/Math.min(minPrice, price[0]) + 1;

        // 최종 숫자 배열 선언
        int[] digits = new int[maxDigit];

        // 최소 비용으로 초기화
        digits[0] = minNum;
        int currentPrice = minPrice;
        for (int idx = 1; idx < maxDigit; idx++) {
            // 가장 큰 자릿수가 아니라면 0의 가격과 min 중 더 작은 값으로 계산
            if (price[0] < minPrice) {
                digits[idx] = 0;
                currentPrice += price[0];
            } else {
                digits[idx] = minNum;
                currentPrice += minPrice;
            }
        }

        // 최종 숫자 배열 업데이트
        for (int i = 0; i < maxDigit; i++) {
            for (int j = N - 1; j > digits[i]; j--) {
                int diff = price[j] - price[digits[i]];
                if (currentPrice + diff <= M) {
                    currentPrice += diff;
                    digits[i] = j;
                    break;
                }
            }
        }

        for (int i=0; i<maxDigit; i++) {
            System.out.print(digits[i]);
        }
    }

}
