package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1149 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        int[][] houses = new int[N][3];
        int[][] minSum = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            houses[i][0] = Integer.parseInt(st.nextToken());
            houses[i][1] = Integer.parseInt(st.nextToken());
            houses[i][2] = Integer.parseInt(st.nextToken());
        }

        minSum[0][0] = houses[0][0];
        minSum[0][1] = houses[0][1];
        minSum[0][2] = houses[0][2];

        for (int i=1; i<N; i++) {
            for (int j=0; j<3; j++) {
                int c1 = (j + 1) % 3;
                int c2 = (j + 2) % 3;
                if (minSum[i - 1][c1] < minSum[i - 1][c2]) {
                    minSum[i][j] = minSum[i - 1][c1] + houses[i][j];
                } else {
                    minSum[i][j] = minSum[i - 1][c2] + houses[i][j];
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i=0; i <3; i++) {
            if (minSum[N-1][i] < min) {
                min = minSum[N-1][i];
            }
        }
        System.out.println(min);
    }
}
