import java.util.*;

class Solution {
    static class Status{
        int num;
        int[] sendPresent; 
        int sendCount;
        int recieveCount;
    }
    static int[] presentCoefficient;
    static int[] recievePresent;
    static Map<String,Status> map = new HashMap<>();
    static StringTokenizer st;
    public int solution(String[] friends, String[] gifts) {
        presentCoefficient=new int[friends.length];
        recievePresent=new int[friends.length];
        
        for(int i=0; i<friends.length; i++){
            String name = friends[i];
            map.put(name,new Status());
            Status status = map.get(name);
            
            status.num=i;
            status.sendPresent=new int[friends.length];
        }
        
        for(int i=0; i<gifts.length; i++){
            st=new StringTokenizer(gifts[i]);
            Status fromStatus = map.get(st.nextToken());
            Status toStatus = map.get(st.nextToken());
            
            fromStatus.sendPresent[toStatus.num]++;
            fromStatus.sendCount++;
            toStatus.recieveCount++;
        }
        
      for(String name : map.keySet()){
          Status status = map.get(name);
          presentCoefficient[status.num] = status.sendCount - status.recieveCount;
      }
        
        for(int i=0; i<friends.length; i++){
            for(int j=i+1; j<friends.length; j++){
                String a = friends[i];
                String b = friends[j];
                Status aStatus = map.get(a);
                Status bStatus = map.get(b);
                
                int aSendCount = aStatus.sendPresent[j];
                int bSendCount = bStatus.sendPresent[i];
                
                if(aSendCount>bSendCount)
                    recievePresent[i]++;
                else if(bSendCount>aSendCount)
                    recievePresent[j]++;
                else{
                    if(presentCoefficient[i]>presentCoefficient[j])
                        recievePresent[i]++;
                    else if(presentCoefficient[j]>presentCoefficient[i])
                        recievePresent[j]++;
                }
            }
        }
        int answer=0;
        
        for(int i=0; i<friends.length; i++){
            answer=Math.max(recievePresent[i],answer);
        }
        
        
        return answer;
    }
}