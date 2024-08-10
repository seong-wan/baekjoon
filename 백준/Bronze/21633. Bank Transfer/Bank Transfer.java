import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static double k;

    public static void main(String[] args) throws Exception {
        k = Double.parseDouble(br.readLine());
        double ans = k / 100 + 25;
        if (ans < 100)
            ans = 100;
        if (ans > 2000)
            ans = 2000;

        System.out.printf("%.2f", ans);
    }
}