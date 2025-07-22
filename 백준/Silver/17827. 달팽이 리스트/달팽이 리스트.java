import java.io.IOException;

public class Main {
	public static StringBuilder sb = new StringBuilder();
	public static java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		String s = br.readLine().trim();
		String str1[] = s.split(" ");	
		int n = Integer.parseInt(str1[0]);
		int m = Integer.parseInt(str1[1]);
		int v = Integer.parseInt(str1[2]);
		
		s = br.readLine().trim();
		str1 = s.split(" ");
		
		for(int i = 0; i < m; i++) {
			int q = Integer.parseInt(br.readLine().trim());
			sb.append(str1[check(n,q,v)]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static int check(int n, int q, int v) {
		int num = 0;
        //n보다 작으면 그대로 그 번호애
		if(q < n) {
			return q;
		}
        //n이면 계속 맨 끝에 애
		if(v == n) {
			return n-1;
		}
		//v-1 하는이유 : 배열에서 호출하는거라 1 빼줌.
		num = (q-n)%(n-v+1) + v-1;
		return num;
	}
}