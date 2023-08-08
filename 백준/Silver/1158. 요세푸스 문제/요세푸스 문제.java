import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static Queue<Integer> q = new ArrayDeque<>();  //요세푸스 순열 저장
	static LinkedList<Integer> l = new LinkedList<>(); //사람들

	public static void main(String[] args) throws Exception{ 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		for(int i=1; i<=N; i++) {
			l.add(i);
		}
		
		while(!l.isEmpty()) {
			
			for(int i=0; i<M-1; i++) {
				int temp = 0;
				temp = l.poll();
				l.add(temp);
			}
			
			q.add(l.poll());
		}
		
		sb.append("<");
		while(!q.isEmpty()) {
			sb.append(q.poll()).append(", ");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.deleteCharAt(sb.length()-1);
		sb.append(">");
		
		System.out.println(sb);
	}

}
