import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K;
    static long ans;
    static int[][] sum;
    static int[] friend;

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        //길이 -2로 저장
        friend = new int[N];
        //이름 길이 2~20까지 각각의 누적합;
        sum = new int[N][19];

        for (int i = 0; i < N; i++) {
            friend[i] = br.readLine().length() - 2;
        }

        for (int i = 0; i < 19; i++) {
            if (friend[0] == i)
                sum[0][i] = 1;

            for (int j = 1; j < N; j++) {
                sum[j][i] = sum[j - 1][i] + (friend[j] == i ? 1 : 0);
            }
        }

        for (int i = 0; i < N; i++) {
            int temp = friend[i];

            ans += sum[Math.min(N - 1, i + K)][temp] - sum[i][temp];
        }

        System.out.println(ans);
    }
}