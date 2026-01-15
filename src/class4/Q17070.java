package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 파이프 옮기기 1
 * @link <a href="https://www.acmicpc.net/problem/17070">BOJ 17070</a>
 */
public class Q17070 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int[][] house;
    private static int count = 0;

    private static int determineType(int i1, int j1, int i2, int j2) {
        if (i2 - i1 == 0 && j2 - j1 == 1) return 0; // 가로
        if (i2 - i1 == 1 && j2 - j1 == 0) return 1; // 세로
        if (j2 - j1 == 1 && i2 - i1 == 1) return 2; // 대각선
        return -1;
    }

    private static boolean isMovable(int type, int i2, int j2) {
        if (type == 2) {
            return house[i2][j2] == 0 && house[i2 - 1][j2] == 0 && house[i2][j2 - 1] == 0;
        } else {
            return house[i2][j2] == 0;
        }
    }

    private static void countCases(int i1, int j1,  int i2, int j2) {
        // 파이프가 공간을 벗어나는 경우 예외 처리
        if (i2 >= N || j2 >= N) {
            return ;
        }
        // 타입(가로, 세로, 대각선) 확인
        int type = determineType(i1, j1, i2, j2);
        // 파이프가 벽에 부딪히는 경우 예외 처리
        if (!isMovable(type, i2, j2)) {
            return ;
        }
        // 파이프가 (N, N)에 도달하면 count 1 증가
        if (i2 == N-1 && j2 == N-1) {
            count++;
            return ;
        }
        if (type == 0) {
            countCases(i1, j1 + 1, i2, j2 + 1);
            countCases(i1, j1 + 1, i2 + 1, j2 + 1);
        } else if (type == 1) {
            countCases(i1 + 1, j1, i2 + 1, j2);
            countCases(i1 + 1, j1, i2 + 1, j2 + 1);
        } else {
            countCases(i1 + 1, j1 + 1, i2, j2 + 1);
            countCases(i1 + 1, j1 + 1, i2 + 1, j2);
            countCases(i1 + 1, j1 + 1, i2 + 1, j2 + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력
        N = Integer.parseInt(br.readLine());
        house = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 경우의 수 계산
        countCases(0, 0, 0, 1);
        System.out.println(count);
    }
}
