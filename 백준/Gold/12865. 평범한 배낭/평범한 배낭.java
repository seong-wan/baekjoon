import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,K;
	static int[][] item;
	static int[] memoi;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		item = new int[N+1][2];
		memoi = new int[K+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<2; j++) {
				item[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//memoi[] 배열을 1차원으로 선언하는 방식
		
		for(int i=1; i<=N; i++) {
			//가장 큰 무게부터 작은 무게로 움직이면서
			for(int j = K ; j >= item[i][0]; j--) {
				//memoi[j] => i번째 item을 고려하기 전까지 w무게를 만드는데 최상의 value
				//memoi[j] 를 새로운 현재 고려하는 값으로 덮어쓰지 않으면 이전 item을 고려한 최상의 value
				memoi[j] = Math.max(memoi[j], memoi[j-item[i][0]] + item[i][1]);
			}
		}
		
		System.out.println(memoi[K]);
	}

}