//input 길이가 꽤 크다, 50만개의 입력
//Scanner, BufferedReader 경우의 차이가 커진다.
//자료구조 = 모두 다 담고 왔다갔다 하는 배열?? <= 불필요한 자료는 제거하는 stack (나보다 작은애들은 버리면서 감)
//output도 커서 StringBuilder
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

//N개의 탑 꼭대기에 송신기
//송신기는 수평 왼쪽 방향으로 레이저 신호 발사
//탑의 기둥에는 수신 장치, 하나의 레이저 신호는 가장 먼저 만나는 단 하나의 탑에서만 신호수신 가능
//탑의 수  1<=N<=500000 -> 배열로 for문 2번 돌면 시간초과.... 
//1<=높이<=10,000,000
//수신 탑 없으면 0 , 출력 : 수신탑 출력
//뒤에서 부터 -- , 큰 높이가 있다면 

public class Main {
	static int N;
	static Deque<int[]> stack = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i=1; i<=N; i++) {
			int height = Integer.parseInt(st.nextToken());
			//stack 에서 현재 높이보다 작은 애들은 제거, 큰 것이 나오면 그 큰 것의 번호를 출력에 추가
			while(!stack.isEmpty()) {
				//자신보다 큰 경우
				if(stack.peek()[1] >= height) {
					sb.append(stack.peek()[0]).append(" ");
					break;
				}
				
				stack.pop(); //height보다 작으면 제거
			}
			
			//내가 제일 높으면
			if(stack.isEmpty()) {
				sb.append("0 ");
			}
			stack.push(new int[] {i, height});
		}
		
		System.out.println(sb);
		

	}

}
