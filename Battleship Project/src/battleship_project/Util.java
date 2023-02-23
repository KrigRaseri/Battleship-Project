package battleship_project;

import java.util.Scanner;

public class Util {

    public static boolean checkInput(ShipTypes.Ships ship, int x, int y, int x1, int y1) {
        int size = ship.size;
        if (y == y1 && x1 - x + 1 != size) {
            System.out.printf("Error! Wrong length of the %s! Try again:\n", ship.name);
            return true;
        }

        if (x == x1 && y1 - y + 1 != size) {
            System.out.printf("Error! Wrong length of the %s! Try again:\n", ship.name);
            return true;
        }

        if (x != x1 && y != y1) {
            System.out.println("Error! Wrong ship location! Try again:");
            return true;
        }
        return false;
    }


    public static int[] toCordArray() {
        Scanner sc = new Scanner(System.in);
        String inp = sc.nextLine().toUpperCase();

        String cord1 = inp.substring(0, 3).trim();
        int x1 = ShipTypes.Rows.valueOf(cord1.substring(0, 1)).rowNum;
        int y1 = Integer.parseInt(cord1.substring(1));

        String cord2 = inp.substring(3).trim();
        int x2 = ShipTypes.Rows.valueOf(cord2.substring(0, 1)).rowNum;
        int y2 = Integer.parseInt(cord2.substring(1));

        int[] cords2 = new int[4];
        cords2[0] = x1;
        cords2[1] = y1;
        cords2[2] = x2;
        cords2[3] = y2;

        return cords2;
    }


    public static void startGame() {
        ShipTypes st = new ShipTypes();
        GameBoard.createBoard(GameBoard.arr);
        GameBoard.printBoard(GameBoard.arr);

        st.placeShip(GameBoard.arr, ShipTypes.Ships.AIRCRAFT_CARRIER);
        st.placeShip(GameBoard.arr, ShipTypes.Ships.BATTLESHIP);
        st.placeShip(GameBoard.getArr(), ShipTypes.Ships.SUBMARINE);
        st.placeShip(GameBoard.getArr(), ShipTypes.Ships.CRUISER);
        st.placeShip(GameBoard.getArr(), ShipTypes.Ships.DESTROYER);
    }
}
