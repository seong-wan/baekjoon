import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M;
	static int[] topo;
	static List<List<Integer>> graph = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //학생수
		M = Integer.parseInt(st.nextToken()); //비교횟수
		
		topo = new int[N + 1]; // 0 dummy
		
		//인접리스트
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			//A -> B
			graph.get(A).add(B);
			topo[B]++; //진입차수증가
		}
	
		//풀이
		Queue<Integer> queue = new ArrayDeque<>();
		
		//진입 차수가 0인 번호를 queue에 넣기
		for(int i=1; i<=N; i++) {
			if(topo[i] == 0) {
				queue.offer(i);
			}
		}
		
		//queue를 이용해서 꺼내면서 연결을 끊고 다시 진입차수가 0인 학생을 queue에 담는다.
		while(!queue.isEmpty()) {
			int no = queue.poll();
			
			//queue 에서 꺼낸 번호가 바로 줄세우기 번호
			sb.append(no).append(" ");
			
			//no 학생으로부터 갈 수 있는 다른 학생을 모두 대상으로
			List<Integer> list = graph.get(no);
			int size = list.size();
			for(int i=0; i<size; i++) {
				int temp = list.get(i);
				topo[temp]--; //no -> temp관계인데 no를 그래프에서 제가하므로 temp의 진입차수가 늘 줄어든다.
				if(topo[temp] == 0) { //진입차수가 0이면 더 이상 누구를 기다릴 필요 없이 새로운 시작점이 된다.
					queue.offer(temp);
				}
			}
		}
		
		System.out.println(sb);
	}
}