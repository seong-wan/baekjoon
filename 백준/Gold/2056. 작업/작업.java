import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, ans;
    static int[] pre;
    static int[] time;
    static List<Integer>[] postList;
    static PriorityQueue<Integer> pq = new PriorityQueue<>((e1, e2) -> time[e1] - time[e2]);

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        pre = new int[N];
        time = new int[N];
        postList = new List[N];
        for (int i = 0; i < N; i++) {
            postList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());

            pre[i] = Integer.parseInt(st.nextToken());
            if (pre[i] == 0)
                pq.add(i);
            
            for (int j = 0; j < pre[i]; j++) {
                int preNum = Integer.parseInt(st.nextToken()) - 1;
                postList[preNum].add(i);
            }
        }

        while (!pq.isEmpty()) {
            int temp = pq.poll();
            ans += time[temp];

            for (Integer i : pq) {
                time[i] -= time[temp];
            }

            for (Integer i : postList[temp]) {
                pre[i]--;
                if (pre[i] == 0) {
                    pq.add(i);
                }
            }
        }

        System.out.println(ans);
    }
}