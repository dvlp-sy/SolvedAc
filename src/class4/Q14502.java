package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q14502 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int M;

    private static int[][] lab;
    private static final int[] rowDelta = { 1, -1, 0, 0 };
    private static final int[] colDelta = { 0, 0, 1, - 1 };

    private static Queue<Pair> queue;

    private static int maxSpace = 0;
    private static int[][] virusMap;
    private static List<Pair> virusPoints;

    private static class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static void backtrack(int cnt, int index) {
        if (cnt == 3) {
            infect();
            return ;
        }

        for (int i = index ; i < N * M ; i++) {
            int row = i / M;
            int col = i % M;

            // 벽이 없는 곳에 벽 설치 후 cnt 증가
            if (lab[row][col] == 0) {
                lab[row][col] = 1;
                backtrack(cnt + 1, i + 1);
                lab[row][col] = 0;
            }
        }
    }

    private static void infect() {
        // 바이러스 맵 초기화
        for (int i = 0 ; i < N ; i++) {
            Arrays.fill(virusMap[i], 0);
        }
        // bfs로 바이러스 맵 생성
        queue.addAll(virusPoints);
        bfs(queue);
        // 최대 안전 영역 계산
        int localMaxSpace = 0;
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (lab[i][j] == 0 && virusMap[i][j] == 0) {
                    localMaxSpace++;
                }
            }
        }
        maxSpace = Math.max(maxSpace, localMaxSpace);
    }

    private static void bfs(Queue<Pair> queue) {
        while (!queue.isEmpty()) {
            // 인접 노드 감염
            Pair pair = queue.poll();
            virusMap[pair.row][pair.col] = 2;
            for (int i = 0 ; i < 4 ; i++) {
                int nextRow = pair.row + rowDelta[i];
                int nextCol = pair.col + colDelta[i];
                if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M
                        && lab[nextRow][nextCol] == 0 && virusMap[nextRow][nextCol] == 0) {
                    queue.add(new Pair(nextRow, nextCol));
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        // 입력
        st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lab = new int[N][M];
        virusMap = new int[N][M];
        virusPoints = new ArrayList<>();

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < M ; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if (lab[i][j] == 2) {
                    virusPoints.add(new Pair(i, j));
                }
            }
        }

        queue = new LinkedList<>();
        backtrack(0, 0);
        System.out.println(maxSpace);
    }
}
