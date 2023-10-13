import java.util.*;

class Solution {
    static Deque<Integer> stack = new ArrayDeque<>();
    public int solution(int[] ingredient) {
       int answer = 0;
        int size=ingredient.length;
        for(int i=0;i<size;i++){
            if(stack.size()<3)
            stack.push(ingredient[i]);
            else
              if(ingredient[i]==1){
                  int first = stack.pop();
                  int second = stack.pop();
                  int third = stack.pop();
                  if(first==3&&second==2&&third==1)
                      answer++;
                  else
                  {stack.push(third);
                  stack.push(second);
                  stack.push(first);
                  stack.push(1);} 
              }else stack.push(ingredient[i]);
        }
        
        
   
        return answer;
    }
}