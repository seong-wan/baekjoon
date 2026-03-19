import java.io.*;
import java.util.*;
 
public class Main
{
    static long a, b;
 
    public static long gcd(long x, long y)
    {
        if (y == 0)
            return x;
        else
            return gcd(y, x % y);
    }
 
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
 
        for (int i = 0; i < gcd(a, b); i++)
            bw.write(Integer.toString(1));
 
        br.close();
        bw.flush();
        bw.close();
    }
}