package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q9663 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static int N;
    public static int cnt;

    // r번째 노드에 대해 nQueen 수행
    public static void nQueen(int[] cols, int r) {
        // r이 유망하다면 cols 업데이트
        if (promising(r, cols)) {
            // Solution을 발견한 경우 cnt 증가
            if (r == N) {
                cnt++;
            }
            // 아직 Solution을 찾지 못한 경우 계속해서 nQueen 진행
            else {
                for (int i=1; i<N+1; i++) {
                    cols[r+1] = i;
                    nQueen(cols, r+1);
                }
            }
        }

    }

    // i번째 노드에 대한 유망성 검사
    public static boolean promising(int i, int[] cols) {
        // i보다 먼저 선택된 노드 k에 대해
        // 행이 일치하거나, 대각선에 위치할 수 없음
        for (int k=1; k<i; k++) {
            if (cols[i] == cols[k] || Math.abs(cols[i] - cols[k]) == i-k) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        int[] cols = new int[N+1];
        nQueen(cols, 0);
        System.out.println(cnt);
    }
}
