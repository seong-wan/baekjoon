import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//조합 + 시뮬레이션
public class Main {
	static int N,M,D,max;
	static int[] archer = new int[3]; //조합으로 선택된 궁수의 x좌표
	static List<Enemy> enemyCopy = new ArrayList<>(); //최초 테케입력으로부터 조합 완성 후 시뮬레이션 시작시 사용할 원본
	static List<Enemy> enemy = new ArrayList<>(); //시뮬레이션 과정에서 사용되는 (변하는 Enemy를 관리)
	
	//궁수로부터 가장 가까운 적을 찾는...
	//거리가 같다면 x좌표가 더 왼쪽인걸 
	static PriorityQueue<Enemy> pqueue = new PriorityQueue<>(
			(e1, e2) -> e1.d == e2.d ? e1.x - e2.x : e1.d - e2.d);
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		//적군
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int n = Integer.parseInt(st.nextToken());
				if(n==1) enemyCopy.add(new Enemy(i,j));
			}
		}
		
		//풀이
		comb(0,0); //M개의 자리 (y는 맵 바로 밑) 중에서 3개를 뽑아서 archer[] (tgt)에 담고 처리
		System.out.println(max);
		
	}
	
	static void check() {
		//시뮬레이션 진행
		//적군 초기화
		 enemy.clear();
		 for(Enemy e : enemyCopy) {
			 enemy.add(new Enemy(e.y, e.x)); //객체를 공유하지 않고 내용만 복사
		 }
		//while() 시뮬레이션 진행
		 int dead = 0; //죽인 적군의 수
		 while(true) {
			 //궁수 3명이 한명씩 적군 쏜다.
			 for(int i=0; i<3; i++) {
				 pqueue.clear();
				 
				 int ac = archer[i]; //현재 궁수의 x좌표
				 int size = enemy.size(); //현재 적군의 크기
				 for(int j=0; j<size; j++) { //현재 모든 적군에 대해서
					 Enemy e = enemy.get(j);
					 e.d = Math.abs(ac - e.x) + Math.abs(N - e.y); 
					 if(e.d <= D) { //사정거리 안에 들어오는 적이면 pqueue에 담는다.
						 pqueue.offer(e);
					 }
				 }
				 
				 // pqueue 를 이용해서 우선순위가 높은 적군을 꺼낸다.
				 if(!pqueue.isEmpty()) {
					 pqueue.poll().dead = true; //가장 우선순위 높은 친구를 꺼내서 죽임
				 }
			 }
			 //죽은 적군을 enemy 제거, 남은 적군 한 칸 아래로 이동, 경계선을 벗어나면 enemy에서 제거
			 for(int i=enemy.size() - 1; i >= 0; i--) {
				Enemy e = enemy.get(i);
				if(e.dead) {
					enemy.remove(i);
					dead++;
				}else if(e.y == N-1) { //경계선을 벗어나면 
					enemy.remove(i);
				}else { //죽지도 않고 경계선도 안나가면 y좌표 ++, 맵에서 내려주기
					e.y++;
				}
			 }
			 
			 //시뮬레이션 종료 조건
			 if(enemy.size() == 0)break;
		 }
		 
		 max = Math.max(max, dead);
	}
	
	static void comb(int srcIdx, int tgtIdx) {
		//기저조건
		if(tgtIdx == 3) {
			//complete code
			//simulation
			check();
			return;
		}
		
		if(srcIdx == M) return;
		
		archer[tgtIdx] = srcIdx; //궁수의 자리를 선택
		
		comb(srcIdx + 1, tgtIdx + 1); //선택
		comb(srcIdx + 1, tgtIdx ); //비선택
	}
	static class Enemy{
		int y, x, d; //d : 궁수와의 거리
		boolean dead; //사망 여부
		
		Enemy(int y, int x){ //d, dead <= 시뮬레이션을 진행하면서 setting
			this.y = y;
			this.x = x;
		}
	}

}