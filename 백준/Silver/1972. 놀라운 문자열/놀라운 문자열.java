import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String s;
    static StringBuilder sb = new StringBuilder();
    static final String yes = " is surprising.";
    static final String no = " is NOT surprising.";
    static Set<String> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        while (true) {
            s = br.readLine();
            boolean check = true;
            if (s.equals("*"))
                break;

            for (int i = 0; i < s.length() - 2; i++) {
                set.clear();
                for (int j = 0; j < s.length() - 1 - i; j++) {
                    String temp = s.charAt(j) + s.substring(j + 1 + i, j + 2 + i);
                    set.add(temp);
                }
                if (set.size() != s.length() - 1 - i) {
                    sb.append(s).append(no).append("\n");
                    check = false;
                    break;
                }
            }
            if (check)
                sb.append(s).append(yes).append("\n");

        }
        System.out.println(sb);
    }
}