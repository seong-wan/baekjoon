import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//N 카드수, M 블랙잭 합 , M에 가깝게

public class Main {
	static int N,M,diff,sum,result;
	static int MinDiff = 3000000;
	static int[] numN;
	static int[] card;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numN = new int[N];
		card = new int[3];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numN[i] = Integer.parseInt(st.nextToken());
		}
		
		black(0,0);
		System.out.println(result);
	}

	static void black(int NIdx, int cardIdx) {
		if(cardIdx == 3) {
			sum = 0;
			for(int i=0; i<3; i++) {
				sum += card[i];
			}
			if(M-sum >= 0) {
				diff = M-sum;
				if(diff<MinDiff) {
					MinDiff = diff;
					result = sum;
				}
			}
			
			return;
		}
		
		if(NIdx == N) return;
		
		card[cardIdx] = numN[NIdx];
		black(NIdx + 1, cardIdx+1);
		black(NIdx + 1, cardIdx);
	}
}
