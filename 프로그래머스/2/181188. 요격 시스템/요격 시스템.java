import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        int answer = 1;
        Arrays.sort(targets,(e1,e2)->e1[1]==e2[1]?e1[0]-e2[0]:e1[1]-e2[1]);
        //끝나는 점 기준으로 오름차순 정렬 같다면 시작 점을 오름차순으로 정렬
       int size = targets.length;
        int min=targets[0][0];
        int max = targets[0][1];
        for(int i=1;i<size;i++){
         if(targets[i][0]<=min ) continue;
          else
              if(targets[i][0]<max)
                  min =targets[i][0];
              else
              {answer++; 
              min=targets[i][0];
               max=targets[i][1];} 
        }
        
      
        return answer;
    }
}