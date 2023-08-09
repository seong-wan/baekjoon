import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numPapers = scanner.nextInt();
        
        boolean[][] canvas = new boolean[101][101]; // 초기화되면 false로 채워진 2차원 배열
        
        for (int i = 0; i < numPapers; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            
            // 색종이가 붙은 영역을 true로 표시
            for (int j = x; j < x + 10; j++) {
                for (int k = y; k < y + 10; k++) {
                    canvas[j][k] = true;
                }
            }
        }
        
        int blackArea = 0;
        
        // 검은 영역의 넓이 계산
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                if (canvas[i][j]) {
                    blackArea++;
                }
            }
        }
        
        System.out.println(blackArea);
    }
}
