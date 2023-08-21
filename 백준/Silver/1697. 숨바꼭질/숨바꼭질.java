import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
//그래프 문제로 생각해보기
//bfs -> 최단경로
//visit
public class Main {
	
	static int N,K;
	static Queue<Integer> queue = new ArrayDeque<>();
	static int[] visit = new int[100001]; //boolean 이 아닌 int 쓰는 이유? -> visit자체에 몇번재 depth인지 기록
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		System.out.println(bfs(N, K));
	}
	
	static int bfs(int N, int K) {
		// 최소 초를 계산해서 return하기
		int minus = 0; //뺀값
		int plus = 0; //더한값
		int multiply = 0; //2배한 값
		
		visit[N] = 0; //depth계산을 위한 0
		queue.offer(N);
		
		while(!queue.isEmpty()) {
			// 꺼내서 K이면 return;
			int curr = queue.poll();
			if(curr == K) break;
			
			//그렇지 않으면 꺼내서 +1, -1, *2 에 대한 계산을 하고 유효하면 queue에 넣기
			minus = curr - 1;
			plus = curr + 1;
			multiply = curr * 2;
			
			if(minus >= 0 && visit[minus] == 0) {//음수가 아니고 방문하지 않았다면
				visit[minus] = visit[curr] + 1; //꺼낸 curr까지 온 depth보다 한 번 더 계산
				queue.offer(minus);
			}
			
			if(plus <= 100000 && visit[plus] == 0) {
				visit[plus] = visit[curr] + 1; 
				queue.offer(plus);
			}
			
			if(multiply <= 100000 && visit[multiply] == 0) {
				visit[multiply] = visit[curr] + 1; 
				queue.offer(multiply);
			}
		}
		return visit[K]; //boolean의 방문 여부 체크  + //K까지 오는데 몇번 걸렸는지 저장되잇음
	}
}