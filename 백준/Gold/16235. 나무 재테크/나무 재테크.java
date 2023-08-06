import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Tree {// 주어지는 나무의 정보 x,y,z에서 x,y가 (x,y)좌표가 아니라 그냥 r,c임 ㅡㅡ
	int r, c, age = 1;
	boolean live = true;

	public Tree(int r, int c) {
		this.r = r;
		this.c = c;
	}

	public Tree(int r, int c, int age) {
		this.r = r;
		this.c = c;
		this.age = age;
	}

}// 나무의 정보를 담고 있는 클래스

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int[][] map, sup;// 현재 양분 정보를 담을 배열과 추가할 양분 정보를 담을 배열
	static List<Tree> treeinfo = new LinkedList<Tree>();// 나무 정보를 담을 리스트
	static int N, M, K;
	static int[] dy = { 1, 1, 0, -1, -1, -1, 0, 1 };
	static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };// 8방향 탐색을 위해(아래에서부터 반시계 방향)
	static Tree cur;
	static List<Tree> newtree = new ArrayList<Tree>();

	public static void main(String[] args) throws IOException {
		start();
		for (int i = 0; i < K; i++) {
			spring();
			summer();
			fall();
			winter();
		} // K년 만큼 진행

		System.out.println(treeinfo.size());// 남아 있는 나무의 개수

	}

	static void start() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		sup = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = 5;

			}
		} // 처음 맵의 모든 곳이 양분 5로 시작

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				sup[i][j] = Integer.parseInt(st.nextToken());

			}

		} // 겨울에 추가할 양분 정보를 담음
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			treeinfo.add(new Tree(r, c, age));
		} // 나무 정보들을 담음

	}

	static void spring() {
//		treeinfo.sort(new Comparator<Tree>() {
//
//			@Override
//			public int compare(Tree t1, Tree t2) {
//
//				return Integer.compare(t1.age, t2.age);
//			}
//		});// 나이 값을 기준으로 오름차 순으로 나무들 정렬(나이가 적은 나무부터 양분을 주기 위해)X
		// 정렬할 필요 X(입력 나무들은 서로 다른 좌표이기 때문에 새로 생긴 나무들만 신경써서 리스트 앞으로 넣어주면 됨) 시간 초과 원인
		for (Iterator<Tree> it = treeinfo.iterator(); it.hasNext();) {
			cur = it.next();
			int r = cur.r - 1;
			int c = cur.c - 1;
			int age = cur.age;

			if (age <= map[r][c]) {
				map[r][c] -= age;
				cur.age += 1;// 양분을 먹고 나이가 1증가
			}

			else
				cur.live = false;
		} // 나무들의 나이만큼 양분을 먹고 먹을 양분이 없으면 사망

	}

	static void summer() {
//		for (Integer i : die_tree_i) {
//			map[treeinfo.get(i).r - 1][treeinfo.get(i).c - 1] += treeinfo.get(i).age / 2;
//			treeinfo.remove(i);
//		}
//		die_tree_i.clear();

		for (Iterator<Tree> it = treeinfo.iterator(); it.hasNext();) {
			// 리스트에서 값을 삭제할 때 뒤에서부터 삭제하면 사이즈가 바뀌어도 정상적으로 삭제
			cur = it.next();
			if (!cur.live) {
				map[cur.r - 1][cur.c - 1] += cur.age / 2;
				it.remove();
			}
		}

	}

	static void fall() {
		for (Iterator<Tree> it = treeinfo.iterator(); it.hasNext();) {
//		for (int i = 0; i < treeinfo.size(); i++) {
			cur = it.next();
			if (cur.age % 5 == 0) {// 나이가 5의 배수인지 체크
				int r = cur.r - 1;
				int c = cur.c - 1;
				for (int j = 0; j < 8; j++) {
					if (r + dy[j] >= 0 && r + dy[j] <= N - 1 && c + dx[j] >= 0 && c + dx[j] <= N - 1) {
						// 범위 안인지 체크
//						treeinfo.add(0, new Tree(r + dy[j] + 1, c + dx[j] + 1));// 조건이 맞다면 해당 칸에 나이가 1인 나무 추가
//						i++;// 새로 생성된 나무가 리스트의 앞에 들어가기 때문에 체크한 나무를 계속 체크하지 않게 하기 위해
						// 리스트 값이 계속 뒤로 옮겨야 해서 시간 더 걸림(나무 값을 리스트 뒤에서 부터 체크함으로 해결)
						newtree.add(new Tree(r + dy[j] + 1, c + dx[j] + 1));// 조건이 맞다면 해당 칸에 나이가 1인 나무 추가
					}
				}
			}
//			if (cur.age < 5 && i > M - 1)
//				break; // 입력 값 뒤의 나무들은 나이가 내림차순으로 정렬되므로 5이하인 나무가 1개 나오는 순간부터 더 이상 순회할 필요 X

		}
		treeinfo.addAll(0, newtree);
		newtree.clear();
	}

	static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] += sup[i][j];

			}

		} // 추가할 양분만큼 각 칸에 양분 추가
	}

}
