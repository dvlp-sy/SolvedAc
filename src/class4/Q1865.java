package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Bellman-Form Algorithm
 */
public class Q1865 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static final int INF = 100000000;

    private static int TC;

    private static int N;
    private static int M;
    private static int W;

    private static int[][] graph;
    private static int[] dist;

    private static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int distance;

        Edge(int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.distance, o.distance);
        }
    }

    private static String bellmanFord(List<Edge> edges) {
        Arrays.fill(dist, 0);

        // N-1번 반복하며 모든 간선의 거리 갱신
        for (int i = 1 ; i < N ; i++) {
            for (Edge edge : edges) {
                int nextDist = dist[edge.start] + edge.distance;
                if (dist[edge.start] != INF && dist[edge.end] > nextDist) {
                    dist[edge.end] = nextDist;
                }
            }
        }

        // N번째에 값이 또 갱신된다면 음수 사이클 존재
        for (Edge edge : edges) {
            if (dist[edge.end] > dist[edge.start] + edge.distance) {
                return "YES";
            }
        }
        return "NO";
    }

    private static void operate() throws IOException {
        // 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        List<Edge> edges = new ArrayList<>();

        graph = new int[N+1][N+1];
        dist = new int[N+1];
        for (int i = 0 ; i <= N ; i++) {
            Arrays.fill(graph[i], INF);
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            // 도로는 방향 X
            graph[row][col] = time;
            graph[col][row] = time;
            // 에지 리스트에 추가
            edges.add(new Edge(row, col, time));
            edges.add(new Edge(col, row, time));
        }

        for (int i = 0 ; i < W ; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int time = - Integer.parseInt(st.nextToken());
            // 웜홀은 방향 O
            graph[row][col] = time;
            // 에지 리스트에 추가
            edges.add(new Edge(row, col, time));
        }

        // 웜홀의 음수 사이클 확인
        System.out.println(bellmanFord(edges));
    }

    public static void main(String[] args) throws IOException {
        // 입력
        TC = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < TC ; i++) {
            operate();
        }
    }
}
