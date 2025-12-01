# [Gold IV] Surf - 11610 

[문제 링크](https://www.acmicpc.net/problem/11610) 

### 성능 요약

메모리: 72468 KB, 시간: 364 ms

### 분류

다이나믹 프로그래밍

### 제출 일자

2025년 12월 2일 04:17:45

### 문제 설명

<p>Now that you’ve come to Florida and taken up surfing, you love it! Of course, you’ve realized that if you take a particular wave, even if it’s very fun, you may miss another wave that’s just about to come that’s even more fun. Luckily, you’ve gotten excellent data for each wave that is going to come: you’ll know exactly when it will come, how many fun points you’ll earn if you take it, and how much time you’ll have to wait before taking another wave. (The wait is due to the fact that the wave itself takes some time to ride and then you have to paddle back out to where the waves are crashing.) Obviously, given a list of waves, your goal will be to maximize the amount of fun you could have.</p>

<p>Consider, for example, the following list of waves:</p>

<table class="table table-bordered" style="width:30%">
	<thead>
		<tr>
			<th>Minute</th>
			<th>Fun points</th>
			<th>Wait time</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>2</td>
			<td>80</td>
			<td>9</td>
		</tr>
		<tr>
			<td>8</td>
			<td>50</td>
			<td>2</td>
		</tr>
		<tr>
			<td>10</td>
			<td>40</td>
			<td>2</td>
		</tr>
		<tr>
			<td>13</td>
			<td>20</td>
			<td>5</td>
		</tr>
	</tbody>
</table>

<p>In this example, you could take the waves at times 8, 10 and 13 for a total of 110 fun points. If you take the wave at time 2, you can’t ride another wave until time 11, at which point only 20 fun points are left for the wave at time 13, leaving you with a total of 100 fun points. Thus, for this input, the correct answer (maximal number of fun points) is 110.</p>

<p>Given a complete listing of waves for the day, determine the maximum number of fun points you could earn.</p>

### 입력 

 <p>The first line of input contains a single integer n (1 ≤ n ≤ 300,000), representing the total number of waves for the day. The ith line (1 ≤ i ≤ n) that follows will contain three space separated integers: m<sub>i</sub>, f<sub>i</sub>, and w<sub>i</sub>, (1 ≤ m<sub>i</sub>, f<sub>i</sub>, w<sub>i</sub> ≤ 10<sup>6</sup>), representing the time, fun points, and wait time of the ith wave, respectively. You can ride another wave occurring at exactly time m<sub>i</sub> + w<sub>i</sub> after taking the ith wave. It is guaranteed that no two waves occur at the same time. The waves may not be listed in chronological order.</p>

### 출력 

 <p>Print, on a single line, a single integer indicating the maximum amount of fun points you can get riding waves.</p>

