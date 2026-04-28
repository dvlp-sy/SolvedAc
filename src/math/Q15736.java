package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q15736 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static long N;

    public static void main(String[] args) throws IOException {
        N = Long.parseLong(br.readLine());
        System.out.println((int) Math.sqrt((double) N));
    }
}
