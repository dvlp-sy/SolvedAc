package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1816 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < N ; i++) {
            boolean isPrime = true;
            long S = Long.parseLong(br.readLine());
            for (int j = 2 ; j <= 1000000 ; j++) {
                if (S % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }


    }
}
