import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class egg{
	int S;
	int W;
	public egg(int S,int W) {
		this.S=S;
		this.W=W;
	}
}

public class Main {
static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
static StringTokenizer st;
static int N,cnt,max;
static egg[] eggs;
static int[] tgt=new int[2];

public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		attack(0,0);
		System.out.println(max);
	}
static void input() throws NumberFormatException, IOException {
	N=Integer.parseInt(br.readLine());
	eggs = new egg[N];
	for (int i = 0; i < N; i++) {
		st=new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		eggs[i]=new egg(s,w);
		
	}
	
}

static void attack(int tgtIdx, int cnt) {

	if(tgtIdx == N) {
		
		max = Math.max(max, cnt);
		return;
	}
	
	if(eggs[tgtIdx].S <= 0 || cnt == N-1) {
		attack(tgtIdx+1, cnt);
		return;
	}

	int nCnt = cnt; 
	for(int i=0; i<N; i++) { 
		if(i == tgtIdx) continue;
		if(eggs[i].S <= 0) continue; 
		
	
		eggs[tgtIdx].S -= eggs[i].W;
		eggs[i].S -= eggs[tgtIdx].W;
		
		
		if(eggs[tgtIdx].S <=0) cnt++;
		if(eggs[i].S <=0) cnt++;
		
	
		attack(tgtIdx + 1, cnt);
	
		eggs[tgtIdx].S += eggs[i].W;
		eggs[i].S += eggs[tgtIdx].W;
		cnt = nCnt; 
	}
}

}