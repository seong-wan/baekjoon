import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N,M,start_y, start_x;
    static char[][] maze;
    static Queue<A> queue = new ArrayDeque<>();
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static boolean[][][] visit;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new char[N][M];
        
        for (int i = 0; i < N; i++) {
            maze[i] = br.readLine().toCharArray();
        }
        
        for (int i = 0; i < N; i++) {
            boolean check = false;
            for (int j = 0; j < M; j++) {
                if( maze[i][j] == '0') {
                    start_y = i;
                    start_x = j;
                    check = true;
                    break;
                }
            }
            if( check ) break;
        }
        
        visit = new boolean[N][M][64]; // 2차원 배열에 1차원 배열인 boolean[64]를 추가하여 가진 키에 따라 방문처리를 다르게
        // 해줘야 하기 때문에 비트마스킹 느낌처럼 구현
        int result = 0;
        queue.offer(new A(start_y, start_x, 0, 0));// 민식이의 시작값
        
        while(!queue.isEmpty()) {
            A temp= queue.poll();
            int y = temp.y;
            int x = temp.x;
            int cnt = temp.cnt;
            int keys = temp.key;
            visit[y][x][keys] = true;
            
            if( maze[y][x] == '1') {// 도착
                result = temp.cnt;
                break;
            }
              
            for(int i=0; i < 4; i++) {
                int Y = y + dy[i];
                int X = x + dx[i];
                
                if( Y >= N || X >= M || Y < 0 || X < 0) continue; //범위 out
                if( visit[Y][X][keys] ) continue; // 그 키들로 방문한곳이면 다시 재방문 X
                
                //이제부터 갈수있는지 확인
                if( maze[Y][X] == '.' || maze[Y][X] == '1' || maze[Y][X] == '0') {// 1. .이거나 도착이면
                	queue.offer(new A(Y,X,cnt+1,keys));
                	visit[Y][X][keys] = true;
                }else if( maze[Y][X] >= 65 && maze[Y][X] <= 70) {// 2. 문이면
                	// 키 있는지 확인하고 있으면 queue에 넣기
                	if( (keys & 1<<(maze[Y][X] - 65)) > 0 ) {
                		queue.offer(new A(Y,X,cnt+1,keys));
                		visit[Y][X][keys] = true;
                	}
                }else if( maze[Y][X] >= 97 && maze[Y][X] <= 102 ) {// 3. 키이면
                	// 일단 키를 먹자.
                	
                	int newKeys = keys | 1<<(maze[Y][X] - 97);
                	// 가지고 있던 키로 방문한적이 없으면 queue에 넣기
                	if( !visit[Y][X][newKeys] ) {
                		queue.offer(new A(Y,X,cnt+1,newKeys));
                		visit[Y][X][newKeys] = true;
                	}
                }
                
                
            }
        }
        
        if( result == 0) System.out.println(-1);
        else System.out.println(result);
    }
    
    public static class A{
        int y;
        int x;
        int cnt;
        int key;
        
		public A(int y, int x, int cnt, int key) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
			this.key = key;
		}
    }
}