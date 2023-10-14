class Solution {
    public int[] solution(int brown, int yellow) {
        int hr=0;
        int vr=0;
        int total= brown+yellow;
        for(int i=1;i<=yellow;i++){
            if(yellow%i==0){
                hr=yellow/i;
                vr=i;
                if((hr+2)*(vr+2)==total){
                    int[] answer = {hr+2,vr+2};
                     return answer;
                }
            }
        }
        return null;
    }
}