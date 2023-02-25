package battleship_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameBoard {
    public static String[][] arr = new String[10][10];
    public static String[][] fog = new String[10][10];

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void fire(String[][] map, String[][] fog, BufferedReader reader) {
        ShipTypes st = new ShipTypes();
        while (!st.isWin(ShipTypes.shipList)) {
            try {
                String shotCord = reader.readLine().trim().toUpperCase();
                int x1 = ShipTypes.Rows.valueOf(shotCord.substring(0, 1)).rowNum;
                int y1 = Integer.parseInt(shotCord.substring(1)) - 1;

                if (map[x1][y1].equals(" X")) {
                    System.out.println("You hit a ship! Try again:");
                    fogOfWar(fog);
                    continue;
                }

                if (map[x1][y1].equals(" O")) {
                    map[x1][y1] = " X";
                    fog[x1][y1] = " X";

                    st.shipHP(ShipTypes.shipList, x1, y1);

                    System.out.println("You hit a ship! Try again:");
                } else {
                    map[x1][y1] = " M";
                    fog[x1][y1] = " M";
                    System.out.println("You missed. Try again:");
                }

                fogOfWar(fog);
                System.out.println();

            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ee) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    public static void fogOfWar(String[][] array) {
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
