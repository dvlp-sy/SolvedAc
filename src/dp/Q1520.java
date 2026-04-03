package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1520 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int M;
    private static int N;

    private static final int[] xDelta = { 1, -1, 0, 0 };
    private static final int[] yDelta = { 0, 0, 1, -1 };

    private static int[][] map;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        // 입력
        input();

        // bfs로 경로의 수 계산
        int result = dfs(0, 0);

        // 출력
        System.out.println(result);
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], -1);
        }
    }

    private static int dfs(int x, int y) {
        // (x, y)가 끝점이면 경로 추가
        if (x == M - 1 && y == N - 1) {
            return 1;
        }

        // 이미 방문한 칸이면 중복 계산하지 않고 dp 값 반환
        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        // 방문하지 않은 칸이면 dp 계산
        dp[x][y] = 0;

        for (int i = 0 ; i < 4 ; i++) {
            int nextX = x + xDelta[i];
            int nextY = y + yDelta[i];

            if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N) continue;

            if (map[nextX][nextY] < map[x][y]) {
                dp[x][y] += dfs(nextX, nextY);
            }
        }

        return dp[x][y];
    }

}
