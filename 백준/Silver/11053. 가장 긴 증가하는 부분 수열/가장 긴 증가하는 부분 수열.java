import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[] arr;
	static int[] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new int[N]; // 길이, 마지막값
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.fill(dp, 1);
		
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if(arr[i] > arr[j]) {
					dp[i] = Math.max(dp[j]+1, dp[i]);
				}
			}
		}
		int max=0;
		for (int i = 0; i < N; i++) {
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}

}