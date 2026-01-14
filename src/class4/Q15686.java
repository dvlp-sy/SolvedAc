package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 치킨 배달
 * @link <a href="https://www.acmicpc.net/problem/15686">BOJ 15686</a>
 */
class Pair {
    public int x;
    public int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Q15686 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 입력값
    private static int N;
    private static int M;

    // 치킨집 개수, 집 개수
    private static int chickenCnt = 0;
    private static int houseCnt = 0;

    // 치킨집 좌표 벡터, 집 좌표 벡터
    private static List<Pair> chickenPair = new ArrayList<>();
    private static List<Pair> housePair = new ArrayList<>();

    // DFS로 방문한 치킨집 벡터
    private static boolean[] visited;

    // 치킨 거리
    private static int minDist =  Integer.MAX_VALUE;

    private static void selectChicken(int chickenIdx, int depth) {
        if (depth == M) {
            calculate();
        }
        // DFS로 치킨집 선택
        for (int i = chickenIdx; i < chickenCnt; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selectChicken(i, depth + 1);
                visited[i] = false;
            }
        }
    }

    private static void calculate() {
        // distance 배열 초기화
        int[] distance = new int[houseCnt];
        for (int i = 0; i < houseCnt; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        // 선택된 치킨집의 최소 치킨 거리 계산
        for (int i=0; i<chickenCnt; i++) {
            if (visited[i]) {
                for (int j = 0; j < houseCnt; j++) {
                    Pair chicken = chickenPair.get(i);
                    Pair house = housePair.get(j);
                    distance[j] = Math.min(Math.abs(chicken.x - house.x) + Math.abs(chicken.y - house.y), distance[j]);
                }
            }
        }
        // distance 배열 합 계산
        int localMinDist = 0;
        for (int i = 0; i < houseCnt; i++) {
            localMinDist += distance[i];
        }
        minDist = Math.min(localMinDist, minDist);
    }

    public static void main(String[] args) throws IOException {

        // N, M 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // N*N 도시 정보 행렬
        int[][] city = new int[N][N];

        // N*N 도시 정보 입력
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                // 입력값이 집인 경우
                if (city[i][j] == 1) {
                    houseCnt++;
                    housePair.add(new Pair(i, j));
                }
                // 입력값이 치킨집인 경우
                else if (city[i][j] == 2) {
                    chickenCnt++;
                    chickenPair.add(new Pair(i, j));
                }
            }
        }

        // 치킨집 선택
        visited = new boolean[chickenCnt];
        selectChicken(0, 0);

        System.out.println(minDist);
    }
}
