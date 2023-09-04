import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N,M;
	static int[] input,target;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input);
		M = Integer.parseInt(br.readLine());
		target = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int now = Integer.parseInt(st.nextToken());
			int start = 0;
			int end = N-1;
			int mid = (end+start) / 2;
			
			while(start <= end) {
				if(input[mid] > now) {
					end = mid-1;
					mid = (end+start) / 2;
				}else if(input[mid] < now) {
					start = mid+1;
					mid = (end+start) / 2;
				}else {
					target[i] = 1;
					break;
				}
			}
			sb.append(target[i]).append(" ");
		}
		System.out.println(sb);
	}

}