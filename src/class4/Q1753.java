package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1753 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        // Input
        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        int[][] graph = new int[V+1][V+1];

        for (int i=1; i<V+1; i++) {
            for (int j=1; j<V+1; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = 100; // INF = 100
                }
            }
        }

        for (int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u][v] = w;
        }

        // Dijkstra
        int[] length = new int[V+1];
        boolean[] visited = new boolean[V+1];

        for (int i=1; i<V+1; i++) {
            length[i] = 100;
            visited[i] = false;
        }
        length[K] = 0;

        for (int i=1; i<V+1; i++) {
            int vnear = -1;
            int minLen = 100;

            // 방문하지 않은 노드 중 최소 length를 가진 vnear 선택
            for (int j=1; j<V+1; j++) {
                if (!visited[j] && length[j] < minLen) {
                    minLen = length[j];
                    vnear = j;
                }
            }

            // vnear가 없다면 종료
            if (vnear == -1) break;

            // vnear에 대해 방문 표시
            visited[vnear] = true;

            // length 업데이트
            for (int j=1; j<V+1; j++) {
                if (!visited[j] && graph[vnear][j] != 100) {
                    length[j] = Math.min(length[vnear] + graph[vnear][j], length[j]);
                }
            }
        }

        // Output
        for (int i=1; i<V+1; i++) {
            System.out.println(length[i] == 100 ? "INF" : length[i]);
        }

    }
}
