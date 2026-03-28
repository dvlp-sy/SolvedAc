package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2638 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int M;

    private static int[][] map;
    private static Queue<Node> queue;
    private static boolean[][] visited;

    private static int[] xDelta = { 1, -1 ,0, 0 };
    private static int[] yDelta = { 0, 0, 1, -1 };

    private static class Node {
        int x;
        int y;
        int time;

        Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        queue = new LinkedList<Node>();
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 치즈 녹이기
        int result = melt();

        // 출력
        System.out.println(result);
    }

    private static int melt() {
        int cycle = 0;

        while (true) {
            // map 업데이트
            updateMap();

            // map에서 녹일 치즈 선택하기
            int cheezeCount = 0;
            for (int x = 0 ; x < N ; x++) {
                for (int y = 0 ; y < M ; y++) {
                    // 치즈가 아니면 건너뛴다
                    if (map[x][y] != 1) continue;

                    // 치즈 개수 계산
                    cheezeCount++;

                    // 외부 공기 접촉면 확인
                    int cnt = 0;
                    for (int k = 0 ; k < 4 ; k++) {
                        int row = x + xDelta[k];
                        int col = y + yDelta[k];
                        if (map[row][col] == 2) {
                            cnt++;
                        }
                    }

                    // 2면 이상 외부 공기와 접촉하면 -1로 표시
                    if (cnt >= 2) {
                        map[x][y] = -1;
                    }
                }
            }

            // 더 이상 치즈가 없으면 중단
            if (cheezeCount == 0) break;

            // 사이클 1 증가
            cycle++;
        }
        return cycle;
    }

    private static void updateMap() {
        queue.add(new Node(0, 0, 0));
        for (int i = 0 ; i < N ; i++) {
            Arrays.fill(visited[i], false);
        }
        visited[0][0] = true;
        map[0][0] = 2;

        while (!queue.isEmpty()) {
            Node air = queue.poll();
            for (int i = 0 ; i < 4 ; i++) {
                int nextRow = air.x + xDelta[i];
                int nextCol = air.y + yDelta[i];
                if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M &&
                        map[nextRow][nextCol] != 1 && !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    map[nextRow][nextCol] = 2;
                    queue.add(new Node(nextRow, nextCol, air.time + 1));
                }
            }
        }
    }
}
