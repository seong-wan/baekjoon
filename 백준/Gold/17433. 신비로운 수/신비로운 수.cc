#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#define FASTIO cin.tie(NULL); cout.tie(NULL); ios::sync_with_stdio(false);

using namespace std;
int T, N;
vector<int> Vec;
vector<int> Sub;
int Answer;

void Init() {
	Vec.clear();
	Sub.clear();
	Answer = 0;
}

int GCD(int N, int M) {
	while (1) {
		int R = N % M;
		if (R == 0) {
			return M;
		}
		N = M;
		M = R;
	};
}

void Settings() {
	sort(Vec.begin(), Vec.end());
	if (Vec.size() > 1) {
		for (int i = 0; i < ((int)Vec.size() - 1); i++) {
			if (Vec[i + 1] - Vec[i] > 0) {
				Sub.push_back(Vec[i + 1] - Vec[i]);
			}
		}
	}
	sort(Sub.begin(), Sub.end());
	Sub.erase(unique(Sub.begin(), Sub.end()), Sub.end());
	if (Sub.size() == 1) {
		Answer = Sub[0];
	}
	else if (Sub.size() > 1) {
		Answer = GCD(Sub[1], Sub[0]);
		for (int i = 2; i < Sub.size(); i++) {
			Answer = GCD(Sub[i], Answer);
		}
	}
}

void Find_Answer() {
	if (Answer == 0) {
		cout << "INFINITY\n";
	}
	else {
		cout << Answer << "\n";
	}
}

void Input() {
	cin >> T;
	while (T--) {
		Init();
		cin >> N;
		for (int i = 0; i < N; i++) {
			int I;
			cin >> I;
			Vec.push_back(I);
		}
		Settings();
		Find_Answer();
	};
}

int main() {
	FASTIO
	Input();

	return 0;
}