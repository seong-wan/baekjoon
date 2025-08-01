# [Silver II] 달팽이 리스트 - 17827 

[문제 링크](https://www.acmicpc.net/problem/17827) 

### 성능 요약

메모리: 52068 KB, 시간: 564 ms

### 분류

수학, 구현

### 제출 일자

2025년 7월 23일 05:51:49

### 문제 설명

<p>영진이는 달팽이를 좋아한다. 달팽이를 너무너무 좋아하기 때문에 특정한 모양의 단방향 연결리스트에 달팽이 리스트라는 이름을 붙여주었다.</p>

<p>일반적인 선형 단방향 연결리스트의 각 노드 번호를 연결된 순서대로 1, 2, ..., <em>N</em>이라 하자. 이때 <em>N</em>번 노드는 아무 노드도 가리키지 않는데, 여기서 <em>N</em>번 노드가 1번 노드를 제외한 임의의 노드를 가리켜 사이클을 이루게 되는 리스트를 달팽이 리스트라고 한다. 달팽이 리스트는 각 노드당 하나의 정수를 저장한다.</p>

<p>즉, 달팽이 리스트는 다음과 같이 생긴 연결리스트이다. 노드 안의 수는 저장된 값을 뜻한다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/9c987950-3731-49c1-bab6-8e545e8d54bc/-/preview/" style="height: 426px; width: 560px;"></p>

<p>"달팽아 달팽아 1번 노드부터 한 칸씩 총 <em>K</em>번 이동해 도착한 노드엔 어떤 값이 있을까?"</p>

<p>영진이는 어두운 방 안에서 달팽이 리스트 하나를 쓱쓱 그리더니, 같은 말을 <em>K</em>만 바꿔가며 계속 중얼거렸다.</p>

<p>영진이의 상태가 심상치 않아 보인다... 상황이 더 악화되기 전에 영진이의 모든 질문에 대답해주도록 하자.</p>

### 입력 

 <p>첫째 줄에 노드의 개수 <em>N</em>(2 ≤ <em>N</em> ≤ 200,000), 질문의 횟수 <em>M</em>(1 ≤<em> M</em> ≤ 200,000), <em>N</em>번 노드가 가리키는 노드의 번호 <em>V</em>(2 ≤ <em>V</em> ≤ <em>N</em>)가 공백으로 구분되어 주어진다.</p>

<p>둘째 줄에 <em>N</em>개의 정수 <em>C<sub>1</sub>, C<sub>2</sub>, …, C<sub>N</sub></em>이 공백으로 구분되어 주어진다. 이때 <em>C<sub>i</sub></em>는 <em>i</em>번 노드에 저장된 값을 뜻한다. (1 ≤ <em>C<sub>i</sub></em> ≤ 1,000,000)</p>

<p>셋째 줄부터 <em>M</em>개의 줄에 걸쳐 각 질문에 해당하는 <em>K</em>(1 ≤ <em>K</em> ≤ 10<sup>9</sup>)가 주어진다.</p>

### 출력 

 <p><em>M</em>개의 줄에 걸쳐 각 질문의 답을 출력한다.</p>

