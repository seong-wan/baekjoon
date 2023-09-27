import java.util.PriorityQueue;
class Solution {
    
    
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();
        PriorityQueue<Integer> pq2 = new PriorityQueue<>((a,b) -> b-a);
        for( int i = 0; i < operations.length; i++){
            String order = operations[i];
            String[] orders = order.split(" ");
            if( orders[0].equals("I")){ // I면 큐에 삽입
                pq1.offer(Integer.parseInt(orders[1]));
            }else{
                if(orders[1].equals("-1")){ // -1이면 최솟값 삭제
                    pq1.poll();
                }else{ // 최댓값 삭제
                    pq2.addAll(pq1);
                    pq2.poll();
                    pq1.clear();
                    pq1.addAll(pq2);
                    pq2.clear();
                }
            }
        }
        if( pq1.isEmpty()){
            answer[0] = 0;
            answer[1] = 0;
        }else{
            pq2.addAll(pq1);
            answer[0] = pq2.poll();
            answer[1] = pq1.poll();
        }
        
        return answer;
    }
}