package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * floyd : O(N^3) {@link Q1238_floyd} <br>
 * dijkstra : O(M logN)
 */
public class Q1238_dijkstra {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static final int INF = 100000000;

    private static int N;
    private static int M;
    private static int X;

    private static int[][] graph;
    private static int[][] reversedGraph;

    private static Queue<Edge> pq = new PriorityQueue<>();

    private static class Edge implements Comparable<Edge>{
        int dst;
        int distance;

        public Edge(int dst, int distance) {
            this.dst = dst;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.distance, o.distance);
        }
    }

    private static int[] dijkstra(int[][] graph, int v) {
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);
        pq.offer(new Edge(v, 0));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int currentNode = edge.dst;
            int currentDist = edge.distance;

            if (currentDist > dist[currentNode]) continue;

            for (int next = 1 ; next <= N ; next++) {
                // 간선이 존재하면 distance 업데이트
                if (graph[currentNode][next] != INF) {
                    int nextDist = currentDist + graph[currentNode][next];
                    if (nextDist < dist[next]) {
                        dist[next] = nextDist;
                        pq.offer(new Edge(next, dist[next]));
                    }
                }
            }
        }
        return dist;
    }

    private static int calculateMax(int[] dist, int[] reversedDist) {
        int max = 0;
        for (int i = 1 ; i <= N ; i++) {
            if (i != X) {
                // dist[i] = X부터 i까지 거리
                // reversedDist[i] = i부터 X까지 거리
                max = Math.max(max, dist[i] + reversedDist[i]);
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
        reversedGraph = new int[N+1][N+1];
        for (int i = 1 ; i <= N ; i++) {
            Arrays.fill(graph[i], INF);
            Arrays.fill(reversedGraph[i], INF);
        }
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start][end] = Math.min(graph[start][end], Integer.parseInt(st.nextToken()));
            reversedGraph[end][start] = graph[start][end];
        }

        // 순방향 그래프에 대한 최단경로 계산
        int[] graphDist = dijkstra(graph, X);

        // 역방향 그래프에 대한 최단경로 계산
        int[] reversedGraphDist = dijkstra(reversedGraph, X);

        // 최단 경로 출력
        System.out.println(calculateMax(graphDist, reversedGraphDist));
    }
}
