import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//상어가 겹칠 수 있으므로 2차원 배열만으로는 상어를 저장할 수 없음
//상어의 움직임이 규칙적이므로 수학적으로 계산해놓기
//상어를 ArrayList에 넣어놓고 map과 상호작용
public class Main {
	static int R,C,M,sum; 
	static Shark[][] map;
	static List<Shark> list = new ArrayList<>();
	
	
	//d가 1 위, 2아래, 3 우, 4 좌
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,1,-1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Shark[R+1][C+1]; //0:dummy
		
		for(int i=0; i<M; i++) { //상어 수 만큼
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			Shark shark = new Shark(r,c,s,d-1,z); //delta 위치 보정 1234 -> 0123
			
			//list와 map에 넣어주기
			list.add(shark);
			map[r][c] = shark;
			
		}
		
		//풀이
		for(int i=1; i<=C; i++) { //col 을 이동하면서 
			//상어를 잡는다.
			catchShark(i);
			
			//상어를 이동시킨다.
			//map에는 변화가 적용되지 않았다.
			moveShark();
			
			//상어를 정리한다.
			//리스트를 이용하여 map에 적용해준다.
			killShark();
		}
		
		System.out.println(sum);
	}
	
	//map에서 상어를 잡는다. 
	static void catchShark(int col) { //현재 column 번호를 받아서 아래로 이동하면서
		for(int i=1; i<=R; i++) { //밑으로 이동하면서
			if(map[i][col] != null) { //상어가 있다면
				sum += map[i][col].z; 
				list.remove(map[i][col]); //잡은 상어 리스트에서 지우기
				break; //한 마리 잡으면 끝남
			}
		}
	}
	
	//list에서 상어를 움직인다.
	//map에는 변화가 적용되 않았다.
	static void moveShark() {
		for (Shark shark : list) {
			int r = shark.r;
			int c = shark.c;
			int s = shark.s;
			int d = shark.d;
			
			switch(d) {
				//상,하
				case 0:
				case 1:
					s = s % ((R-1)*2); //제자리로 오는 계산을 수학적으로 선처리 후 남는 부분만 이동
					for(int i=0; i<s; i++) {
						//양끝이면 방향 전환
						if(r == 1) d=1; // 위에 도착하면 방향을 상 -> 하
						else if(r == R) d = 0; //아래에 도착하면 방향을 하->상
						
						r+= dy[d];
					}
					shark.r = r;
					shark.d = d;
					break;
			
					//좌,우
				case 2:
				case 3:
					s = s % ((C-1)*2); //제자리로 오는 계산을 수학적으로 선처리 후 남는 부분만 이동
					for(int i=0; i<s; i++) {
						//양끝이면 방향 전환
						if(c == 1) d=2; // 왼쪽에 도착하면 방향을 좌 -> 우
						else if(c == C) d = 3; //오른쪽에 도착하면 방향을 우->좌
						
						c+= dx[d];
					}
					shark.c = c;
					shark.d = d;
					break;
			}
		}
	}
	
	//기존 맵을 초기화
	//list -> map 기록하면서 겹치면 잡아먹는 처리~~
	static void killShark() {
		//맵 초기화 
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				map[i][j] = null;
			}
		}
		
		//list를 이용해서 map 정리
		int size = list.size();
		
		for(int i=size-1; i>=0; i--) { //삭제되기 때문에 뒤에서 부터 시작하기! index
			Shark s = list.get(i);
			
			if(map[s.r][s.c] == null) {
				map[s.r][s.c] = s;
			}else {
				if(s.z > map[s.r][s.c].z) { //list에서 꺼낸 상어가 더 크다면
					list.remove(map[s.r][s.c]); //작은 상어를 list에서 지워주고
					map[s.r][s.c] = s; //꺼낸 상어를 맵에 넣어주기
				}else {//list에서 꺼낸 상어가 더 작다면
					list.remove(i); //list에 있던 상어 지워주기
				}
			}
		}
	}
	
	//(r,c)위치, s 속력, d 이동방향, z 크기
	static class Shark{
		int r, c, s, d, z;
		Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
}