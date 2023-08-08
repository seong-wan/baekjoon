import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Queue 사용
public class Main {
	static int N,M;
	static Queue<Integer> queue = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{ 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//살아있는 사람 전체를 queue에 담는다
		for (int i=1; i<=N; i++ ) {
			queue.offer(i);
		}
		
		int aliveCnt = 1;
		
		sb.append("<");
		
		while(!queue.isEmpty()) {
			int num = queue.poll();
			
			if( aliveCnt % M == 0 ) {
				sb.append(num).append(", ");
			}else {
				queue.offer(num); //살아있지만 K번째가 아닌 사람은 다시 담는다.
			}
			
			aliveCnt++;
		}
		
		sb.setLength(sb.length()-2);
		sb.append(">");
		System.out.println(sb);
	}

}
