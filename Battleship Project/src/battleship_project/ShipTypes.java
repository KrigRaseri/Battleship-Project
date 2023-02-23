package battleship_project;

public class ShipTypes extends GameBoard{

    enum Rows {
        A(0), B(1), C(2),
        D(3), E(4), F(5),
        G(6), H(7), I(8), J(9);

        final int rowNum;

        Rows(int rowNum) {
            this.rowNum = rowNum;
        }
    }

    enum Ships {
        AIRCRAFT_CARRIER("Aircraft Carrier", 5),
        BATTLESHIP("Battleship", 4),
        SUBMARINE("Submarine", 3),
        CRUISER("Cruiser", 3),
        DESTROYER("Destroyer", 2);

        final String name;
        final int size;


        Ships(String name, int size) {
            this.name = name;
            this.size = size;
        }
    }


    public void placeShip(String[][] arr, Ships ship) {
        while (true) {
            System.out.printf("Enter the coordinates of the %s (%d cells):\n", ship.name, ship.size);
            int[] cordArray = Util.toCordArray();
            int x1 = cordArray[0];
            int y1 = cordArray[1] - 1;
            int x2 = cordArray[2];
            int y2 = cordArray[3] - 1;

            if (y1 > y2) {
                int temp;
                temp = y1;
                y1 = y2;
                y2 = temp;
            }

            if (x1 > x2) {
                int temp;
                temp = x1;
                x1 = x2;
                x2 = temp;
            }

            if (Util.checkInput(ship, x1, y1, x2, y2)) {
                continue;
            }
            if (GameBoard.proxCheck(GameBoard.getArr(), x1, y1) || GameBoard.proxCheck(GameBoard.getArr(), x2, y2)) {
                continue;
            }


            //Horizontal
            if (x1 == x2) {
                arr[x1][y1] = " O";
                for (int i = 0; i < ship.size; i++) {
                    arr[x2][y2 - i] = " O";
                }
                printBoard(GameBoard.arr);
                break;
            }

            //Vertical
            else {
                arr[x1][y1] = " O";
                for (int i = 0; i < ship.size; i++) {
                    arr[x2 - i][y2] = " O";
                }
                printBoard(GameBoard.arr);
                break;
            }
        }
    }
}
