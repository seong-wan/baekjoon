import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
//야구공 1 시간초과나용
//src 수만큼 순열로 tgt를 만들려고하면 perm() 파라미터를 tgt 기준이 아닌 src 기준으로 변경할 수 있다.
//perm3를 보세요~~~~~~~~~~~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class Main {
	static int N,num,max; //이닝 
	static int[][] score; //각 선수가 각 이닝에서 얻는 결과 , 행:이닝 , 열:선수
	static int[] tgt = new int[9];
	static int[] src = {0,1,2,3,4,5,6,7,8};
	static boolean[] visit = new boolean[9];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		score = new int[N][9];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				score[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//0번째 선수는 3위치에 고정시키기
		tgt[3] = src[0];
		visit[3] = true;
		
		perm(1);
		
		System.out.println(max);
	}
	static void perm(int srcIdx) {
		//기저조건 -> 1번 선수를 4번 타자로 고정
		if(srcIdx == src.length) {
			//complete code
			num = game();
			max = Math.max(max, num);
			
			return;
		}
		
		for(int i=0; i<9; i++) {
			if(visit[i]) continue;
			
			tgt[i] = src[srcIdx];
			visit[i] = true;
			perm(srcIdx + 1);
			visit[i] = false;
		}
	}
	
	static int game() {
		int sum = 0;
		int out = 0;
		Deque<Integer> q = new ArrayDeque<>();
		int playerIdx = 0;
		
		for(int i=0; i<N; i++) { //N이닝 돌리기
			out = 0;
			if(!q.isEmpty()) q.clear();
			q.offer(0);
			q.offer(0);
			q.offer(0); //각 자리 비워주기
			while(out != 3) {
				
				if(score[i][tgt[playerIdx%9]] == 0) {
					out++;
					
				}else if(score[i][tgt[playerIdx%9]] == 1) {
					q.offer(1);
					if(q.poll() == 1) {
						sum++;
					}
					
				}else if(score[i][tgt[playerIdx%9]] == 2) {
					q.offer(1);
					if(q.poll() == 1) {
						sum++;
					}
					q.offer(0);
					if(q.poll() == 1) {
						sum++;
					}
				}else if(score[i][tgt[playerIdx%9]] == 3) {
					q.offer(1);
					if(q.poll() == 1) {
						sum++;
					}
					for(int j=0; j<2; j++) {
						q.offer(0);
						if(q.poll() == 1) {
							sum++;
						}
					}
					
				}else if(score[i][tgt[playerIdx%9]] == 4) {
					for(int j=0; j<3; j++) {
						if(q.poll() == 1) {
							sum++;
						}
					}
					//자리 비워주기
					q.offer(0);
					q.offer(0);
					q.offer(0);
					sum++; //홈런을 친 선수 넣어주기
				}
				
				playerIdx++;
			}
		}
		
		return sum;
	}
}