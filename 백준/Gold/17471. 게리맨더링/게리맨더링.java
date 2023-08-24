import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

//부분집합을 통해서 A,B 두개의 그룹으로 나누고 각각 연결여부 bfs, dfs로 확인 후 - 최소값 갱신
//dfs 
public class Main {
	static int N, min;
	static int[][] matrix;
	static boolean[] select; //부분집합용도
	static boolean[] visit; //bfs, dfs 중복 check
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		matrix = new int[N+1][N+1]; //0 dummy
		select = new boolean[N+1];
		visit = new boolean[N+1];
		min = Integer.MAX_VALUE;
		
		//인구수 -> 별도의 자료구조 대신 matrix[V][0] 를 활용 
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			matrix[i][0] = Integer.parseInt(st.nextToken());
		}
		
		//그래프 인접 행렬
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); //i에 인접한 구역의 수
			for(int j=1; j<=n; j++	) {
				int v = Integer.parseInt(st.nextToken());
				matrix[i][j] = v;
			}
		}
		
		//부분집합
		subset(1);
	
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}
	
	static void dfs(int v, boolean sel) { //sel -> true : A그룹, false : B그룹
		visit[v] = true; //해당 정점 방문
		for(int i=1; i<=N; i++) {
			int adj = matrix[v][i];
			if(adj != 0 && !visit[adj] && select[adj] == sel) {
				dfs(adj, sel);
			}
		}
	}
	
	static void check() {
		//두 그룹이 각각 유효한지 따진다. (연결)
		//그룹별로 따지되, 양쪽 다 연결여부를 확인
		//dfs로 각각 갈 수 있는 곳으로 가서 visit -> true
		//두 그룹 모두 따지고 나면 visit 전체 true ? 일부라도 false?
		
		//자료구조 초기화
		Arrays.fill(visit, false);
		
		//A - select[] : true
		int a = -1; //A그룹의 dfs 시작 정점
		
		for(int i=1; i<=N; i++)	{
			if(select[i]) {
				//선택된 한 정점에서 dfs
				a=i;
				break;
			}	
		}
		
		if(a == -1) return;
		
		dfs(a,true); 
		
		//B - select[] : false
		int b = -1; //B그룹의 dfs 시작 정점
		for(int i=1; i<=N; i++)	{
			if(!select[i]) {
				//선택된 한 정점에서 dfs
				b=i;
				break;
			}
					
		}
				
		if(b == -1) return;
		
		dfs(b,false);
			
		//A,B 모두 연결되어 있는지 visit[] 확인
		for(int i=1; i<=N; i++) {
			if(!visit[i]) return; //연결되지 않은 정점들이 하나라도 있으면 끝낸다.
		}
			
		//여기까지 두 그룹을 다 나누었고, 모두 연결된 상태
		//두 그룹의 인구수의 차이를 계산 min과 비교
		
		int sumA = 0;
		int sumB = 0;
		for(int i=1; i<=N; i++) {
			if(select[i]) sumA += matrix[i][0];
			else sumB += matrix[i][0];
		}
		
		min = Math.min(min,  Math.abs(sumA - sumB));
	}
	
	static void subset(int srcIdx) {
		if(srcIdx == N+1) {
			//complete Code 
			//2가지로 나뉜 상태
			check();
			
			return;
		}
		
		select[srcIdx] = true;
		subset(srcIdx + 1);
		select[srcIdx] = false;
		subset(srcIdx + 1);
	}
}