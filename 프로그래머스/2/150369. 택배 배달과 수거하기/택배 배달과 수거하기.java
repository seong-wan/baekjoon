class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int deliverCnt = 0;
        int pickCnt=0;
        
        for(int i=n-1; i>=0; i--){
            deliverCnt += deliveries[i];
            pickCnt += pickups[i];
            
            while(deliverCnt>0||pickCnt>0){
                answer+=(i+1)*2;
                deliverCnt-=cap;
                pickCnt-=cap;
            }
        }
        
        return answer;
    }
}