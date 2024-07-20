import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] input;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        input = new int[N];
        dp = new int[N][N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
            dp[i][i] = 1;
        }

        for (int term = 1; term < N; term++) {
            for (int i = 0; i < N - term; i++) {
                if (term == 1) {
                    if (input[i] == input[i + term])
                        dp[i][i + term] = 1;

                    continue;
                }

                if (input[i] == input[i + term] && dp[i + 1][i + term - 1] == 1) {
                    dp[i][i + term] = 1;
                }
            }
        }

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            sb.append(dp[start - 1][end - 1]).append("\n");
        }

        System.out.println(sb);
    }
}