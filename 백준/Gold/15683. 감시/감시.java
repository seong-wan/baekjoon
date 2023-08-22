import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N,M,cctvCnt,min,blind;
	static int[][] map;
	static int[][] copyMap;
	static int[] src = {0,1,2,3};
	static int[] tgt;
	static List<Node> cctv = new ArrayList<>();
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		copyMap = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				copyMap[i][j] = map[i][j];
				
				if(map[i][j]!=0 && map[i][j]!=6) {
					cctv.add(new Node(i, j, map[i][j]));
				}
			}
		}
		//각 cctv가 바라보는 방향을 담은 tgt
		tgt = new int[cctv.size()];
		min = Integer.MAX_VALUE;
		
		perm(0);
		
		System.out.println(min);
	}
	
	static void perm(int tgtIdx) {
		//기저조건
		if(tgtIdx == tgt.length) {
			
			for(int i=0; i<tgt.length; i++) {
				fillMap(i, tgt[i]);
			}

			//complete code -> 총 사각지대 세서 최소크기 비교
			blind = blindCheck(); // 맵에서 0의 수 세기
			min = Math.min(min, blind);
			
			//다 쓴 맵 초기화
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					map[i][j] = copyMap[i][j];
				}
			}
			
			return;
		}
		
		//각 cctv 4방향 돌려주기
		for(int i=0; i<4; i++) {
			tgt[tgtIdx] = src[i];
			perm(tgtIdx + 1);
		}
	}
	
	static void fillMap(int cctvIdx, int direction) {
		Node temp = cctv.get(cctvIdx);
		
		int ny = temp.y;
		int nx = temp.x;
		int type = temp.num;
		
		switch(type) {
		
		case 1:
			while(true) {
				ny += dy[direction];
				nx += dx[direction];
				
				if(ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 6) break;
				map[ny][nx] = 7;
			}
			break;
			
		case 2:
			for(int i=0; i<2; i++) {
				int d = ( direction + 2*i ) % 4;
				ny = temp.y;
				nx = temp.x;
				while(true) {
					ny += dy[d];
					nx += dx[d];
					if(ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 6) break;
					map[ny][nx] = 7;
				}
			}
			break;
		
		case 3:
			for(int i=0; i<2; i++) {
				int d = ( direction + i ) % 4;
				ny = temp.y;
				nx = temp.x;
				while(true) {
					ny += dy[d];
					nx += dx[d];
					if(ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 6) break;
					map[ny][nx] = 7;
				}
			}
			break;
		
		case 4:
			for(int i=0; i<3; i++) {
				int d = ( direction + i ) % 4;
				ny = temp.y;
				nx = temp.x;
				while(true) {
					ny += dy[d];
					nx += dx[d];
					if(ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 6) break;
					map[ny][nx] = 7;
				}
			}
			break;
			
		case 5:
			for(int i=0; i<4; i++) {
				ny = temp.y;
				nx = temp.x;
				
				while(true) {
					ny += dy[i];
					nx += dx[i];
					if(ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 6) break;
					map[ny][nx] = 7;
				}
			}
			break;
		}
		
	}
	static int blindCheck() {
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				
				if(map[i][j] == 0) cnt++;
			}
		}
		return cnt;
	}

	static class Node{
		int y,x,num;
		
		public Node(int y, int x, int num) {
			this.y = y;
			this.x = x;
			this.num = num;
		}
	}

}