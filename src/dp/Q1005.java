package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1005 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static int N;
    public static int K;
    public static boolean[][] graph;
    public static int[] time;
    public static int[] indegree;
    public static int[] dp;
    public static Queue<Integer> queue = new LinkedList<>();

    public static void getMinTime() {

        while (!queue.isEmpty()) {
            // 상위 노드 추출
            int current = queue.poll();

            // 진입차수가 0인 end의 자식 노드를 큐에 추가
            for (int next=1; next<N+1; next++) {
                // 연결된 간선이 있는 경우
                if (graph[current][next]) {
                    dp[next] = Math.max(dp[next], dp[current] + time[next]);

                    indegree[next]--;
                    if (indegree[next] == 0) {
                        queue.add(next);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int i = 0; i<T; i++) {

            // 건물 개수, 규칙 개수 입력
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());   // 건물 개수
            K = Integer.parseInt(st.nextToken());   // 규칙 개수

            // 건물당 건설 시간 배열
            time = new int[N+1];

            // 총 소요 시간 배열
            dp = new int[N+1];

            // 노드(건물)별 진입차수 저장 배열
            indegree = new int[N+1];

            // 건물당 건설 시간 입력
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j<N+1; j++) {
                time[j] = Integer.parseInt(st.nextToken());
                dp[j] = time[j];
                dp[j] = time[j];
            }

            // 건물 그래프 생성
            graph = new boolean[N+1][N+1];
            for (int j = 1; j<K+1; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());   // 시작 건물
                int end = Integer.parseInt(st.nextToken());     // 끝 건물

                indegree[end]++;                                // 진입차수 증가
                graph[start][end] = true;                       // 존재하는 간선에 표시
            }

            // 건설해야 할 건물의 번호 입력
            int W = Integer.parseInt(br.readLine());

            // 루트 노드 추가
            for (int node=1; node<N+1; node++) {
                if (indegree[node] == 0) queue.add(node);
            }

            // 누적 시간 계산
            getMinTime();

            System.out.println(dp[W]);
        }
    }
}
