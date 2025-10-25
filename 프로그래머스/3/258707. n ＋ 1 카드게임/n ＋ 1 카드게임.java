import java.util.*;

class Solution {
    static int n,round=1,cnt,preCnt;
    static Set<Integer> have = new HashSet<>();
    static Set<Integer> remain = new HashSet<>();
    public int solution(int coin, int[] cards) {
        n=cards.length+1;
        int idx=0;
        
        for(; idx<cards.length/3; idx++){
            int need = n-cards[idx];
            if(have.contains(need)){
                have.remove(need);
                cnt++;
            }else
                have.add(cards[idx]);
        }
        
        while(idx<n-1){
         for(int i=0; i<2; i++){
             int temp = cards[idx++];
             int need = n-temp;
             
             if(have.contains(need)&&coin>0){
                 have.remove(need);
                 cnt++;
                 coin--;
             }else if(remain.contains(need)&&coin>=2){
                 remain.remove(need);
                 preCnt++;
             }else
                 remain.add(temp);
         }
            
        //라운드를 넘어갈 수 있는 카드 쌍이 있는 경우
        if(cnt>=1){
         round++;
         cnt--;
         continue;
        }
            
        if(preCnt>=1&&coin>=2){
            round++;
            preCnt--;
            coin-=2;
            continue;
        }
            
        break;
    }
        return round;
    }
}