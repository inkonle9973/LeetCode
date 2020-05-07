package Days.March;

public class gameOfLife6 {
    public static void game(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[] dx = {0, 0, 1, -1, -1, -1, 1, 1};
        int[] dy = {1, -1, 0, 0, -1, 1,-1,1};
        int[][] nu = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (number(dx, dy, board, i, j, m, n) < 2 || number(dx, dy, board, i, j, m, n) > 3) {
                    nu[i][j] = 0;
                } else if (number(dx, dy, board, i, j, m, n) == 3) {
                    nu[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = nu[i][j];
            }
        }

    }

    public static int number(int[] dx, int[] dy, int[][] board, int x, int y, int m, int n) {
        int ans = 0;
        for (int k = 0; k < 8; k++) {
            int newX = x + dx[k];
            int newY = y + dy[k];
            if (newX < 0 || newX >= m || newY < 0 || newY >= n) {
                continue;
            }
            ans = board[newX][newY] == 1 ? ans+1 : ans;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] board = new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();

        }
        System.out.println();

        System.out.println();
        game(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();

        }
    }
}
