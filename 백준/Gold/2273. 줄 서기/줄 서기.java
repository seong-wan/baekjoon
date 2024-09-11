import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[][] mat;
    static boolean chk;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        mat = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            mat[from][to] = 1;
        }

        floyd();
        check();
        System.out.println(chk ? -1 : sb);
    }

    static void check() {
        for (int i = 1; i <= N; i++) {
            int start = 1;
            int end = N;

            for (int j = 1; j <= N; j++) {
                //비교가 이상한 상황 -> 양 쪽 다 크다고 하는 경우
                if (mat[i][j] == 1 && mat[j][i] == 1) {
                    chk = true;
                    return;
                }

                if (mat[i][j] == 1)
                    end--;

                if (mat[j][i] == 1)
                    start++;
            }

            sb.append(start).append(" ").append(end).append("\n");
        }
    }

    static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (mat[i][k] == 1 && mat[k][j] == 1)
                        mat[i][j] = 1;
                }
            }
        }

    }
}