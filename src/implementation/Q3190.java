package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q3190 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int K;
    private static int L;

    private static int[][] map;
    private static Direction[] moving;
    private static Deque<Pair> snake;


    private static class Pair {
        int row;
        int col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static class Direction {
        int time;
        char dir;

        Direction(int time, char dir) {
            this.time = time;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        snake = new ArrayDeque<>();

        // 입력
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        K = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < K ; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            map[row - 1][col - 1] = 1;
        }

        L = Integer.parseInt(br.readLine());
        moving = new Direction[L];
        for (int i = 0 ; i < L ; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            moving[i] = new Direction(time, dir);
        }

        // 뱀 이동
        int time = 0;
        int currentDir = 0; // 동남서북 순서로 0, 1, 2, 3
        snake.add(new Pair(0, 0));

        while (true) {
            // 시간 1 증가
            time++;

            // 머리 늘리기
            Pair head = null;
            switch (currentDir) {
                case 0: // 동
                    head = new Pair(snake.getLast().row, snake.getLast().col + 1);
                    break;
                case 1: // 남
                    head = new Pair(snake.getLast().row + 1, snake.getLast().col);
                    break;
                case 2: // 서
                    head = new Pair(snake.getLast().row, snake.getLast().col - 1);
                    break;
                case 3: // 북
                    head = new Pair(snake.getLast().row - 1, snake.getLast().col);
                    break;
            }

            // 벽에 부딪히는 경우 종료
            if (head == null || head.row < 0 || head.row >= N || head.col < 0 || head.col >= N) {
                break;
            }

            // 몸에 부딪히는 경우 종료
            boolean isCollided = false;
            for (Pair pair : snake) {
                if (pair.row == head.row && pair.col == head.col) {
                    isCollided = true;
                    break;
                }
            }
            if (isCollided) {
                break;
            }

            if (map[head.row][head.col] == 1) {
                // 사과가 있는 경우 사과 먹고 꼬리 유지
                snake.add(head);
                map[head.row][head.col] = 0;
            } else {
                // 사과가 없는 경우 머리 추가하고 꼬리 제거
                snake.add(head);
                snake.removeFirst();
            }

            // 방향 전환이 필요한 경우 방향 전환
            for (Direction direction : moving) {
                if (direction.time == time) {
                    if (direction.dir == 'D') {
                        currentDir = (currentDir + 1) % 4;
                    } else {
                        currentDir = (currentDir + 3) % 4;
                    }
                }
            }
        }

        System.out.println(time);
    }
}
