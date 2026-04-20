#include <bits/stdc++.h>
using namespace std;

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int N;
    cin >> N;

    int ct[51]; fill(ct, ct + 51, 0);
    for (int i = 0, n; i < N; i++)
        cin >> n, ct[n]++;

    for (int i = 50; i >= 0; i--)
        if (ct[i] == i){
            cout << i;
            return 0;
        }

    cout << -1;
}