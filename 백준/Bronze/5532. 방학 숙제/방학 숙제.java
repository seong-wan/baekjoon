import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int L, A, B, C, D;
    static int workDay;

    public static void main(String[] args) throws Exception {
        L = Integer.parseInt(br.readLine());
        A = Integer.parseInt(br.readLine());
        B = Integer.parseInt(br.readLine());
        C = Integer.parseInt(br.readLine());
        D = Integer.parseInt(br.readLine());

        int temp = 0;
        if (A % C != 0) {
            temp = A / C + 1;
        } else {
            temp = A / C;
        }

        workDay = Math.max(workDay, temp);

        if (B % D != 0) {
            temp = B / D + 1;
        } else {
            temp = B / D;
        }

        workDay = Math.max(workDay, temp);

        System.out.println(L - workDay);
    }
}