import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//재료 N개, 신맛 S -> 곱, 쓴맛 B -> 합
// 신맛과 쓴맛의 차이가 작게, 재료는 적어도 하나 이상
// 출력 : 차이가 가장 작은 요리의 차이 
public class Main {
	static int N;
	static int[][] ingre;
	static int subsetCnt;
	static int S,B;
	static int diff = 1000000000;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		ingre = new int[N][2];
		subsetCnt = 1 << N;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			ingre[i][0] = Integer.parseInt(st.nextToken());
			ingre[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<subsetCnt; i++) { //재료를 하나도 안쓰는건 안돼~~~~
			S = 1;
			B = 0;
			for(int j=0; j<N; j++) {
				if((i & 1 << j)!=0) {
					S = S * ingre[j][0];
					B = B + ingre[j][1];
				}
			}
			int temp = Math.abs(S - B);
			diff = Math.min(diff, temp);
		}
		
		System.out.println(diff);
	}

}
