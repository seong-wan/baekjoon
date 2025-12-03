import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long T = Long.parseLong(st.nextToken());

        long[] L = new long[N];
        long[] R = new long[N];
        long sumL = 0, sumR = 0;
        long maxR = 0, maxL = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            L[i] = Long.parseLong(st.nextToken());
            R[i] = Long.parseLong(st.nextToken());
            sumL += L[i];
            sumR += R[i];
            maxR = Math.max(maxR, R[i]);
            maxL = Math.max(maxL, L[i]);
        }

        // 기본 불가능 조건
        if (sumL > T || sumR < T) {
            System.out.println(-1);
            return;
        }

        long need = T - sumL;

        long lo = maxL; 
        long hi = maxR;
        long ans = hi;

        while (lo <= hi) {
            long mid = (lo + hi) / 2;

            long extra = 0;
            for (int i = 0; i < N; i++) {
                long up = Math.min(R[i], mid);
                long canGive = up - L[i];
                if (canGive > 0) extra += canGive;
                if (extra >= need) break;
            }

            if (extra >= need) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        System.out.println(ans);
    }
}