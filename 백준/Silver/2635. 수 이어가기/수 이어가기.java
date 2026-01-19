import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Main {
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
 
        int maxLen = 1;
        int maxSecond = N;
 
        int first, second;
        for (int num = N; num >= 0; num--) {
            first = N;
            second = num;
            int cnt = 2;
 
            while (first - second >= 0) {
                int third = first - second;
                first = second;
                second = third;
                cnt++;
            }
 
            if (maxLen < cnt) {
                maxLen = cnt;
                maxSecond = num;
            }
        }
 
        StringBuilder sb = new StringBuilder();
        sb.append(maxLen).append("\n");
        sb.append(N).append(" ").append(maxSecond).append(" ");
 
        first = N;
        while (first - maxSecond >= 0) {
            int third = first - maxSecond;
            first = maxSecond;
            maxSecond = third;
 
            sb.append(maxSecond).append(" ");
        }
 
        System.out.println(sb);
    }
}