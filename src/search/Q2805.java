package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2805 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int goal;

    private static int maxHeight = 0;
    private static int minHeight = 0;
    private static int[] trees;

    public static void main(String[] args) throws IOException {
        // 입력 및 초기화
        init();

        // 이진 탐색으로 최적의 높이 찾기
        System.out.println(getWood());
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        goal = Integer.parseInt(st.nextToken());

        trees = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N ; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            maxHeight = Math.max(maxHeight, trees[i]);
            minHeight = Math.min(minHeight, trees[i]);
        }
    }

    private static int getWood() {
        while (minHeight <= maxHeight) {
            int mid = (minHeight + maxHeight) / 2;

            // mid 높이에서 자를 때 얻을 수 있는 나무의 양 계산
            int wood = getWoodHeight(mid);

            if (wood == goal) {
                return mid;
            }
            if (wood < goal) {
                maxHeight = mid - 1;
            } else {
                minHeight = mid + 1;
            }
        }
        return -1;
    }

    private static int getWoodHeight(int mid) {
        int height = 0;
        for (int tree : trees) {
            if (tree > mid) {
                height += tree - mid;
            }
        }
        return height;
    }
}
