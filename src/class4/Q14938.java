package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q14938 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static final int MAX_INT = 100000000;

    private static int n;   // 지역의 개수
    private static int m;   // 수색 범위
    private static int r;   // 길의 개수

    private static int[] items;     // 각 지역에 있는 아이템의 개수
    private static int[][] graph;   // 그래프

    public static void main(String[] args) throws IOException {
        // 입력
        input();

        // 플로이드-워셜 알고리즘 수행
        floyd();

        // 수색 범위 내의 최대 아이템 개수 계산
        System.out.println(calculateMaxItems());
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        items = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1 ; i <= n ; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        graph = new int[n+1][n+1];
        for (int i = 0 ; i <= n ; i++) {
            Arrays.fill(graph[i], MAX_INT);
        }

        for (int i = 0 ; i < r ; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start][end] = cost;
            graph[end][start] = cost;
        }
    }

    private static void floyd() {
        for (int k = 1 ; k <= n ; k++) {
            for (int i = 1 ; i <= n ; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) continue;
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
    }

    private static int calculateMaxItems() {
        int maxItems = 0;
        for (int i = 1; i <= n; i++) {
            int localMaxItems = 0;
            for (int j = 1; j <= n; j++) {
                // 수색 범위 내에 있는 지역의 경우 아이템 개수 합산
                if (i == j || graph[i][j] <= m) {
                    localMaxItems += items[j];
                }
            }
            maxItems = Math.max(maxItems, localMaxItems);
        }
        return maxItems;
    }
}
