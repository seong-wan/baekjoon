import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        int N = in.nextInt();
        int M = in.nextInt();

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int A = in.nextInt();
            int B = in.nextInt();
            graph[B].add(A);
        }

        List<Integer> hackingList = new ArrayList<>();
        int maxCount = 0;

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            int count = dfs(i);

            if (count > maxCount) {
                hackingList.clear();
                hackingList.add(i);
                maxCount = count;
            } else if (count == maxCount) {
                hackingList.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int num : hackingList) {
            sb.append(num).append(" ");
        }

        System.out.println(sb);
    }

    private static int dfs(int node) {
        if (visited[node]) {
            return 0;
        }

        visited[node] = true;
        int count = 1;

        for (int neighbor : graph[node]) {
            count += dfs(neighbor);
        }

        return count;
    }

    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

        String nextString() throws Exception {
            StringBuilder sb = new StringBuilder();
            byte c;
            while ((c = read()) < 32) {
                if (size < 0) return "endLine";
            }
            do sb.appendCodePoint(c);
            while ((c = read()) > 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
            return sb.toString();
        }

        char nextChar() throws Exception {
            byte c;
            while ((c = read()) < 32) ; // SPACE 분리라면 <=로, SPACE 무시라면 <로
            return (char) c;
        }

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

        long nextLong() throws Exception {
            long n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) ;
            if (c == 45) {
                c = read();
                isMinus = true;
            }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        double nextDouble() throws Exception {
            double n = 0, div = 1;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) {
                if (size < 0) return -12345;
            }
            if (c == 45) {
                c = read();
                isMinus = true;
            } else if (c == 46) {
                c = read();
            }
            do n = (n * 10) + (c & 15);
            while (isNumber(c = read()));
            if (c == 46) {
                while (isNumber(c = read())) {
                    n += (c - 48) / (div *= 10);
                }
            }
            return isMinus ? -n : n;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
        }

        boolean isAlphabet(byte c) {
            return (64 < c && c < 91) || (96 < c && c < 123);
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