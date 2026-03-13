package class4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9935 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // 입력
        String inputString = br.readLine();
        String bombString = br.readLine();

        StringBuilder sb = new StringBuilder();

        int strLength = inputString.length();
        int bombStrLength = bombString.length();

        for (int i = 0; i < strLength; i++) {
            char currentChar = inputString.charAt(i);
            sb.append(currentChar);

            // String Builder의 길이가 폭발 문자열보다 짧으면 다음으로 넘어감
            if (sb.length() < bombStrLength) continue;

            // 뒤에서부터 비교
            boolean isBombed = true;
            for (int j = 0; j < bombStrLength; j++) {
                if (sb.charAt(sb.length() - bombStrLength + j) != bombString.charAt(j)) {
                    isBombed = false;
                    break;
                }
            }

            // 폭발 문자열을 보유한 경우 String Builder에서 제거
            if (isBombed) {
                sb.delete(sb.length() - bombStrLength, sb.length());
            }
        }

        String result = sb.toString();
        System.out.println(result.isEmpty() ? "FRULA" : result);
    }
}
