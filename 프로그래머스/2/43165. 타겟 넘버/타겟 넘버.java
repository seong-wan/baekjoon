class Solution {
    static int[] arr;
    static int t;
    static int answer;
    public int solution(int[] numbers, int target) {
        arr = numbers;
        t= target;
        
        dfs(0,0);
        
        return answer;
    }
    static void dfs(int sum, int idx){
        //마지막 num까지 다 처리했을 때
        if(idx == arr.length){
            if(sum==t)
                answer++;
            
            return;
        }
        
        dfs(sum+arr[idx],idx+1);
        dfs(sum-arr[idx],idx+1);
    }
}