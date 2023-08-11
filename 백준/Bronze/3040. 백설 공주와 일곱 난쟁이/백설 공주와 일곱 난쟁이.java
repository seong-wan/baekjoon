import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] dwarf = new int[9];//난쟁이 9명의 숫자가 들어갈 배열
	static int sum;//9명의 숫자의 합
	static int dif;//숫자의 합과 100의 차이
	static int rem1, rem2;//없애야 할 난쟁이의 인덱스

	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		chk();
		print();
	}

	static void input() throws NumberFormatException, IOException {
		for (int i = 0; i < 9; i++) {
			sum += dwarf[i] = Integer.parseInt(br.readLine());
		}
		dif = sum - 100;
	}//난쟁이 숫자를 전부 배열에 넣으면서 합과 100의 차이를 구함

	static void chk() {
		for (int i = 0; i < 9; i++) {

			for (int j = i + 1; j < 9; j++) {
				if (dwarf[i] + dwarf[j] == dif) {
					rem1 = i;
					rem2 = j;
					break;
				}

			}
		}//for문을 돌면서 숫자의 합이 dif와 같은 난쟁이 2명의 인덱스를 구함
	}

	static void print() {
		for (int i = 0; i < 9; i++) {
			if (i == rem1 || i == rem2)
				continue;
			System.out.println(dwarf[i]);
		}
	}//제거할 난쟁이를 제외한 난쟁이들의 숫자를 출력

}
