package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q16953 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static Map<Long, Long> map = new HashMap<>();

    private static void BFS(long A, long B) {
        Queue<Long> q = new LinkedList<>();
        q.add(A);
        map.put(A, 1L);

        while (!q.isEmpty()) {
            long n = q.peek();
            q.remove();

            // n이 B와 일치하면 종료
            if (n == B) {
                break;
            }

            // 인접 노드 추가
            long n1 = 10*n+1;
            if (!map.containsKey(n1) && n1 <= B) {
                q.add(n1);
                map.put(n1, map.get(n)+1);
            }

            // 인접 노드 추가
            long n2 = 2*n;
            if (!map.containsKey(n2) && n2 <= B) {
                q.add(n2);
                map.put(n2, map.get(n) + 1);
            }

        }
    }

    public static void main(String[] args) throws IOException {

        // Input
        long A = 0;
        long B = 0;

        st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        // BFS
        BFS(A, B);

        if (map.containsKey(B)) {
            System.out.println(map.get(B));
        } else {
            System.out.println(-1);
        }

    }
}
