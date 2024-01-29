import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i =0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(A);
		
		int answer = 0;
		int start = 0;
		int end = N-1;
		
		while(start<end) {
			if(A[start] + A[end] == M) {
		    	answer++;
		        start++;
		        end--;
		    }
		    else if(A[start] + A[end] > M) end--;
		    else start++;
		}
		System.out.println(answer);
	}

}