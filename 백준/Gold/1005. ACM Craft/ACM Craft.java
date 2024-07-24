import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, N, K, W;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[] time;
    static int[] preCount;
    static List<Integer>[] postList;
    static PriorityQueue<Integer> queue = new PriorityQueue<>((e1, e2) -> time[e1] - time[e2]);

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            int ans = 0;

            time = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            preCount = new int[N + 1];
            postList = new List[N + 1];

            for (int i = 1; i <= N; i++) {
                postList[i] = new ArrayList<>();
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int pre = Integer.parseInt(st.nextToken());
                int post = Integer.parseInt(st.nextToken());

                preCount[post]++;
                postList[pre].add(post);
            }
            W = Integer.parseInt(br.readLine());

            for (int i = 1; i <= N; i++) {
                if (preCount[i] == 0) {
                    if (i == W) {
                        ans += time[i];
                        queue.clear();
                        break;
                    }
                    queue.add(i);
                }
            }

            while (!queue.isEmpty()) {
                int temp = queue.poll();
                ans += time[temp];

                for (Integer i : queue) {
                    time[i] -= time[temp];
                }

                for (Integer i : postList[temp]) {
                    preCount[i]--;
                    if (preCount[i] == 0) {
                        if (i == W) {
                            ans += time[i];
                            queue.clear();
                            break;
                        }
                        queue.add(i);
                    }
                }
            }

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}