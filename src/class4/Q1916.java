package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1916 {

    public static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static int[][] graph;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(bf.readLine());
        int M = Integer.parseInt(bf.readLine());

        graph = new int[N + 1][N + 1];

        // Initialization
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[v1][v2] = Math.min(graph[v1][v2], w);  // 같은 노드에 대해 여러 버스가 있을 수 있으므로 최소값 저장
        }

        st = new StringTokenizer(bf.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        // Dijkstra
        dijkstra(start, N, end);
    }

    public static void dijkstra(int start, int N, int end) {

        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        // Initialization
        for (int i = 1; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE;  // 기본적으로는 모두 무한대로 설정
            visited[i] = false;
        }
        dist[start] = 0;  // 시작점의 거리는 0

        // Dijkstra
        for (int i = 1; i <= N; i++) {
            int vnear = -1;
            int minDist = Integer.MAX_VALUE;

            // 아직 방문하지 않은 노드 중 최단 거리를 가진 노드 찾기
            for (int j = 1; j <= N; j++) {
                if (!visited[j] && dist[j] < minDist) {
                    vnear = j;
                    minDist = dist[j];
                }
            }

            if (vnear == -1) break;  // 더 이상 방문할 노드가 없으면 종료

            visited[vnear] = true;  // 선택한 노드를 방문 처리

            // 거리 갱신
            for (int j = 1; j <= N; j++) {
                if (!visited[j] && graph[vnear][j] != Integer.MAX_VALUE) {
                    dist[j] = Math.min(dist[j], dist[vnear] + graph[vnear][j]);
                }
            }
        }

        System.out.println(dist[end]);
    }
}
