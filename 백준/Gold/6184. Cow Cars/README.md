# [Gold III] Cow Cars - 6184 

[문제 링크](https://www.acmicpc.net/problem/6184) 

### 성능 요약

메모리: 18616 KB, 시간: 168 ms

### 분류

그리디 알고리즘, 정렬

### 제출 일자

2025년 11월 24일 13:22:29

### 문제 설명

<p>N (1 <= N <= 50,000) cows conveniently numbered 1..N are driving in separate cars along a highway in Cowtopia. Cow i can drive in any of M different high lanes (1 <= M <= N) and can travel at a maximum speed of S_i (1 <= S_i <= 1,000,000) km/hour.</p>

<p>After their other bad driving experience, the cows hate collisions and take extraordinary measures to avoid them. On this highway, cow i reduces its speed by D (0 <= D <= 5,000) km/hour for each cow in front of it on the highway (though never below 0 km/hour). Thus, if there are K cows in front of cow i, the cow will travel at a speed of max[S_i - D * K, 0]. While a cow might actually travel faster than a cow directly in front of it, the cows are spaced far enough apart so crashes will not occur once cows slow down as described,</p>

<p>Cowtopia has a minimum speed law which requires everyone on the highway to travel at a a minimum speed of L (1 <= L <= 1,000,000) km/hour so sometimes some of the cows will be unable to take the highway if they follow the rules above. Write a program that will find the maximum number of cows that can drive on the highway while obeying the minimum speed limit law.</p>

### 입력 

 <ul>
	<li>Line 1: Four space-separated integers: N, M, D, and L</li>
	<li>Lines 2..N+1: Line i+1 describes cow i's initial speed with a single integer: S_i</li>
</ul>

<p> </p>

### 출력 

 <ul>
	<li>Line 1: A single integer representing the maximum number of cows that can use the highway</li>
</ul>

