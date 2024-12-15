import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] liquid;
    static int[] ans = new int[3];
    static long minValue = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        liquid = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            liquid[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(liquid);

        for (int i = 0; i < N - 2; i++) {
            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                long temp = (long) liquid[i] + liquid[left] + liquid[right];

                if (Math.abs(temp) < minValue) {
                    minValue = Math.abs(temp);
                    ans[0] = i;
                    ans[1] = left;
                    ans[2] = right;
                }

                if (temp == 0) {
                    print();
                    return;
                } else if (temp < 0) left++;
                else right--;
            }
        }

        print();
    }

    static void print() {
        for (int i = 0; i < 3; i++) {
            System.out.print(liquid[ans[i]] + " ");
        }
    }
}