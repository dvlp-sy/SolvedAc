package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1937 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int maxDepth;

    private static int[][] map;
    private static final int[] xDelta = { 1, -1, 0, 0 };
    private static final int[] yDelta = { 0, 0, 1, -1 };
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        // 입력 및 초기화
        init();

        // dfs 수행
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                maxDepth = Math.max(maxDepth, dfsWithDp(i, j));
            }
        }


        // 출력
        System.out.println(maxDepth);
    }

    private static void init() throws IOException{
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void dfs(int x, int y, int depth) {
        // 현재 depth가 기록된 maxDepth보다 크면 갱신한다
        if (depth > maxDepth) {
            maxDepth = depth;
        }

        for (int i = 0 ; i < 4 ; i++) {
            int nextX = x + xDelta[i];
            int nextY = y + yDelta[i];

            if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;

            // 다음 좌표가 현재 좌표보다 값이 크면 이동한다
            if (map[nextX][nextY] > map[x][y]) {
                dfs(nextX, nextY, depth + 1);
            }
        }
    }

    private static int dfsWithDp(int x, int y) {
        if (dp[x][y] != 0) {
            return dp[x][y];
        }

        dp[x][y] = 1;

        for (int i = 0 ; i < 4 ; i++) {
            int nextX = x + xDelta[i];
            int nextY = y + yDelta[i];

            if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;

            // 다음 좌표가 현재 좌표보다 값이 크면 이동한다
            if (map[nextX][nextY] > map[x][y]) {
                dp[x][y] = Math.max(dp[x][y], dfsWithDp(nextX, nextY) + 1);
            }
        }

        return dp[x][y];
    }
}
