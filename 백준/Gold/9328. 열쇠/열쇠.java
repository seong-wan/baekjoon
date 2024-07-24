import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int T, h, w, ans;
    static char[][] map;
    static List<int[]> start;
    static boolean[] keys;
    static Deque<int[]> queue = new ArrayDeque<>();
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();

        T = in.nextInt();
        for (int t = 0; t < T; t++) {
            start = new ArrayList<>();
            keys = new boolean[26];
            ans = 0;

            h = in.nextInt();
            w = in.nextInt();

            map = new char[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    map[i][j] = in.nextChar();

                    //가장자리에 있는 경우
                    if (i == 0 || i == h - 1 || j == 0 || j == w - 1) {
                        if (map[i][j] == '*')
                            continue;

                        if (map[i][j] == '$') {
                            ans++;
                            map[i][j] = '.';
                        }

                        if (isKey(i, j)) {
                            keys[map[i][j] - 'a'] = true;
                            map[i][j] = '.';
                        }

                        start.add(new int[]{i, j});
                    }
                }
            }

            String key = in.nextString();
            if (!key.equals("0")) {
                int keySize = key.length();
                for (int i = 0; i < keySize; i++) {
                    keys[key.charAt(i) - 'a'] = true;
                }
            }

            bfs();

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static void bfs() {
        boolean[][] visit = new boolean[h][w];
        boolean check = false;
        for (int[] start : start) {
            int cr = start[0];
            int cc = start[1];

            if (isDoor(cr, cc)) {
                if (keys[map[cr][cc] - 'A']) {
                    map[cr][cc] = '.';
                } else continue;
            }

            visit[start[0]][start[1]] = true;
            queue.add(start);
        }

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int cr = temp[0];
            int cc = temp[1];

            for (int dir = 0; dir < 4; dir++) {
                int nr = cr + dr[dir];
                int nc = cc + dc[dir];

                if (!canGo(nr, nc) || visit[nr][nc] || map[nr][nc] == '*')
                    continue;

                if (map[nr][nc] == '$') {
                    ans++;
                    map[nr][nc] = '.';

                }

                if (isKey(nr, nc)) {
                    int keyNum = map[nr][nc] - 'a';
                    if (keys[keyNum]) {
                        map[nr][nc] = '.';
                    } else {
                        keys[map[nr][nc] - 'a'] = true;
                        check = true;
                    }
                }

                if (isDoor(nr, nc)) {
                    int doorNum = map[nr][nc] - 'A';

                    if (keys[doorNum]) {
                        map[nr][nc] = '.';
                    } else continue;
                }

                visit[nr][nc] = true;
                queue.add(new int[]{nr, nc});
            }
        }

        if (check)
            bfs();
    }

    static boolean isKey(int r, int c) {
        return map[r][c] - 'a' >= 0 && map[r][c] - 'a' <= 25;
    }

    static boolean isDoor(int r, int c) {
        return map[r][c] - 'A' >= 0 && map[r][c] - 'A' <= 25;
    }

    static boolean canGo(int r, int c) {
        return r >= 0 && r <= h - 1 && c >= 0 && c <= w - 1;
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