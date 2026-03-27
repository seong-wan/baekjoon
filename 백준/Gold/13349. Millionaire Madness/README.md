# [Gold IV] Millionaire Madness - 13349 

[문제 링크](https://www.acmicpc.net/problem/13349) 

### 성능 요약

메모리: 83252 KB, 시간: 588 ms

### 분류

그래프 이론, 그래프 탐색, 이분 탐색, 최단 경로, 데이크스트라

### 제출 일자

2026년 3월 28일 03:05:21

### 문제 설명

<p>A close friend of yours, a duck with financial problems, has requested your help with a matter that will help him pay off his debts. He is the nephew of an extremely wealthy duck, who has a large vault, filled with mountains of coins. This wealthy duck has a certain coin in his possession which has a lot of sentimental value to him. Usually, it is kept under a protective glass dome on a velvet cushion.</p>

<p>However, during a recent relocating of the coins in the vault, the special coin was accidentally moved into the vault, leading to an extremely stressful situation for your friend’s uncle. Luckily, the coin has recently been located. Unfortunately, it is completely opposite to the entrance to the vault, and due to the mountains of coins inside the vault, actually reaching the coin is no simple task.</p>

<p>He is therefore willing to pay your friend to retrieve this coin, provided that he brings his own equipment to scale the mountains of coins. Your friend has decided he will bring a ladder, but he is still uncertain about its length. While a longer ladder means that he can scale higher cliffs, it also costs more money. He therefore wants to buy the shortest ladder such that he can reach the special coin, so that he has the largest amount of money left to pay off his debts.</p>

<p>The vault can be represented as a rectangular grid of stacks of coins of various heights (in meters), with the entrance at the north west corner (the first height in the input, the entrance to the vault is at this height as well) and the special coin at the south east corner (the last height in the input). Your avian companion has figured out the height of the coins in each of these squares. From a stack of coins he can attempt to climb up or jump down to the stack immediately north, west, south or east of it. Because your friend cannot jump or fly (he is a very special kind of duck that even wears clothes), successfully performing a climb of n meters will require him to bring a ladder of at least n meters. He does not mind jumping down, no matter the height; he just lets gravity do all the work.</p>

### 입력 

 <p>The first line contains two integers: the length M, and the width N of the vault, satisfying 1 ≤ M, N ≤ 1000.</p>

<p>The following M lines each contain N integers. Each integer specifies the height of the pile of coins in the vault at the corresponding position. (The first line describes the north-most stacks from west to east; the last line describes the south-most stacks from west to east). The heights are given in meters and all heights are at least 0 and at most 10<sup>9 </sup>(yes, your friend’s uncle is very rich).</p>

### 출력 

 <p>Output a single line containing a single integer: the length in meters of the shortest ladder that allows you to get from the north west corner to the south east corner.</p>

