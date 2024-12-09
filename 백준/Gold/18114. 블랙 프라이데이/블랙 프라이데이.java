import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, C;
    static int[] input;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        input = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input);

        //되는 경우가 없을 때
        if (C < input[1]) {
            System.out.println(0);
            return;
        }

        for (int i = N; i >= 0; i--) {
            if (input[i] > C)
                continue;

            if (input[i] == C) {
                System.out.println(1);
                return;
            }

            if (i == 1)
                break;

            int num = C - input[i];
            int left = 0;
            int right = i - 1;

            while (left != right) {
                int sum = input[left] + input[right];
                if (sum == num) {
                    System.out.println(1);
                    return;
                } else if (sum > num) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        System.out.println(0);
    }
}