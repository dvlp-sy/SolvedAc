package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q11653 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        while (N != 1) {
            for (int i = 2; i <= N ; i++) {
                if (N % i == 0) {
                    System.out.println(i);
                    N /= i;
                    break;
                }
            }
        }
    }
}
