import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
//PriorityQueue를 사용하는
//10만개의 정수 배열을 만들고, from, to index 를 이용
public class Main {
	//1.입력값이 0이면 배열에서 절댓값이 가장 작은 값 출력, 제외
	//1-2 절댓값이 가장 작은 값이 여러개면 가장 작은 수 출력, 제외
	//2 입력 값이 x이면 배열에 x 추가
	//3.배열이 비어있는데 입력 값이 0 이면 0출력
	
	static int N, from, to;
	static PriorityQueue<Integer> pqueue = new PriorityQueue<>(
	(n1, n2) -> Math.abs(n1) == Math.abs(n2) ? n1 - n2 : Math.abs(n1) - Math.abs(n2)	
	);
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		//N개의 연산 처리
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0) {
				Integer min = pqueue.poll();
				System.out.println(min == null ? 0 : min);
			}else {
				pqueue.offer(num);
			}
		}
		
	}
	

}
