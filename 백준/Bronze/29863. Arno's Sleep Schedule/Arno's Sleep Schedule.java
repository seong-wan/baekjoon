import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sleep = Integer.parseInt(br.readLine());
        int wake = Integer.parseInt(br.readLine());

        int time = wake - sleep;

        System.out.println(time < 0 ? time + 24 : time);
    }
}