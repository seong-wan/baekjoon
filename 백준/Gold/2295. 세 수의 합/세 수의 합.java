import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] input;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        input = new int[N];

        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(input);

        for (int i = N - 1; i >= 1; i--) {
            int candidate = input[i];
            for (int j = i - 1; j >= 0; j--) {
                int second = input[j];

                int diff = candidate - second;

                int left = 0;
                int right = j;

                while (left <= right) {
                    int sum = input[left] + input[right];

                    if (sum == diff) {
                        System.out.println(candidate);
                        return;
                    }

                    if (sum > diff) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
    }
}