import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Reader in = new Reader();

        int N = in.nextInt();
        int K = in.nextInt();

        int a1 = in.nextInt();
        int a2 = in.nextInt();
        int b1 = in.nextInt();
        int b2 = in.nextInt();

        int[] nextPos = new int[N + 1];
        for (int i = 1; i <= N; i++) nextPos[i] = i;

        reverse(nextPos, a1, a2);
        reverse(nextPos, b1, b2);

        int[] to = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            to[nextPos[i]] = i;
        }

        int[] result = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;

            List<Integer> cycle = new ArrayList<>();
            int curr = i;
            while (!visited[curr]) {
                visited[curr] = true;
                cycle.add(curr);
                curr = to[curr]; // i번 위치가 다음으로 이동할 위치를 따라감
            }

            int cycleSize = cycle.size();
            int shift = (int) (K % cycleSize);

            for (int j = 0; j < cycleSize; j++) {
     
                result[cycle.get((j + shift) % cycleSize)] = cycle.get(j);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append("\n");
        }
        System.out.print(sb);
    }

    static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            while ((c = read()) <= 32);
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return n;
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