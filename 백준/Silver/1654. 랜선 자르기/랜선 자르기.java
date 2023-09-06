import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N,K;
	static int[] lanes;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		lanes = new int[K];
		for (int i = 0; i < K; i++) {
			lanes[i] = Integer.parseInt(br.readLine());
		}
		// max값 찾기
		long end=0;
		long start=1;
		long mid=0;
		for (int i = 0; i < K; i++) {
			end = Math.max(end, lanes[i]);
		}
		
		while(start <= end) {
			mid = (start + end)/2; // 나눌 전선길이
			
			int cnt=0;
			for (int i = 0; i < K; i++) {
				cnt+=lanes[i] / mid;
			}
			
			if( cnt < N) { // 막대 길이가 큰 경우 이므로 end를 줄이자.
				end = mid-1;
			}else { // 막대 길이가 작거나 알맞은 경우이므로 start를 올리자. 
				start = mid+1;
			}
			
		}
		System.out.println(end);
	}

}