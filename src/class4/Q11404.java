package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Math.min;

public class Q11404 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static final int MAX_INT = 100000000;
    private static int n;
    private static int m;

    private static int[][] graph;
    private static int[][] result;

    private static void floyd() {
        for (int k = 1 ; k < n + 1 ; k++) {
            for (int i = 1 ; i < n + 1 ; i++) {
                for (int j = 1 ; j < n + 1 ; j++) {
                    // 시작 지점과 도착 지점이 같은 경우는 확인하지 않음
                    if (i == j) continue;
                    // i -> j , i -> k -> j 중 더 짧은 경로 저장
                    result[i][j] = min(result[i][j], result[i][k] + result[k][j]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new int[n+1][n+1];
        result = new int[n+1][n+1];
        for (int i = 1 ; i < n+1 ; i++) {
            Arrays.fill(graph[i], MAX_INT);
            Arrays.fill(result[i], MAX_INT);
        }

        for (int i = 0 ; i < m ; i ++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start][end] = min(graph[start][end], cost);
            result[start][end] = graph[start][end];
        }

        // 최단 경로 계산
        floyd();

        // 출력
        for (int i = 1 ; i < n + 1 ; i++) {
            for (int j = 1 ; j < n + 1; j++) {
                if (result[i][j] == MAX_INT) {
                    System.out.print(0 + " ");
                } else {
                    System.out.print(result[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
