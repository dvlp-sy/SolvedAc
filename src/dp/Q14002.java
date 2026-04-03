package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q14002 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int[] sequence;
    private static Number[] dp;

    private static class Number {
        int count;
        int parent;

        Number(int count, int parent) {
            this.count = count;
            this.parent = parent;
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력
        input();

        // dp 계산
        int maxIdx = calculate();

        // 결과 출력
        printResult(maxIdx);
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        dp = new Number[N];
        Arrays.fill(dp, new Number(1, -1));
        sequence = new int[N];
        for (int i = 0 ; i < N ; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static int calculate() {
        int maxIdx = -1;
        int maxCount = -1;

        for (int i = 0 ; i < N ; i++) {
            int value = sequence[i];
            for (int j = 0 ; j < i ; j++) {
                // 값이 증가하고, dp[j]의 count가 dp[i]의 count 보다 크면 dp[i] 갱신
                if (value > sequence[j]) {
                    if (dp[i].count < dp[j].count + 1) {
                        dp[i] = new Number(dp[j].count + 1, j);
                    }

                    // dp[i] 갱신 후 max 갱신
                    if (dp[i].count > maxCount) {
                        maxCount = dp[i].count;
                        maxIdx = i;
                    }
                }
            }
        }
        return maxIdx;
    }

    private static void printResult(int maxIdx) {
        if (maxIdx == -1) {
            System.out.println(1);
            System.out.println(sequence[0]);
            return ;
        }

        System.out.println(dp[maxIdx].count);
        Stack<Integer> stack = new Stack<>();

        int idx = maxIdx;
        while (idx != -1) {
            stack.push(idx);
            idx = dp[idx].parent;
        }

        while (!stack.isEmpty()) {
            System.out.print(sequence[stack.pop()] + " ");
        }
        System.out.println();
    }


}
