package battleship_project;

public class GameBoard {
    String[][] arr = new String[10][10];

    public void createBoard(String[][] array) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                array[i][j] = " ~";
            }
        }
    }


    public void printBoard(String[][] array) {
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
}
