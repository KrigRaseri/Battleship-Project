package battleship_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Gameboard class for methods that deal with either creating or altering the game boards.
 *
 * @author Krig Raseri(Pen name).
 * */
public class GameBoard {

    //Player 1 ship map, and fog map to shoot at.
    public static String[][] map = new String[10][10];
    public static String[][] fog = new String[10][10];
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Fire method takes user input of (x, y) coordinates and checks the opposing players map if there is a ship there,
     * if so it marks the original players fog map as an X and the opponents map as an X. The hit is then -1hp for the
     * ship that was hit.
     *
     * @param map Represents the players board.
     * @param fog Represents a hidden map of the opponent that the user uses to shoot at.
     * @param shipList Represents a list of all the users ship objects.
     * */
    public static void fire(String[][] map, String[][] fog, BufferedReader reader, ArrayList<ShipTypes> shipList) {
        ShipTypes st = new ShipTypes();
        try {
            String shotCord = reader.readLine().trim().toUpperCase();
            int x1 = ShipTypes.Rows.valueOf(shotCord.substring(0, 1)).rowNum;
            int y1 = Integer.parseInt(shotCord.substring(1)) - 1;

            if (map[x1][y1].equals(" X")) {
                System.out.println("You hit a ship!");
            }

            if (map[x1][y1].equals(" O")) {
                map[x1][y1] = " X";
                fog[x1][y1] = " X";

                System.out.println("You hit a ship!");
                st.shipHP(shipList, x1, y1);

            } else {
                map[x1][y1] = " M";
                fog[x1][y1] = " M";
                System.out.println("You missed!");
            }
            System.out.println();

        } catch (IOException | IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
        }
    }

    /**
     * Creates the game board (AKA Map).
     *
     * @param array Represents the 2d array to be edited to become the map.
     * */
    public static void createBoard(String[][] array) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                array[i][j] = " ~";
            }
        }
    }

    /**
     * Prints out the game board (AKA Map) with 1-10 on the top for columns, and A-J on the side for rows.
     *
     * @param map Represents the map to be printed. Either map (Player 1), or mapOpp (Player 2's map).
     * */
    public static void printBoard(String[][] map) {
        char[] ch = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

        System.out.println(" 1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print(ch[i] + "");
            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j] + "");
            }
            System.out.println();

        }
    }

    /**
     * Creates the fog map that is a hidden version of the regular map.
     *
     * @param map Represents the 2d array map to be hidden.
     * */
    public static void fogOfWar(String[][] map) {
        char[] ch = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        System.out.println(" 1 2 3 4 5 6 7 8 9 10");

        for (int i = 0; i < 10; i++) {
            System.out.print(ch[i] + "");
            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j] + "");
            }
            System.out.println();
        }
    }

    /**
     * Prints out an in-game view of both the map and fog map. The fog map is printed on top, while the regular map
     * is print on the bottom.
     *
     * @param map Represents the users map with ships on it.
     * @param fog Represents the fog map of the opponent for you to shoot at.
     * */
    public static void printFullField(String[][] map, String[][] fog) {
        System.out.println();
        fogOfWar(fog);
        System.out.println("---------------------");
        printBoard(map);
        System.out.println();
    }


    /**
     * Checks the user inputted corddinates during ship placement to determine if the ship being placed is too close to
     * another ship.
     *
     * @param map Represents the users map with ships on it.
     * @param x is the x coordinate of where the ship is to be placed.
     * @param y is the y coordinate of where the ship is to be placed.
     * */
    public static boolean proxCheck(String[][] map, int x, int y) {
        try {
            if (map[x+1][y].equals(" O") || map[x-1][y].equals(" O")) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                return true;
            } else if (map[x][y+1].equals(" O") || map[x][y-1].equals(" O")) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return false;
    }

    //To convert row letter to a number.
    enum Rows {
        A(0), B(1), C(2),
        D(3), E(4), F(5),
        G(6), H(7), I(8), J(9);

        final int rowNum;

        Rows(int rowNum) {
            this.rowNum = rowNum;
        }
    }
}
