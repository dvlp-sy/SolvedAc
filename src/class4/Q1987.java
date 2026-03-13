package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1987 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int R;
    private static int C;

    private static char[][] input;
    private static boolean[] visitedAlpha;

    private static int[] rowMove = { 1, -1, 0, 0 };
    private static int[] colMove = { 0, 0, 1, -1 };

    private static int maxDepth = 1;

    private static void dfs(int r, int c, int depth) {
        char currentChar = input[r][c];
        int currentCharIdx = getIndex(currentChar);

        // currentChar가 이미 나온 알파벳인 경우 maxDepth 업데이트 후 리턴
        if (visitedAlpha[currentCharIdx]) {
            if (depth > maxDepth) {
                maxDepth = depth;
            }
            return ;
        }

        // dfs
        visitedAlpha[currentCharIdx] = true;
        for (int i = 0; i < 4; i++) {
            int row = r + rowMove[i];
            int col = c + colMove[i];
            if (row >= 0 && row < R && col >= 0 && col < C) {
                dfs(row, col, depth + 1);
            }
        }
        visitedAlpha[currentCharIdx] = false;
    }

    private static int getIndex(char ch) {
        return ch - 'A';
    }

    public static void main(String[] args) throws IOException {
        // 입력
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        input = new char[R][C];
        visitedAlpha = new boolean[26];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                input[i][j] = str.charAt(j);
            }
        }

        // dfs
        dfs(0, 0, 0);
        System.out.println(maxDepth);
    }
}
