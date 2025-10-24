class Solution {
    static int[] tgt,bTgt,answer;
    static int n,maxWin;
    static int[][] result;
    public int[] solution(int[][] dice) {
        n = dice.length;
        tgt = new int[n/2];
        bTgt=new int[n/2];
        answer = new int[n/2];
        
        comb(0,0,dice);
        
        return answer;
    }
    static void comb(int num, int idx,int[][] dice){
        if(idx==n/2){
            result=new int[2][n*100+1];
            
            int bIdx=0;
            for(int i=0; i<n; i++){
                boolean hasA=false;
                for(int j=0; j<n/2; j++){
                    if(tgt[j]==i){
                        hasA=true;
                        break;
                    }
                }
                
                if(!hasA){
                    bTgt[bIdx++]=i;
                }
            }
            
            calcA(0,0,dice);
            calcB(0,0,dice);
            calcReuslt();
            
            return;
        }
        
        if(num==n)
            return;
        
        tgt[idx]=num;
        
        comb(num+1,idx+1,dice);
        comb(num+1,idx,dice);
    }
    static void calcReuslt(){
        int win=0;
        for(int i=1; i<=n*100; i++){
            for(int j=0; j<i; j++){
                win+= result[0][i]*result[1][j];
            }
        }
        
        if(win>maxWin){
            maxWin=win;
            
            for(int i=0; i<n/2; i++){
                answer[i]=tgt[i]+1;
            }
        }
    }
    
    static void calcA(int idx, int score,int[][] dice){
        if(idx==n/2){
            result[0][score]++;
            return;
        }
        
        for(int i=0; i<6; i++){
            calcA(idx+1,score+dice[tgt[idx]][i],dice);
        }
    }
    
      static void calcB(int idx, int score,int[][] dice){
        if(idx==n/2){
            result[1][score]++;
            return;
        }
        
        for(int i=0; i<6; i++){
            calcB(idx+1,score+dice[bTgt[idx]][i],dice);
        }
    }
}