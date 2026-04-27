package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2503 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int result;
    private static Info[] infoArr;

    private static class Info {
        int[] number;
        int strike;
        int ball;

        Info(int[] number, int strike, int ball) {
            this.number = number;
            this.strike = strike;
            this.ball = ball;
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력
        N = Integer.parseInt(br.readLine());
        infoArr = new Info[N];
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());

            int[] numberArr = new int[3];
            for (int j = 2 ; j >= 0 ; j--) {
                numberArr[j] = number % 10;
                number /= 10;
            }
            infoArr[i] = new Info(numberArr, strike, ball);
        }

        // 가능한 모든 숫자 조합으로 완전탐색
        // 백의 자리 x, 십의 자리 y, 일의 자리 z로 표현
        for (int x = 1 ; x < 10 ; x++) {
            for (int y = 1 ; y < 10 ; y++) {
                for (int z = 1 ; z < 10; z++) {

                    // 세 숫자는 서로 다른 숫자여야 한다
                    if (x == y || y == z || z == x) continue;

                    boolean isPossible = true;
                    for (Info info : infoArr) {
                        int strikeCount = 0;
                        int ballCount = 0;

                        for (int i = 0 ; i < 3 ; i++) {
                            if (i == 0 && x == info.number[i]) {
                                strikeCount++;
                            } else if (i == 1 && y == info.number[i]) {
                                strikeCount++;
                            } else if (i == 2 && z == info.number[i]) {
                                strikeCount++;
                            } else if (x == info.number[i]) {
                                ballCount++;
                            } else if (y == info.number[i]) {
                                ballCount++;
                            } else if (z == info.number[i]) {
                                ballCount++;
                            }
                        }

                        // 각 조건별로 스트라이크와 볼 개수가 맞지 않는 경우 반복을 중단하고 다음으로 넘어간다
                        if (strikeCount != info.strike || ballCount != info.ball) {
                            isPossible = false;
                            break;
                        }
                    }

                    // 주어진 조건을 모두 충족하는 세자리수라면 result를 증가시킨다
                    if (isPossible) {
                        result++;
                    }
                }
            }
        }

        // 출력
        System.out.println(result);
    }
}
