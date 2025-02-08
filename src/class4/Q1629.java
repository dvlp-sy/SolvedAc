package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1629 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    // A^B = A^(B/2) * A^(B/2) (B가 짝수)
    // A^B = A^(B/2) * A^(B/2) * A (B가 홀수)
    // (x*y)%C = (x%C * y%C) % C
    // x = y = A^(B/2)
    private static long divide(long A, long B, long C) {
        if (B == 0) {
            return 1;
        }
        long x = divide(A, B/2, C);
        long result = (x * x) % C;
        if (B % 2 == 0) {
            // 짝수
            return result;
        } else {
            // 홀수
            return (result * A) % C;
        }
    }

    public static void main(String[] args) throws IOException {

        String line = br.readLine();
        st = new StringTokenizer(line);

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        long result = divide(A, B, C);
        System.out.println(result);
    }
}
