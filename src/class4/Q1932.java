package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1932 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static int[][] triangle = new int[501][501];

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());

        for (int i=1; i<=n; i++) {
            int num;
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<i+1; j++) {
                num = Integer.parseInt(st.nextToken());

                if (triangle[i-1][j] > triangle[i-1][j - 1]) {
                    triangle[i][j] = triangle[i-1][j] + num;
                } else {
                    triangle[i][j] = triangle[i-1][j - 1] + num;
                }
            }
        }

        int max = 0;
        for (int i=1; i<=n; i++) {
            if (triangle[n][i] > max) {
                max = triangle[n][i];
            }
        }
        System.out.println(max);
    }
}
