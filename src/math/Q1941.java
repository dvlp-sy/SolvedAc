package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q1941 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int totalCnt = 0;

    private static final int[] xDelta = { 1, -1, 0, 0 };
    private static final int[] yDelta = { 0, 0, 1, -1 };

    private static final int[][] classroom = new int[5][5];
    private static final boolean[] selected = new boolean[25];

    public static void main(String[] args) throws IOException {
        // 입력
        for (int i = 0 ; i < 5 ; i++) {
            String line = br.readLine();
            char[] chars = line.toCharArray();
            for (int j = 0 ; j < 5 ; j++) {
                if (chars[j] == 'S') {
                    classroom[i][j] = 1; // 이다솜파
                } else {
                    classroom[i][j] = 2; // 임도연파
                }
            }
        }

        // 모든 경우의 수 계산
        combination(0, 0, 0);

        System.out.println(totalCnt);
    }

    private static void combination(int start, int count, int total) {
        if (total == 7) {
            if (count >= 4 && isConnected()) {
                totalCnt++;
            }
            return ;
        }

        for (int i = start ; i < 25 ; i++) {
            selected[i] = true;
            combination(i + 1, count + (classroom[i / 5][i % 5] == 1 ? 1 : 0), total + 1);
            selected[i] = false;
        }
    }

    private static boolean isConnected() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[25];
        int count = 0;

        // 시작 학생 선정
        for (int stu = 0 ; stu < 25 ; stu++) {
            if (selected[stu]) {
                queue.offer(stu);
                visited[stu] = true;
                count++;
                break;
            }
        }

        // BFS로 연결 여부 확인
        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            for (int i = 0 ; i < 4 ; i++) {
                int row = (current / 5) + xDelta[i];
                int col = (current % 5) + yDelta[i];
                int next = row * 5 + col;

                if (row < 0 || row >= 5 || col < 0 || col >= 5) continue;

                if (!visited[next] && selected[next]) {
                    visited[next] = true;
                    queue.offer(next);
                    count++;
                }
            }
        }

        return count == 7;
    }
}
