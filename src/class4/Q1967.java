package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q1967 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static class Edge implements Comparable<Edge> {
        int node, weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) { return Integer.compare(weight, o.weight); }
    }

    public static int n;
    public static List<Edge>[] tr;
    public static boolean[] visited;
    public static int max;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());

        // 인접 리스트 그래프
        tr = new ArrayList[n+1];
        for (int i=1; i <n+1; i++) {
            tr[i] = new ArrayList<>();
        }

        for (int i=0; i<n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            tr[u].add(new Edge(v, w));
            tr[v].add(new Edge(u, w));
        }

        int result = 0;
        for (int i=1; i<n+1; i++) {
            max = 0;                    // max 초기화
            visited = new boolean[n+1]; // visited 배열 초기화
            dfs(i, 0);              // 모든 노드에 대해 경로 탐색
            if (max > result) {
                result = max;
            }
        }

        System.out.println(result);
    }

    public static void dfs(int i, int w) {

        // i 노드에 대해 방문 표시
        visited[i] = true;

        // i를 시작점으로 가질 때 트리의 지름 계산
        for (Edge e : tr[i]) {
            // e를 아직 방문하지 않은 경우
            if (!visited[e.node]) {
                visited[e.node] = true;
                dfs(e.node, w+e.weight);
                if (w+e.weight > max) {
                    max = w+e.weight;
                }
            }
        }

    }
}
