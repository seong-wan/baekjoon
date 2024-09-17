import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, n, m;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static List<Integer>[] adlist;
    static int[] chk;//0-미방문 1-A색 2-B색
    static boolean possible;

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            possible = true;

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            adlist = new List[n + 1];
            chk = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                adlist[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                adlist[from].add(to);
                adlist[to].add(from);
            }

            for (int i = 1; i <= n; i++) {
                if (chk[i] == 0) {
                    chk[i] = 1;
                    dfs(i);

                    if (!possible)
                        break;
                }
            }
            sb.append(possible ? "possible" : "impossible").append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int node) {
        if (!possible)
            return;

        for (Integer next : adlist[node]) {
            if (chk[next] == 0) {
                chk[next] = chk[node] == 1 ? 2 : 1;
                dfs(next);
            } else {
                if (chk[node] == chk[next])
                    possible = false;
            }
        }
    }
}