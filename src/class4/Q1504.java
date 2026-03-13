package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1504 {

    private static final int INF = 2000000;
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int E;

    private static int v1;
    private static int v2;

    private static int[][] graph;

    private static int[] dist1;
    private static int[] dist_v1;
    private static int[] dist_v2;

    private static class Edge implements Comparable<Edge> {
        int node;
        int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    private static int[] dijkstra(int v) {
        // dist 배열 초기화
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);
        dist[v] = 0;

        // Priority Queue 초기화
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(v, 0));

        while (!pq.isEmpty()) {
            Edge vnear = pq.poll();
            int currentNode = vnear.node;
            int currentDistance = vnear.weight;

            if (currentDistance > dist[currentNode]) continue;

            for (int next = 1; next < N+1; next++) {
                if (graph[currentNode][next] != 0) {
                    int newDistance = currentDistance + graph[currentNode][next];

                    // 현재 next까지의 최단 경로와 새로 계산한 경로 비교 후 업데이트
                    if (newDistance < dist[next]) {
                        dist[next] = newDistance;
                        pq.offer(new Edge(next, newDistance));
                    }
                }
            }
        }
        return dist;
    }

    private static int calculateMin() {
        // 1 -> v1 -> v2 -> N
        int candidate1 = dist1[v1] + dist_v1[v2] + dist_v2[N];
        // 1 -> v2 -> v1 -> N
        int candidate2 = dist1[v2] + dist_v2[v1] + dist_v1[N];

        // 최솟값 계산
        int min = Math.min(candidate1, candidate2);
        if (min >= INF) {
            return -1;
        } else {
            return min;
        }
    }

    public static void main(String[] args) throws IOException {
        // Input
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new int[N+1][N+1];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a][b] = c;
            graph[b][a] = c;
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        // 1번 정점에서부터 모든 정점까지의 최단 거리 계산 (dijkstra)
        dist1 = dijkstra(1);
        // v1에서부터 모든 정점까지의 최단 거리 계산 (dijkstra)
        dist_v1 = dijkstra(v1);
        // v2에서부터 모든 정점까지의 최단 거리 계산 (dijkstra)
        dist_v2 = dijkstra(v2);

        System.out.println(calculateMin());
    }
}
