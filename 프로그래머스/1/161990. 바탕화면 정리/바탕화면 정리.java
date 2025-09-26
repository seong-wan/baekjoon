//S(r이 제일 작은 값, c가 제일 작은 값)
//E(r이 제일 큰 값+1, c가 제일 큰 값+1)
class Solution {
    public int[] solution(String[] wallpaper) {
        int[] answer = new int[4];
        int minR=50, minC=50, maxR=0, maxC=0;
        
        for(int i=0; i<wallpaper.length; i++){
            for(int j =0; j<wallpaper[i].length(); j++){
                if(wallpaper[i].charAt(j)=='#'){
                    minR=Math.min(minR,i);
                    minC=Math.min(minC,j);
                    maxR=Math.max(maxR,i);
                    maxC=Math.max(maxC,j);
                }
            }
        }
        
        answer[0]=minR;
        answer[1]=minC;
        answer[2]=maxR+1;
        answer[3]=maxC+1;
          
        return answer;
    }
}