import java.util.Scanner;

public class Main {
    static int[][] arr;
    static int r, c;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int max = 0; // 최대 방문 횟수
    static boolean[] alpha; // 방문한 알파벳인지 확인

    public static void main(String[] args)  {
        Scanner scan = new Scanner(System.in);

        r = scan.nextInt();
        c = scan.nextInt();
        scan.nextLine();

        arr = new int[r][c];

        for (int i = 0; i < r; i++) {
            String str = scan.nextLine();
            for (int j = 0; j < c; j++) {
                arr[i][j] = str.charAt(j) - 'A';
            }
        }

        alpha = new boolean[26];
        dfs(0, 0, 1);
        System.out.println(max);
    }

    //len은 몇 번 지났는지
    public static void dfs(int x, int y, int len){
        alpha[arr[x][y]] = true; // 알파벳 방문 체크
        max = Math.max(max, len);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >=0 && nx < r && ny < c){
                if(alpha[arr[nx][ny]] == false){
                    dfs(nx, ny, len+1);
                    //갈 수 있는 길 하나를 끝까지 가봤으면 다시 알파벳 방문을 초기화해줘야함
                    alpha[arr[nx][ny]] = false;
                }
            }
        }
    }
}