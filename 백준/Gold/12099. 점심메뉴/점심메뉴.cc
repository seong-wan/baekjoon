#include <bits/stdc++.h>
using namespace std;
int n, q, u, v, x, y;
using pii = pair<int, int>;
pii tast[100001];
 
int main() {
    ios::sync_with_stdio(0); cin.tie(0);
    cin >> n >> q;
    for (int i = 0; i < n; i++) {
        cin >> tast[i].first >> tast[i].second;
    }
    sort(tast, tast + n);
    while (q--) {
        cin >> u >> v >> x >> y;
        int cnt = 0;
        int s = lower_bound(tast, tast + n, pii(u, -1e9)) - tast;
        int e = upper_bound(tast, tast + n, pii(v, 1e9)) - tast;
        for (int i = s; i < e; i++) {
            if (tast[i].second >= x && tast[i].second <= y) {
                cnt++;
            }
        }
        cout << cnt << '\n';
    }
}