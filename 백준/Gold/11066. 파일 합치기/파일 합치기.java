import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int T, N;
    static StringBuilder sb = new StringBuilder();
    static int[][] dp;
    static int[] sum;

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            dp = new int[N + 1][N + 1];
            sum = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
            }

            for (int term = 1; term < N; term++) {
                for (int i = 1; i < N; i++) {
                    int end = i + term;
                    if (end > N)
                        break;

                    for (int j = i; j < end; j++) {
                        int value = dp[i][j] + dp[j + 1][end] + sum[end] - sum[i - 1];
                        dp[i][end] = dp[i][end] == 0 ? value : Math.min(dp[i][end], value);
                    }
                }
            }

            sb.append(dp[1][N]).append("\n");
        }
        System.out.println(sb);
    }
}