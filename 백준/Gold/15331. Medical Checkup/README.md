# [Gold IV] Medical Checkup - 15331 

[문제 링크](https://www.acmicpc.net/problem/15331) 

### 성능 요약

메모리: 26004 KB, 시간: 276 ms

### 분류

수학, 그리디 알고리즘

### 제출 일자

2026년 3월 8일 17:42:04

### 문제 설명

<p>Students of the university have to go for a medical checkup, consisting of lots of checkup items, numbered 1, 2, 3, and so on.</p>

<p>Students are now forming a long queue, waiting for the checkup to start. Students are also numbered 1, 2, 3, and so on, from the top of the queue. They have to undergo checkup items in the order of the item numbers, not skipping any of them nor changing the order. The order of students should not be changed either.</p>

<p>Multiple checkup items can be carried out in parallel, but each item can be carried out for only one student at a time. Students have to wait in queues of their next checkup items until all the others before them finish.</p>

<p>Each of the students is associated with an integer value called health condition. For a student with the health condition h, it takes h minutes to finish each of the checkup items. You may assume that no interval is needed between two students on the same checkup item or two checkup items for a single student.</p>

<p>Your task is to find the items students are being checked up or waiting for at a specified time t.</p>

### 입력 

 <p>The input consists of a single test case in the following format.</p>

<pre>n t
h<sub>1</sub>
.
.
.
h<sub>n</sub></pre>

<p>n and t are integers. n is the number of the students (1 ≤ n ≤ 10<sup>5</sup>). t specifies the time of our concern (0 ≤ t ≤ 10<sup>9</sup>). For each i, the integer h<sub>i</sub> is the health condition of student i (1 ≤ h<sub>i</sub> ≤ 10<sup>9</sup>).</p>

### 출력 

 <p>Output n lines each containing a single integer. The i-th line should contain the checkup item number of the item which the student i is being checked up or is waiting for, at (t+ 0.5) minutes after the checkup starts. You may assume that all the students are yet to finish some of the checkup items at that moment.</p>

