import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//N까지의 소수 구하기
//소수들의 누적합 구하기
//투포인터로 수보다 작거나 같으면 right++ 아니면 left++로 수와 누적합이 딱 맞는 범위의 개수 구하기
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static boolean[] isNotPrime;
	static List<Integer> primes = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		isNotPrime = new boolean[N + 1];

		searchPrime();
		makeSum();

		int cnt = 0;
		int left = 0;
		int right = 1;

		while (right != primes.size()) {
			int temp = primes.get(right) - primes.get(left);

			if (temp <= N)
				right++;
			else
				left++;

			if (temp == N)
				cnt++;
		}

		System.out.print(cnt);
	}

	static void searchPrime() {
		for (int i = 2; i <= Math.sqrt(N); i++) {
			if (!isNotPrime[i]) {
				for (int j = i * i; j <= N; j += i) {
					isNotPrime[j] = true;
				}
			}
		}

		primes.add(0); //누적합 더미 추가

		for (int i = 2; i <= N; i++) {
			if (!isNotPrime[i])
				primes.add(i);
		}
	}

	static void makeSum() {
		for (int i = 1; i < primes.size(); i++) {
			primes.set(i, primes.get(i) + primes.get(i - 1));
		}
	}
}