import java.io.BufferedReader;
import java.io.InputStreamReader;
//PriorityQueue를 사용하지 않는 <= 배열을 이용해서 직접구현(힙 구현x)
//10만개의 정수 배열을 만들고, from, to index 를 이용
public class Main {
	//1.입력값이 0이면 배열에서 절댓값이 가장 작은 값 출력, 제외
	//1-2 절댓값이 가장 작은 값이 여러개면 가장 작은 수 출력, 제외
	//2 입력 값이 x이면 배열에 x 추가
	//3.배열이 비어있는데 입력 값이 0 이면 0출력
	
	static int N, from, to;
	static int[] array = new int[100000];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		//N개의 연산 처리
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0) {
				check();
			}else {
				array[to++] = num;
			}
		}
		
	}
	
	static void check() {
		// 대상이 없을 때 0 <= 최초, 다 꺼냈을 때
		if(from == to) {
			System.out.println(0);
			return;
		}
		
		//최소 값 (최소 값의 index)
		int minNum = Integer.MAX_VALUE;
		int minIdx = from;
		
		for(int i=from; i<to; i++) {
			//array[i] 를 따져본다.
			if(Math.abs(minNum) == Math.abs(array[i])) {//현재 최소 절대값과 같은 경우
				if(array[i] < minNum) {
					minNum = array[i];
					minIdx = i;
				}
			}else if(Math.abs(minNum) > Math.abs(array[i])) {//현재 최소 절대값보다 큰 경우
				minNum = array[i];
				minIdx = i;
			}
		}
		
		System.out.println(minNum); //출력
		//최소값을 빼는 부분 <=from 과 바꿔치기
		array[minIdx] = array[from];
		from++;
		
	}

}
