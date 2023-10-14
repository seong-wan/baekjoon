n = int(input())  # 입력값을 받습니다.

count = 0  # 한수의 개수를 카운트합니다.

for i in range(1, n+1):
    if i <= 99:  # 1부터 99까지는 모두 한수입니다.
        count += 1
    else:
        num_str = str(i)  # 숫자를 문자열로 변환합니다.
        d = int(num_str[1]) - int(num_str[0])  # 첫 번째 자리와 두 번째 자리의 차이를 계산합니다.
        is_hansu = True  # 한수인지 판별하는 변수를 초기화합니다.
        for j in range(2, len(num_str)):
            if int(num_str[j]) - int(num_str[j-1]) != d:  # 차이가 같지 않으면 한수가 아닙니다.
                is_hansu = False
                break
        if is_hansu:
            count += 1

print(count)  # 한수의 개수를 출력합니다.