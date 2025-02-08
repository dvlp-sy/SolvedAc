package class2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q30802 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());

        int[] ary = new int[6];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<6; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int rst1 = 0;
        for (int i=0; i<6; i++) {
            rst1 += ary[i]/T;
            if (ary[i]%T > 0) {
                rst1 += 1;
            }
        }
        int rst2 = N/P;
        int rst3 = N%P;

        System.out.println(rst1);
        System.out.print(rst2 + " " + rst3);

    }
}
