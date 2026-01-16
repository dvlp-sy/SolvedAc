package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 거짓말
 * @link <a href="https://www.acmicpc.net/problem/1043">BOJ 1043</a>
 */
public class Q1043 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;   // 사람의 수
    private static int M;   // 파티의 수

    // 전체 인원 중 진실을 아는 인원을 표시한 벡터
    private static boolean[] people;
    // 파티 입력값 행렬
    private static final List<List<Integer>> input = new ArrayList<>();
    // 파티 인원간 연결 그래프
    private static int[][] graph;
    // 그래프 방문 여부 저장 벡터
    private static boolean[] visited;
    // 거짓말 가능 여부
    private static boolean ableToLie;

    public static void main(String[] args) throws IOException {
        // 첫 번째 줄 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 두 번째 줄 입력 후 people 벡터 초기화
        people = new boolean[N+1];
        st = new StringTokenizer(br.readLine());
        int peopleCount = Integer.parseInt(st.nextToken());
        for (int i = 0; i < peopleCount; i++) {
            int trueMember = Integer.parseInt(st.nextToken());
            people[trueMember] = true;
        }

        // 파티 멤버 입력 후 graph 초기화
        graph = new int[N+1][N+1];
        for (int i = 0; i < M; i++) {
            st = new  StringTokenizer(br.readLine());
            peopleCount = Integer.parseInt(st.nextToken());

            List<Integer> list = new ArrayList<>();
            int priorMember = 0;    // 이전 입력 저장
            for (int j = 0; j < peopleCount; j++) {
                int partyMember =  Integer.parseInt(st.nextToken());
                list.add(partyMember);
                if (j >= 1) {
                    graph[priorMember][partyMember] = 1;
                    graph[partyMember][priorMember] = 1;
                }
                priorMember = partyMember;
            }
            input.add(list);
        }

        // 거짓말 할 수 있는 파티의 최댓값 계산
        System.out.println(calculateMax());
    }

    private static int calculateMax() {
        visited = new boolean[N+1];
        int max = 0;
        for (int i = 0; i < M; i++) {
            ableToLie = true;
            searchGraph(input.get(i).get(0));
            if (ableToLie) max++;
        }
        return max;
    }

    private static void searchGraph(int member) {
        // 연관된 파티에 진실을 아는 사람이 있다면 거짓말을 할 수 없다
        if (people[member]) {
            ableToLie = false;
            return ;
        }
        if (!visited[member]) {
            visited[member] = true;
            for (int i = 1; i < N+1; i++) {
                if (graph[member][i] == 1 && ableToLie) {
                    searchGraph(i);
                }
            }
            visited[member] = false;
        }
    }
}
