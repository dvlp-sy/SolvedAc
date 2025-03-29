package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1753_PQ {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    static class Edge implements Comparable<Edge> {
        int node, weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public static void main(String[] args) throws IOException {

        // Input
        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        // 인접 리스트 그래프
        List<Edge>[] graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
        }

        // Dijkstra
        dijkstra(K, V, graph);
    }

    public static void dijkstra(int start, int V, List<Edge>[] graph) {

        // Initialization
        int[] length = new int[V + 1];
        Arrays.fill(length, Integer.MAX_VALUE);
        length[start] = 0;

        // Priority Queue 초기 상태 설정
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge vnear = pq.poll();
            int currentNode = vnear.node;
            int currentDistance = vnear.weight;

            // 현재 노드의 최단 거리가 이미 기록된 값보다 크면 skip
            // 예를 들어 vnear가 (4,6)인데 length[4]에 기록된 weight=5인 경우
            if (currentDistance > length[currentNode]) continue;

            // length 업데이트
            for (Edge next : graph[currentNode]) {
                int nextNode = next.node;
                int newDist = currentDistance + next.weight;

                if (newDist < length[nextNode]) {
                    length[nextNode] = newDist;
                    pq.offer(new Edge(nextNode, newDist));
                }
            }
        }

        // Output
        for (int i = 1; i <= V; i++) {
            System.out.println(length[i] == Integer.MAX_VALUE ? "INF" : length[i]);
        }
    }
}
