import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] computer;
    static long sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        computer = new int[N][N];

        long end = 0;
        for(int i = 0 ; i < N ; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                computer[i][j] = Integer.parseInt(st.nextToken());
                sum += computer[i][j];
                end = Math.max(end, computer[i][j]);
            }
        }


        long start = 0;
        long half = 0;
        if(sum == 0) System.out.println(0);
        else {
            while (start <= end) {
                half = (start + end) / 2;
                if (solution(half)) {
                    end = half - 1;
                } else {
                    start = half + 1;
                }
            }

            System.out.println(start);
        }

    }

    static boolean solution(long num){
        long result = 0;
        for (int[] i : computer) {
            for (int j : i) {
                if( j >= num) result += num;
                else result += j;
            }
        }
        return result * 2 >= sum;

    }
}
