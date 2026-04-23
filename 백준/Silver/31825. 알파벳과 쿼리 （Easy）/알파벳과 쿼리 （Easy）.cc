#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int N, Q;
    cin >> N >> Q;

    string input;
    cin >> input;

    int query, start, end;
    for(int i = 0; i < Q; i++)
    {
        cin >> query >> start >> end;

        if(query == 1)
        {
            int result = 0;
            char prev = ' ';
            for(int idx = start-1; idx < end; idx++)
            {
                if(input[idx] != prev)
                {
                    prev = input[idx];
                    result++;
                }
            }   
            cout << result << "\n";
        }
        else
        {
            for(int idx = start-1; idx < end; idx++)
                input[idx] = (input[idx] - 'A' + 1) % 26 + 'A';
        }
    }
}