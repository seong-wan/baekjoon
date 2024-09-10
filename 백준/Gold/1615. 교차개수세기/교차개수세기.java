import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//세그먼트 트리를 이용하여 1열을 정렬 후 돌면서
//연결되는 각 정점들의 인덱스 이후의 구간 합을 구하고 해당 인덱스에 1을 update 하는 식으로 진행
//1열은 오름차순으로 정렬되어 있으므로 연결된 정점의 인덱스 다음으로 있는 간선이 있다면 그 개수만큼 교차개수 형성
//1열의 인덱스가 같을 때는 2열의 인덱스를 오름차순으로 정렬하여 같은 정점에서 나온 간선으로 인해 교차점이 생기지 않게 처리
//1열의 첫번째 인덱스는 교차할 간선이 없으므로 update만 진행
public class Main {
    static class Node implements Comparable<Node> {
        int a;
        int b;

        @Override
        public int compareTo(Node o) {
            return this.a == o.a ? this.b - o.b : this.a - o.a;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] tree;
    static Node[] edge;

    public static void main(String[] args) throws Exception {
        Reader in=new Reader();
        int N = in.nextInt();
        int M = in.nextInt();
        long ans = 0;
        tree = new int[4 * N];

        //간선 입력
        edge = new Node[M];

        for (int i = 0; i < M; i++) {
            int a = in.nextInt();
            int b = in.nextInt();

            edge[i] = new Node();
            edge[i].a = a;
            edge[i].b = b;
        }

        Arrays.sort(edge);

        for (int i = 0; i < M; i++) {
            Node temp = edge[i];
            if (temp.a != 1) {
                ans += query(1, 1, N, temp.b + 1);
            }

            update(1, 1, N, temp.b);
        }
        System.out.println(ans);
    }

    static void update(int node, int left, int right, int idx) {
        //범위와 상관 없는 경우
        if (idx < left || idx > right) {
            return;
        }
        if (left == right) {
            tree[node] += 1;
            return;
        }

        int mid = (left + right) / 2;
        update(node << 1, left, mid, idx);
        update(node << 1 | 1, mid + 1, right, idx);

        tree[node] = tree[node << 1] + tree[node << 1 | 1];
    }

    static int query(int node, int left, int right, int qleft) {
        //범위와 상관 없는 경우
        if (qleft > right) {
            return 0;
        }
        //완전히 범위에 속하는 경우
        if (qleft <= left) {
            return tree[node];
        }

        int mid = (left + right) / 2;
        int lR = query(node << 1, left, mid, qleft);
        int RR = query(node << 1 | 1, mid + 1, right, qleft);

        return lR + RR;
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