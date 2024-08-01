import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M;
    static int[] pre;
    static List<Integer>[] postList;
    static int[] term;
    static Deque<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pre = new int[N + 1];
        term = new int[N + 1];
        postList = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            postList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int preNum = Integer.parseInt(st.nextToken());
            int postNum = Integer.parseInt(st.nextToken());

            pre[postNum]++;
            postList[preNum].add(postNum);
        }

        for (int i = 1; i <= N; i++) {
            if (pre[i] == 0)
                queue.add(i);
        }

        int termNum = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int temp = queue.poll();
                term[temp] = termNum;

                for (Integer next : postList[temp]) {
                    pre[next]--;
                    if (pre[next] == 0)
                        queue.add(next);
                }
            }
            termNum++;
        }

        for (int i = 1; i <= N; i++) {
            sb.append(term[i]).append(" ");
        }
        System.out.println(sb);
    }
}