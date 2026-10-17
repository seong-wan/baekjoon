import java.util.*;

class Solution {
    static int n,m;
    static int[][] arr;
    static int[][] checked;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,-1,0,1};
    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        arr = maps;
        checked = new int[n][m];
        
        bfs();
        
        return checked[n-1][m-1]==0?-1:checked[n-1][m-1];
    }
    static void bfs(){
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0,0});
        checked[0][0]=1;
        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int cr = cur[0];
            int cc = cur[1];
            
            for(int dir = 0; dir<4; dir++){
                int nr = cr+dr[dir];
                int nc = cc+dc[dir];
                
                if(canGo(nr,nc)){
                    checked[nr][nc]=checked[cr][cc]+1;
                    queue.add(new int[]{nr,nc});
                    
                    if(nr == n && nc == m)
                        return;
                }
            }
        }
    }
    static boolean canGo(int r, int c){
        return r>=0&&r<n&&c>=0&&c<m&&checked[r][c]==0&&arr[r][c]==1;
    }
}