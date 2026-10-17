import java.util.*;

class Solution {
    static int cnt;
    static List<Integer>[] adlist;
    static boolean[] checked;
    static int answer;
    public int solution(int n, int[][] computers) {
        cnt = n;
        adlist = new List[n];
        checked = new boolean[n];
        
        for(int i=0; i<n ; i++){
            adlist[i] = new ArrayList<>();
        }
        
        for(int i=0; i<n ; i++){
            for(int j=i+1; j<n; j++){
                if(computers[i][j]==1){
                    adlist[i].add(j);
                    adlist[j].add(i);
                } 
            }
        }
        
        for(int i=0; i<n ; i++){
            if(!checked[i]){
                answer++;
                bfs(i);
            }
        }
        
        return answer;
    }
    static void bfs(int idx){
        Deque<Integer> queue = new ArrayDeque<>();
        checked[idx]=true;
        queue.add(idx);
        
        while(!queue.isEmpty()){
            int cur = queue.poll();
           
            for(int next: adlist[cur]){
                if(checked[next])
                    continue;
                
                checked[next]=true;
                queue.add(next);
            }
        }
    }
}