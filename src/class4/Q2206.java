package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2206 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int M;

    private static int[][] map;
    private static int[] xDelta = { 1, -1, 0, 0 };
    private static int[] yDelta = { 0, 0, -1, 1 };

    private static Queue<Node> queue;

    private static class Node {
        int x;
        int y;
        int path;
        boolean broken;

        public Node(int x, int y, int path, boolean broken) {
            this.x = x;
            this.y = y;
            this.path = path;
            this.broken = broken;
        }
    }

    private static int getShortestPath(Node start) {
        queue = new LinkedList<>();
        queue.offer(start);

        // visited[x][y][0] : 문을 한 번도 부수지 않고 (x, y)에 방문 여부
        // visited[x][y][1] : 문을 한 번 부수고 (x, y)에 방문 여부
        boolean[][][] visited = new boolean[N][M][2];
        visited[start.x][start.y][0] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // (x, y)가 끝점이면 종료
            if (current.x == N - 1 && current.y == M - 1) {
                return current.path;
            };

            for (int i = 0; i < 4; i++) {
                int row = current.x + xDelta[i];
                int col = current.y + yDelta[i];

                // row, col이 범위를 벗어나면 예외 처리
                if (row < 0 || row >= N || col < 0 || col >= M) continue;

                // 다음 칸에 벽이 없는 경우
                if (map[row][col] == 0) {
                    int status = current.broken ? 1 : 0;
                    if (!visited[row][col][status]) {
                        visited[row][col][status] = true;
                        queue.offer(new Node(row, col, current.path + 1, current.broken));
                    }
                }

                // 다음 칸에 벽이 있는 경우
                if (map[row][col] == 1) {
                    // 이미 벽을 부수고 왔다면 패스
                    if (current.broken) continue;
                    // 아직 벽을 부수지 않은 경우 벽을 부수고 이동
                    if (!visited[row][col][1]) {
                        visited[row][col][1] = true;
                        queue.offer(new Node(row, col, current.path + 1, true));
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        // 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0 ; i < N ; i++) {
            String input = br.readLine();
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = input.toCharArray()[j] - '0';
            }
        }

        // BFS로 최단경로 계산
        int shortestPath = getShortestPath(new Node(0, 0, 1, false));

        // 출력
        System.out.println(shortestPath);
    }
}
