package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q11779 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static final int INF = 100000000;

    private static int n;
    private static int m;

    private static int start;
    private static int end;

    private static List<List<Edge>> graph;
    private static Queue<Edge> pq;

    private static class Edge implements Comparable<Edge> {
        int city;
        int cost;

        Edge(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    private static class Result {
        int cost;
        Stack<Integer> path;

        Result(int cost, Stack<Integer> path) {
            this.cost = cost;
            this.path = path;
        }
    }

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        // 그래프 및 우선순위 큐 초기화
        pq = new PriorityQueue<>();
        graph = new ArrayList<>();
        for (int i = 0 ; i <= n ; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0 ; i < m ; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Edge(end, cost));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        // 다익스트라 알고리즘 수행
        Result result = dijkstra(start);
        System.out.println(result.cost);
        System.out.println(result.path.size());
        while (!result.path.isEmpty()) {
            System.out.print(result.path.pop() + " ");
        }
        System.out.println();
    }

    private static Result dijkstra(int start) {
        int[] dist = new int[n+1];
        int[] parent = new int[n+1];
        Arrays.fill(dist, INF);

        dist[start] = 0;
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            // 이미 더 짧은 경로가 존재한다면 확인하지 않음
            if (current.cost > dist[current.city]) continue;

            for (Edge next : graph.get(current.city)) {
                int nextDistance = current.cost + next.cost;

                // 현재 도시와 다음 도시가 같다면 확인하지 않음
                if (current.city == next.city) continue;

                if (nextDistance < dist[next.city]) {
                    dist[next.city] = nextDistance;
                    parent[next.city] = current.city;
                    pq.add(new Edge(next.city, nextDistance));
                }
            }
        }

        return new Result(dist[end], getPath(end, parent));
    }

    private static Stack<Integer> getPath(int end, int[] parent) {
        Stack<Integer> path = new Stack<>();
        path.add(end);

        int currentCity = end;
        while (true) {
            int parentCity = parent[currentCity];
            if (parentCity == 0) {
                break;
            }
            path.add(parentCity);
            currentCity = parentCity;
        }

        return path;
    }
}
