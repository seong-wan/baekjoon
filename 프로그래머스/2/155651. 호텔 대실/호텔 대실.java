import java.util.*;
class Solution {
    static PriorityQueue<Integer> pq = new PriorityQueue<>((e1,e2)->e1-e2);
    static StringTokenizer st;
    public int solution(String[][] book_time) {
        int answer = 1;
        
        int[][] bookTime = new int[book_time.length][2];
        for(int i=0; i<book_time.length; i++){
            st=new StringTokenizer(book_time[i][0],":");
            bookTime[i][0] = Integer.parseInt(st.nextToken())*100
                +Integer.parseInt(st.nextToken());
            
            st=new StringTokenizer(book_time[i][1],":");
            int hour = Integer.parseInt(st.nextToken());
            int min = Integer.parseInt(st.nextToken())+10;
            
            if(min>=60){
                hour++;
                min-=60;
            }
            
            bookTime[i][1] = hour*100+min;
        }
        
        Arrays.sort(bookTime,(e1,e2)->e1[0]-e2[0]);
        
        for(int i=0; i<bookTime.length; i++){
            if(pq.isEmpty()||pq.peek()<=bookTime[i][0]){
                pq.poll();
            }else{
                answer++;
            }
            
             pq.add(bookTime[i][1]);
        }
        
        return answer;
    }
}