#include <bits/stdc++.h>
using namespace std;

int main()
{   int L,A,B,C,D;
    cin>>L;
    cin>>A;
    cin>>B;
    cin>>C;
    cin>>D;

    int korean = A%C!=0?A/C+1:A/C;
    int math = B%D!=0?B/D+1:B/D;
    
    cout<<L-max(korean,math);
    ios_base::sync_with_stdio(0); 
    cin.tie(0);
    
    return 0;
}