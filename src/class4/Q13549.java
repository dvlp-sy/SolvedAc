package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Q13549 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static Deque<Integer> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] visited = new int[1000001];

        // 방문하지 않은 노드를-1로 초기화
        Arrays.fill(visited, -1);

        q.add(N);
        visited[N] = 0;
        
        // queue에 값이 존재하는 동안 반복
        while (!q.isEmpty()) {

            // queue에서 새로운 노드 꺼내기
            int current = q.poll();

            // 해를 발견하면 종료
            if (current == K) {
                break;
            }

            // 순간이동 우선 처리
            if (current * 2 <= 100000 && visited[current * 2] == -1) {
                visited[current * 2] = visited[current]; // 가중치 0
                q.addFirst(current * 2);
            }

            if (current - 1 >= 0 && visited[current - 1] == -1) {
                visited[current - 1] = visited[current] + 1;
                q.addLast(current - 1);
            }
            if (current + 1 <= 100000 && visited[current + 1] == -1) {
                visited[current + 1] = visited[current] + 1;
                q.addLast(current + 1);
            }
        }

        System.out.println(visited[K]);

    }
}
