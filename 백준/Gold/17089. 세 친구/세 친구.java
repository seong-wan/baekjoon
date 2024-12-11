import java.util.HashSet;
import java.util.Set;

public class Main {
    static int N, M, ans = Integer.MAX_VALUE / 2;
    static Set<Integer>[] adlist;

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        N = in.nextInt();
        M = in.nextInt();

        adlist = new Set[N + 1];
        for (int i = 1; i <= N; i++) {
            adlist[i] = new HashSet<>();
        }

        for (int i = 0; i < M; i++) {
            int from = in.nextInt();
            int to = in.nextInt();

            adlist[from].add(to);
            adlist[to].add(from);
        }

        for (int i = 1; i < N; i++) {
            if (adlist[i].size() < 2)
                continue;

            check(i);
        }

        System.out.println(ans == Integer.MAX_VALUE / 2 ? -1 : ans);
    }

    static void check(int first) {
        for (Integer second : adlist[first]) {
            if (adlist[second].size() < 2)
                continue;

            for (Integer third : adlist[second]) {
                if (adlist[third].contains(first)) {
                    int temp = adlist[first].size() + adlist[second].size() + adlist[third].size() - 6;
                    ans = Math.min(ans, temp);
                }
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