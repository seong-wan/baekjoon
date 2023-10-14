class Solution {
    static int answer=Integer.MAX_VALUE,divide,modu,size;
    public int solution(int[] picks, String[] minerals) {
        size=minerals.length;
        divide =size/5;
        modu =size%5;
        if(modu!=0)
           divide++;
        
        dfs(0,0,picks,minerals);
        return answer;
    }
    static void dfs(int cnt,int result,int[] picks,String[] minerals){
     if(picks[0]==0&&picks[1]==0&&picks[2]==0)
         {answer=Math.min(answer,result);
        return;} 
        if(cnt==divide)
        {answer=Math.min(answer,result);
        return;} 
       
        for(int i=0;i<3;i++){
             int temp=0;
            if(picks[i]>0){
                for(int j=cnt*5;j<Math.min((cnt+1)*5,size);j++){
                    if(i==0)
                        temp++;
                    else if(i==1){
                        if(minerals[j].equals("diamond"))
                            temp+=5;
                        else
                            temp++;
                    }
                    else if(i==2){
                        if(minerals[j].equals("diamond"))
                            temp+=25;
                         else if(minerals[j].equals("iron"))
                             temp+=5;
                         else
                             temp++;
                    }
                 }
                picks[i]-=1;
                dfs(cnt+1,result+temp,picks,minerals);
                picks[i]+=1;
            }
        }
    }
}