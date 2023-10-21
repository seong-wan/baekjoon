import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		if (br.readLine().matches("(100+1+|01)+"))
			System.out.println("SUBMARINE");
		else
			System.out.println("NOISE");

	}

}