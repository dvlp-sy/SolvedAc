package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q16234 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int L;
    private static int R;

    private static Queue<Integer> queue;

    private static final int[] xDelta = { 1, -1, 0, 0 };
    private static final int[] yDelta = { 0, 0, 1, -1 };

    private static int[][] countries;
    private static boolean[] visited;

    private static class Result {
        List<Integer> union;
        int updatedPopulation;

        Result(int updatedPopulation, List<Integer> union) {
            this.union = union;
            this.updatedPopulation = updatedPopulation;
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력 및 초기화
        initialize();

        // 인구 이동 결과 출력
        System.out.println(movePopulation());
    }

    private static void initialize() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        queue = new LinkedList<>();
        visited = new boolean[N*N];

        countries = new int[N][N];
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                countries[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static int movePopulation() {
        int days = 0;

        while (true) {
            // area 배열 초기화
            Arrays.fill(visited, false);

            // 인구 이동
            boolean moved = false;
            for (int i = 0 ; i < N * N ; i++) {
                // 이미 방문한 나라는 건너뛴다
                if (visited[i]) continue;
                // 결과 계산
                Result newPopulation = move(i);
                // 인구 재분배
                if (newPopulation.union.size() > 1) {
                    for (int country : newPopulation.union) {
                        countries[country / N][country % N] = newPopulation.updatedPopulation;
                    }
                    moved = true;
                }
            }

            // 인구 이동이 없는 경우 종료
            if (!moved) {
                break;
            }

            // 인구 이동일 1 증가
            days++;
        }

        return days;
    }

    private static Result move(int index) {
        List<Integer> union = new ArrayList<>();
        queue.offer(index);
        visited[index] = true;

        union.add(index);
        int populationSum = countries[index / N][index % N];
        int countryCount = 1;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            int x = current / N;
            int y = current % N;

            for (int i = 0; i < 4; i++) {
                int nextX = x + xDelta[i];
                int nextY = y + yDelta[i];
                int nextIndex = nextX * N + nextY;

                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;

                if (!visited[nextIndex] && isMovable(x, y, nextX, nextY)) {
                    visited[nextIndex] = true;
                    queue.offer(nextIndex);
                    union.add(nextIndex);
                    countryCount++;
                    populationSum += countries[nextIndex / N][nextIndex % N];
                }
            }
        }

        return new Result(populationSum / countryCount, union);
    }

    private static boolean isMovable(int x, int y, int nextX, int nextY) {
        return Math.abs(countries[x][y] - countries[nextX][nextY]) >= L
                && Math.abs(countries[x][y] - countries[nextX][nextY]) <= R;
    }
}
