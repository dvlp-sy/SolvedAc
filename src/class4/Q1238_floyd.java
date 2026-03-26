package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * floyd : O(N^3) <br>
 * dijkstra : O(M logN) {@link Q1238_dijkstra}
 */
public class Q1238_floyd {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static final int INF = 100000000;

    private static int N;
    private static int M;
    private static int X;

    private static int[][] graph;
    private static int[][] result;

    private static void floyd() {
        // k를 경유하는 모든 경로 확인
        for (int k = 1 ; k <= N ; k++) {
            for (int i = 1 ; i <= N ; i++) {
                for (int j = 1 ; j <= N ; j++) {
                    // 출발점과 도착점이 같은 경우 예외 처리
                    if (i == j) continue;
                    // i -> j, i -> k -> j 중 짧은 경로로 지정
                    result[i][j] = Math.min(result[i][j], result[i][k] + result[k][j]);
                }
            }
        }
    }

    private static int calculateMax() {
        int max = 0;
        for (int i = 1 ; i <= N ; i++) {
            if (i != X) {
                max = Math.max(max, result[i][X] + result[X][i]);
            }
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        // 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new int[N+1][N+1];
        result = new int[N+1][N+1];
        for (int i = 1 ; i <= N ; i++) {
            Arrays.fill(graph[i], INF);
            Arrays.fill(result[i], INF);
        }
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start][end] = Math.min(graph[start][end], Integer.parseInt(st.nextToken()));
            result[start][end] = graph[start][end];
        }

        // 최단 경로 계산
        floyd();

        // 최단 경로 출력
        System.out.println(calculateMax());
    }
}
