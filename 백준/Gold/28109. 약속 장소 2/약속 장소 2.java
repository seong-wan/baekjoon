import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K, origin;
    static String s;
    static int[] check;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()) - 1;

        //가게 이름 존재 X
        if (K > 25 * N) {
            System.out.println(-1);
            return;
        }

        s = br.readLine();

        check = new int[N];

        //A부터 Z를 0~26으로 표시
        for (int i = 0; i < N; i++) {
            check[i] = s.charAt(i) - 'A';
            origin += check[i];
        }

        //본래 단어가 그대로 가게 이름인 경우
        if (K == origin)
            System.out.println(s);
        else if (K < origin) {
            int temp = K;
            for (int i = 0; i < N; i++) {
                if (temp >= check[i]) {
                    temp -= check[i];
                } else {
                    check[i] = temp;
                    for (int j = 0; j < N; j++) {
                        sb.append((char) ('A' + check[j]));
                    }
                    System.out.println(sb);
                    break;
                }
            }
        } else {
            int temp = K - origin;

            for (int i = N - 1; i >= 0; i--) {
                if (check[i] + temp > 25) {
                    temp -= 25 - check[i];
                } else {
                    check[i] += temp;
                    for (int j = 0; j < N; j++) {
                        sb.append((char) ('A' + check[j]));
                    }
                    System.out.println(sb);
                    break;
                }
            }
        }
    }
}