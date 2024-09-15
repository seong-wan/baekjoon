import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        System.out.println(2 * pow(3, N - 1));
    }

    static int pow(int num, int p) {
        int result = 1;
        for (int i = 0; i < p; i++) {
            result *= num;
        }
        return result;
    }
}