# [Gold IV] Empty Stalls - 9567 

[문제 링크](https://www.acmicpc.net/problem/9567) 

### 성능 요약

메모리: 35868 KB, 시간: 484 ms

### 분류

구현, 시뮬레이션

### 제출 일자

2025년 9월 23일 02:19:31

### 문제 설명

<p>Farmer John's new barn consists of a huge circle of N stalls (2 <= N <= 3,000,000), numbered 0..N-1, with stall N-1 being adjacent to stall 0.</p><p>At the end of each day, FJ's cows arrive back at the barn one by one, each with a preferred stall they would like to occupy.  However, if a cow's preferred stall is already occupied by another cow, she scans forward sequentially from this stall until she finds the first unoccupied stall, which she then claims.  If she scans past stall N-1, she continues scanning from stall 0.</p><p>Given the preferred stall of each cow, please determine the smallest index of a stall that remains unoccupied after all the cows have returned to the barn.  Notice that the answer to this question does not depend on the order in which the cows return to the barn.</p><p>In order to avoid issues with reading huge amounts of input, the input to this problem is specified in a concise format using K lines (1 <= K <= 10,000) each of the form:</p><p>X Y A B</p><p>One of these lines specifies the preferred stall for XY total cows: X cows prefer each of the stalls f(1) .. f(Y), where f(i) = (Ai + B) mod N.  The values of A and B lie in the range 0...1,000,000,000.</p><p>Do not forget the standard memory limit of 64MB for all problems.</p>

### 입력 

 <ul><li>Line 1: Two space-separated integers: N and K.</li><li>Lines 2..1+K: Each line contains integers X Y A B, interpreted as above.  The total number of cows specified by all these lines will be at most N-1.  Cows can be added to the same stall by several of these lines.</li></ul>

### 출력 

 <ul><li>Line 1: The minimum index of an unoccupied stall.</li></ul>

