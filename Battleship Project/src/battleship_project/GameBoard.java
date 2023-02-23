package battleship_project;

public class GameBoard {
    public static String[][] arr = new String[10][10];

    public static String[][] getArr() {
        return arr;
    }

    public static void createBoard(String[][] array) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                array[i][j] = " ~";
            }
        }
    }


    public static void printBoard(String[][] array) {
        char[] ch = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

        System.out.println(" 1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print(ch[i] + "");
            for (int j = 0; j < 10; j++) {
                System.out.print(array[i][j] + "");
            }
            System.out.println();

        }

    }


    public static boolean proxCheck(String[][] board, int x, int y) {

        try {
            if (board[x+1][y].equals(" O") || board[x-1][y].equals(" O")) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                return true;
            } else if (board[x][y+1].equals(" O") || board[x][y-1].equals(" O")) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        return false;
    }
}
