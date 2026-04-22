# [Gold V] Rank - 9846 

[문제 링크](https://www.acmicpc.net/problem/9846) 

### 성능 요약

메모리: 14684 KB, 시간: 112 ms

### 분류

그래프 이론, 방향 비순환 그래프, 위상 정렬

### 제출 일자

2026년 4월 23일 01:15:48

### 문제 설명

<p>In a new sports discipline called Triball proposed for the Youth Olympic Games in Singapore, a set of k players {1, 2, . . . , k} are involved (where k < 1000). In every match, 3 players are selected and the result of the match is either 1 winner or 1 loser. Given the result of n matches (where n < 10000), our task is to give a ranking list of the players.</p>

<p>Precisely, the i<sup>th</sup> match involves three distinct players p<sub>i1</sub>, p<sub>i2</sub>, p<sub>i3</sub> ∈ {1, 2, . . . , k}. The result of the i th match is represented by p<sub>i1</sub> > p<sub>i2</sub>, p<sub>i3</sub> (or p<sub>i2</sub>, p<sub>i3</sub> > p<sub>i1</sub>) where p<sub>i1</sub> is the winner (or loser) of the match. The result p<sub>i1</sub> > p<sub>i2</sub>, p<sub>i3</sub> implies that p<sub>i1</sub> is better than p<sub>i2</sub> and that p<sub>i1</sub> is better than p<sub>i3</sub>. Similarly, the result p<sub>i2</sub>, p<sub>i3</sub> > p<sub>i1</sub> implies that p<sub>i2</sub> is better than p<sub>i1</sub> and that p<sub>i3</sub> is better than p<sub>i1</sub>. Our aim is to find the permutation of the k players so that a better player always comes before his opponent, considering all given matches. If we cannot reach our aim and such a permutation does not exist, we output 0.</p>

### 입력 

 <p>The input is as follows. The first line contains two integers k and n separated by a space character, where 1 ≤ k ≤ 1000, 1 ≤ n ≤ 10000. The number k is the number of players, denoted by numbers from 1 to k. The remaining n lines contain n results in the form of either p<sub>i1</sub> > p<sub>i2</sub>, p<sub>i3</sub> or p<sub>i2</sub>, p<sub>i3</sub> > p<sub>i1</sub>.</p>

### 출력 

 <p>If there is a valid ranking of the players, the output contains k integers, separated by a space character, representing the ordering of the players. Otherwise, the output contains one integer 0.</p>

