package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2096 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        // 원본 배열
        int[][] origin = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            origin[i][0] = Integer.parseInt(st.nextToken());
            origin[i][1] = Integer.parseInt(st.nextToken());
            origin[i][2] = Integer.parseInt(st.nextToken());
        }

        // dp 배열
        int[][] dpMin = {{0, 0, 0}, {0, 0, 0}};
        int[][] dpMax = {{0, 0, 0}, {0, 0, 0}};

        for (int i=0; i<N; i++) {
            // dpMin 계산
            dpMin[1][0] = Math.min(dpMin[0][0], dpMin[0][1]) + origin[i][0];
            dpMin[1][1] = Math.min(Math.min(dpMin[0][0], dpMin[0][1]), dpMin[0][2]) + origin[i][1];
            dpMin[1][2] = Math.min(dpMin[0][1], dpMin[0][2]) + origin[i][2];

            // dpMax 계산
            dpMax[1][0] = Math.max(dpMax[0][0], dpMax[0][1]) + origin[i][0];
            dpMax[1][1] = Math.max(Math.max(dpMax[0][0], dpMax[0][1]), dpMax[0][2]) + origin[i][1];
            dpMax[1][2] = Math.max(dpMax[0][1], dpMax[0][2]) + origin[i][2];

            // 갱신
            dpMin[0][0] = dpMin[1][0];
            dpMin[0][1] = dpMin[1][1];
            dpMin[0][2] = dpMin[1][2];
            dpMax[0][0] = dpMax[1][0];
            dpMax[0][1] = dpMax[1][1];
            dpMax[0][2] = dpMax[1][2];
        }

        // max 계산
        int max = Math.max(dpMax[0][0], Math.max(dpMax[0][1], dpMax[0][2]));

        // min 계산
        int min = Math.min(dpMin[0][0], Math.min(dpMin[0][1], dpMin[0][2]));

        System.out.println(max + " " + min);

    }
}
