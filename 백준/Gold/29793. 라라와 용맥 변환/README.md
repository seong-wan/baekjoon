# [Gold III] 라라와 용맥 변환 - 29793 

[문제 링크](https://www.acmicpc.net/problem/29793) 

### 성능 요약

메모리: 24056 KB, 시간: 260 ms

### 분류

다이나믹 프로그래밍, 문자열, 브루트포스 알고리즘, 애드 혹, 많은 조건 분기

### 제출 일자

2025년 11월 30일 20:11:45

### 문제 설명

<blockquote overflow="hidden">
<p class="tutorial_pic" figure=""><img alt="" src="https://upload.acmicpc.net/ed8d5a00-9000-4331-803a-8c0aab64500f/-/preview/"></p>

<p class="tutorial">여러분, 그거 알아요?</p>

<p class="tutorial">자연에서 태어난 모든 생명들은 바람과 물로 이루어져 햇빛을 받아 이 땅에 존재한다고 해요. 그래서 땅을 보면 우리가 어떻게 살아야 하는지 알 수 있대요.</p>

<p class="tutorial">바람처럼 열린 마음으로 날아오르고 강처럼 쉬지 않고 앞으로 나아가다가 햇빛처럼 괴로운 마음들을 따뜻하게 달래 주고 산처럼 고요히 머무르다 가면 되는 거래요.</p>

<p class="tutorial">-라라 튜토리얼 스토리 중-<span class="clear"> </span></p>
</blockquote>

<hr>
<p>메이플스토리의 직업 라라는 <strong>용맥 분출</strong>이라는 스킬을 이용해 사냥한다.</p>

<p>라라는 <strong><span style="color:#f39c12;">해</span></strong>, <strong><span style="color:#3498db;">강</span></strong>, <strong><span style="color:#1abc9c;">바람</span></strong>의 세 종류 용맥을 발견할 수 있는데, 용맥 분출을 사용하면 용맥을 <strong>소모</strong>하여 해당 위치에 지속적으로 데미지를 주는 <strong>토지령</strong>이라는 소환수를 소환할 수 있다. 토지령 역시 <strong><span style="color:#f39c12;">해</span></strong>, <strong><span style="color:#3498db;">강</span></strong>, <strong><span style="color:#1abc9c;">바람</span></strong>의 세 종류 토지령이 있고, 소모한 용맥과 같은 종류의 토지령이 소환된다. 각 토지령은 종류별로 한 마리씩만 소환할 수 있고, 토지령을 소환할 때 같은 종류의 토지령이 이미 소환되어 있다면 기존에 소환되어 있던 같은 종류의 토지령은 사라지고 종류별로 가장 마지막에 소환된 토지령만이 남는다.</p>

<p>예를 들어 <span style="color:#f39c12;">해의 토지령</span>과 <span style="color:#3498db;">강의 토지령</span>이 소환되어 있는 상태에서 용맥 분출로 <span style="color:#1abc9c;">바람 용맥</span>을 소모했을 경우, 그 위치에 <span style="color:#1abc9c;">바람의 토지령</span>이 소환된다. 그러나 여기서 <span style="color:#f39c12;">해 용맥</span>을 소모했을 경우, 기존에 있던 <span style="color:#f39c12;">해의 토지령</span>이 사라지고 새로운<span style="color:#f39c12;"> 해의 토지령</span>이 소환된다. <span style="color:#3498db;">강의 토지령</span>과 <span style="color:#1abc9c;">바람의 토지령</span>은 <span style="color:#f39c12;">해의 토지령</span>과는 종류가 다르기 때문에 사라지지 않는다.</p>

<p>용맥 분출에는 한 가지 단점이 있는데, 적절한 종류의 용맥을 소모하지 못한다면 소환했던 토지령이 너무 빠르게 사라져 몬스터에게 충분한 데미지를 주지 못할 수도 있다. 이를 보완하기 위해 라라는 또 다른 스킬인 <strong>용맥 변환</strong>을 가지고 있다. 용맥 변환을 사용할 때마다 원하는 위치의 용맥 하나를 원하는 다른 종류의 용맥으로 변환할 수 있다.</p>

<p>예를 들어 <span style="color:#f39c12;">해 용맥</span>, <span style="color:#f39c12;">해 용맥</span>, <span style="color:#f39c12;">해 용맥</span>이 순서대로 있는 상태에서 두 번째 용맥에 용맥 변환을 사용해 <span style="color:#1abc9c;">바람 용맥</span>으로 변환할 수 있다. 이 경우 용맥의 배치는 <span style="color:#f39c12;">해 용맥</span>, <span style="color:#1abc9c;">바람 용맥</span>, <span style="color:#f39c12;">해 용맥</span>이 된다. 이어서 세 번째 용맥에 용맥 변환을 사용해 <span style="color:#3498db;">강 용맥</span>으로 변환할 경우, 용맥의 배치는 <span style="color:#f39c12;">해 용맥</span>, <span style="color:#1abc9c;">바람 용맥</span>, <span style="color:#3498db;">강 용맥</span>이 된다.</p>

<p>라라는 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container>개의 용맥이 일렬로 있고, 각 용맥마다 체력이 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D43B TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>H</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$H$</span></mjx-container>인 몬스터가 한 마리씩 있는 직선형 맵에서 사냥하려고 한다. 라라는 이 맵의 가장 왼쪽 용맥에서부터 시작하고, 다음과 같은 과정이 반복되어 사냥이 진행된다.</p>

<ol>
	<li>라라가 현재 위치한 곳의 용맥을 소모하여 용맥 분출을 한다. 소모한 용맥에 따른 토지령이 소환되고, 기존에 소환되어 있던 같은 종류의 토지령은 즉시 사라진다.</li>
	<li>현재 소환되어 있는 모든 토지령이 그 위치에 있는 몬스터에게 데미지를 주고, 체력을 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mn class="mjx-n"><mjx-c class="mjx-c31"></mjx-c></mjx-mn></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mn>1</mn></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$1$</span></mjx-container> 감소시킨다. 몬스터의 체력이 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mn class="mjx-n"><mjx-c class="mjx-c30"></mjx-c></mjx-mn></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mn>0</mn></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$0$</span></mjx-container>이 되었다면 그 몬스터는 처치된다.</li>
	<li>라라가 오른쪽으로 인접한 용맥으로 이동한다. 이동할 수 있다면 반드시 이동을 하고, 맵의 오른쪽 끝에 도달해 이동할 용맥이 없다면 현재 토지령이 소환되어 있는 위치의 몬스터가 모두 처치되고, 사냥을 끝마친다.</li>
</ol>

<p>라라는 맵에 있는 <strong>모든</strong> 몬스터를 처치하기 위해 사냥을 시작하기 전 용맥 변환으로 용맥의 종류를 바꿀 것이다. 단, 용맥 변환은 쿨타임이 있는 스킬이기 때문에 빠른 사냥을 위해 최소한의 횟수로 사용할 것이다.</p>

<p>맵의 <strong>모든</strong> 몬스터를 처치하면서 용맥 변환을 최소한으로 사용할 때, 그 횟수를 라라에게 알려주자.</p>

### 입력 

 <p>첫 번째 줄에는 맵에 있는 용맥의 수와 몬스터의 체력을 나타내는 두 정수 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container>, <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D43B TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>H</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$H$</span></mjx-container>가 공백으로 구분되어 주어진다. <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mo class="mjx-n"><mjx-c class="mjx-c28"></mjx-c></mjx-mo><mjx-mn class="mjx-n"><mjx-c class="mjx-c31"></mjx-c></mjx-mn><mjx-mo class="mjx-n" space="4"><mjx-c class="mjx-c2264"></mjx-c></mjx-mo><mjx-mi class="mjx-i" space="4"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi><mjx-mo class="mjx-n"><mjx-c class="mjx-c2C"></mjx-c></mjx-mo><mjx-mi class="mjx-i" space="2"><mjx-c class="mjx-c1D43B TEX-I"></mjx-c></mjx-mi><mjx-mo class="mjx-n" space="4"><mjx-c class="mjx-c2264"></mjx-c></mjx-mo><mjx-mn class="mjx-n" space="4"><mjx-c class="mjx-c31"></mjx-c></mjx-mn><mjx-mstyle><mjx-mspace style="width: 0.167em;"></mjx-mspace></mjx-mstyle><mjx-mn class="mjx-n"><mjx-c class="mjx-c30"></mjx-c><mjx-c class="mjx-c30"></mjx-c><mjx-c class="mjx-c30"></mjx-c></mjx-mn><mjx-mstyle><mjx-mspace style="width: 0.167em;"></mjx-mspace></mjx-mstyle><mjx-mn class="mjx-n"><mjx-c class="mjx-c30"></mjx-c><mjx-c class="mjx-c30"></mjx-c><mjx-c class="mjx-c30"></mjx-c></mjx-mn><mjx-mo class="mjx-n"><mjx-c class="mjx-c29"></mjx-c></mjx-mo></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mo stretchy="false">(</mo><mn>1</mn><mo>≤</mo><mi>N</mi><mo>,</mo><mi>H</mi><mo>≤</mo><mn>1</mn><mstyle scriptlevel="0"><mspace width="0.167em"></mspace></mstyle><mn>000</mn><mstyle scriptlevel="0"><mspace width="0.167em"></mspace></mstyle><mn>000</mn><mo stretchy="false">)</mo></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$(1 \le N, H \le 1\,000\,000)$</span> </mjx-container></p>

<p>두 번째 줄에는 맵에 있는 용맥의 종류를 순서대로 나타내는 길이 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container>의 공백 없는 문자열이 주어진다.</p>

<p>문자열은 <span style="color:#f39c12;"><code>S</code></span>, <span style="color:#3498db;"><code>R</code></span>, <span style="color:#1abc9c;"><code>W</code></span>로만 이루어져 있고, 각각 용맥의 세 종류인 <span style="color:#f39c12;"><strong>해</strong></span>, <strong><span style="color:#3498db;">강</span></strong>, <span style="color:#1abc9c;"><strong>바람</strong></span>을 나타낸다.</p>

### 출력 

 <p>맵의 <strong>모든</strong> 몬스터를 처치하기 위해 사용해야 하는 용맥 변환 횟수의 최솟값을 출력한다.</p>

<p>만약 아무리 많은 용맥 변환을 사용하더라도 맵의 <strong>모든</strong> 몬스터를 처치할 수 없다면 대신 <span style="color:#e74c3c;"><code>-1</code></span>을 출력한다.</p>

