package class2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q4153 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int x, y, z;

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());

            if (x==0 && y==0 && z==0) {
                break;
            }

            if (x*x + y*y == z*z || x*x + z*z == y*y || y*y + z*z == x*x) {
                System.out.println("right");
            }
            else {
                System.out.println("wrong");
            }
        }
    }
}
