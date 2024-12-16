import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    static int N, M, start = 1;
    static List<int[]>[] adlist;
    static boolean[] check;
    static boolean[] days;

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        N = in.nextInt();
        M = in.nextInt();

        check = new boolean[N + 1];
        adlist = new List[N + 1];
        days = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            adlist[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            int openDay = in.nextInt();

            if (openDay == 1)
                start = from;

            adlist[from].add(new int[]{to, openDay});
            adlist[to].add(new int[]{from, openDay});
        }

        prim();
        for (int i = 1; i <= N; i++) {
            if (!days[i]) {
                System.out.println(i);
                break;
            }
        }
    }

    static void prim() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
        check[start] = true;
        pq.addAll(adlist[start]);
        int count = 1;

        while (!pq.isEmpty() && count < N) {
            int[] cur = pq.poll();
            int to = cur[0];
            int openDay = cur[1];

            if (check[to])
                continue;

            check[to] = true;
            count++;
            if (openDay <= N)
                days[openDay] = true;

            for (int[] next : adlist[to]) {
                if (check[next[0]])
                    continue;
                pq.add(next);
            }
        }
    }

    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) {
                if (size < 0) return -1;
            }
            if (c == 45) {
                c = read();
                isMinus = true;
            }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
        }

        byte read() throws Exception {
            if (index == size) {
                size = System.in.read(buffer, index = 0, SIZE);
                if (size < 0) buffer[0] = -1;
            }
            return buffer[index++];
        }
    }
}