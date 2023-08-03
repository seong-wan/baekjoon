import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char[] ch;
	static StringTokenizer st;
	static int cnt;
	static int A, C, G, T, A_c, C_c, G_c, T_c;

	public static void main(String[] args) throws Exception, IOException {
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		String code = br.readLine();
		ch = code.toCharArray();
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		for (int i = 0; i < P; i++) {
			switch (ch[i]) {
			case 'A':
				A_c++;
				break;
			case 'C':
				C_c++;
				break;
			case 'G':
				G_c++;
				break;
			case 'T':
				T_c++;
				break;
			}

		}
		if (A_c >= A && C_c >= C && G_c >= G && T_c >= T)
			cnt++;
//		A_c = str_arr[0].length() - str_arr[0].replace("A", "").length();
//		C_c = str_arr[0].length() - str_arr[0].replace("C", "").length();
//		G_c = str_arr[0].length() - str_arr[0].replace("G", "").length();
//		T_c = str_arr[0].length() - str_arr[0].replace("T", "").length();
//		if (A_c >= A && C_c >= C && G_c >= G && T_c >= T)
//			cnt++;

		for (int i = P; i < S; i++) {
			switch (ch[i]) {
			case 'A':
				A_c++;
				break;
			case 'C':
				C_c++;
				break;
			case 'G':
				G_c++;
				break;
			case 'T':
				T_c++;
				break;
			}
			switch (ch[i - P]) {
			case 'A':
				A_c--;
				break;
			case 'C':
				C_c--;
				break;
			case 'G':
				G_c--;
				break;
			case 'T':
				T_c--;
				break;
			}
			if (A_c >= A && C_c >= C && G_c >= G && T_c >= T)
				cnt++;
		}
		System.out.println(cnt);
	}

}
