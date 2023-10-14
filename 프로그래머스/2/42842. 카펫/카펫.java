class Solution {
    public int[] solution(int brown, int yellow) {
        for(int i=1;i<=yellow;i++){
            if(yellow%i==0){
               int hr=yellow/i+2;
               int vr=i+2;
                if((hr)*(vr)==brown+yellow){
                    int[] answer = {hr,vr};
                     return answer;
                }
            }
        }
        return null;  }
}