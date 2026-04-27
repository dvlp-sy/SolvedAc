package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1931 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static Meeting[] meetings;

    private static class Meeting implements Comparable<Meeting> {
        long start;
        long end;

        Meeting(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            if (this.end != o.end) {
                return Long.compare(this.end, o.end);
            }
            return Long.compare(this.start, o.start);
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력
        input();

        // meetings 배열을 종료 시간 기준으로 오름차순 정렬
        Arrays.sort(meetings);

        // greedy 하게 회의 선택
        int count = selectMeetings();

        // 결과 출력
        System.out.println(count);
    }

    private static int selectMeetings() {
        int count = 0;
        long endTime = 0;
        for (Meeting meeting : meetings) {
            // 미팅의 시작 시간이 이전 미팅의 종료 시간 이후인 경우 해당 미팅 선택
            if (meeting.start >= endTime) {
                count++;
                endTime = meeting.end;
            }
        }
        return count;
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        meetings = new Meeting[N];
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            long start = Long.parseLong(st.nextToken());
            long end = Long.parseLong(st.nextToken());
            meetings[i] = new Meeting(start, end);
        }
    }
}
