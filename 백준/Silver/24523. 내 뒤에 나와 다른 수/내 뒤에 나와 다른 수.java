import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] ans;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        ans = new int[N + 1];
        Arrays.fill(ans, -1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int first = 1;
        int last = 1;
        int before = Integer.parseInt(st.nextToken());

        for (int i = 2; i <= N; i++) {
            int after = Integer.parseInt(st.nextToken());
            //앞의 수와 다른 수가 나온 경우
            if (before != after) {
                for (int j = first; j <= last; j++) {
                    ans[j] = i;
                }
                before = after;
                first = i;
                last = i;
            }
            //같은 수가 나온 경우
            else {
                last = i;
            }
        }
        for (int i = 1; i <= N; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb);
    }
}