package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q10815 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int A;
    private static int B;

    private static int[] seq;
    private static int[] candidates;

    public static void main(String[] args) throws IOException {
        // 입력 및 초기화
        init();

        // 이진 탐색 전 수열 정렬하기
        Arrays.sort(seq);

        // 이진 탐색 수행 후 출력
        for (int i = 0 ; i < B ; i++) {
            if (binarySearch(candidates[i])) {
                System.out.print(1 + " ");
            } else {
                System.out.print(0 + " ");
            }
        }
        System.out.println();

    }

    private static void init() throws IOException {
        A = Integer.parseInt(br.readLine());
        seq = new int[A];
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < A ; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        B = Integer.parseInt(br.readLine());
        candidates = new int[B];
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < B ; i++) {
            candidates[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static boolean binarySearch(int target) {
        int left = 0;
        int right = A - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (seq[mid] == target) {
                return true;
            }
            if (target < seq[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}
