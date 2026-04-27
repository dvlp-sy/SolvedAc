package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17144 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int R; // 방의 세로 크기
    private static int C; // 방의 가로 크기
    private static int T; // 초

    private static int[] rowDelta = { 1, -1, 0, 0 };
    private static int[] colDelta = { 0, 0, 1, -1 };

    private static int[][] map; // 방의 상태

    private static int airCleanerRow;

    public static void main(String[] args) throws IOException {
        // 입력
        input();

        // 시뮬레이션 수행
        simulate();

        // 결과 계산
        int result = 0;
        for (int i = 0 ; i < R ; i++) {
            for (int j = 0 ; j < C ; j++) {
                if (map[i][j] > 0) {
                    result += map[i][j];
                }
            }
        }
        System.out.println(result);
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0 ; i < R ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < C ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    airCleanerRow = i;
                }
            }
        }
    }

    private static void simulate() {
        for (int t = 0 ; t < T ; t++) {
            // 미세먼지 확산
            diffuse();
            // 공기청정기 작동
            operateAirCleaner();
        }
    }

    private static void diffuse() {
        int[][] tempMap = new int[R][C];

        for (int i = 0 ; i < R ; i++) {
            for (int j = 0 ; j < C ; j++) {
                if (map[i][j] > 0) {
                    int amount = map[i][j] / 5;
                    int count = 0;

                    for (int k = 0 ; k < 4 ; k++) {
                        int nextRow = i + rowDelta[k];
                        int nextCol = j + colDelta[k];

                        // 다음 위치가 방을 벗어나는 경우 확산 X
                        if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C) continue;

                        // 다음 위치가 공기청정기인 경우 확산 X
                        if (map[nextRow][nextCol] == -1) continue;

                        // 다음 위치로 확산
                        tempMap[nextRow][nextCol] += amount;
                        count++;
                    }
                    tempMap[i][j] += (map[i][j] - (amount * count));
                } else if (map[i][j] == -1) {
                    tempMap[i][j] = -1;
                }
            }
        }

        for (int i = 0 ; i < R ; i++) {
            map[i] = tempMap[i].clone();
        }
    }

    private static void operateAirCleaner() {
        int top = airCleanerRow - 1;
        int bottom = airCleanerRow;

        // 반시계 방향 동작
        // 왼쪽
        for (int i = top - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }
        // 위쪽
        for (int i = 0; i < C - 1; i++) {
            map[0][i] = map[0][i + 1];
        }
        // 오른쪽
        for (int i = 0; i < top; i++) {
            map[i][C - 1] = map[i + 1][C - 1];
        }
        // 아래쪽
        for (int i = C - 1; i > 1; i--) {
            map[top][i] = map[top][i - 1];
        }
        map[top][1] = 0;

        // 시계 방향 동작
        // 왼쪽
        for (int i = bottom + 1; i < R - 1; i++) {
            map[i][0] = map[i + 1][0];
        }
        // 아래쪽
        for (int i = 0; i < C - 1; i++) {
            map[R - 1][i] = map[R - 1][i + 1];
        }
        // 오른쪽
        for (int i = R - 1; i > bottom; i--) {
            map[i][C - 1] = map[i - 1][C - 1];
        }
        // 위쪽
        for (int i = C - 1; i > 1; i--) {
            map[bottom][i] = map[bottom][i - 1];
        }
        map[bottom][1] = 0;
    }
}
