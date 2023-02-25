package battleship_project;

import java.util.ArrayList;

public class ShipTypes extends GameBoard{

    public void shipHP(ArrayList<ShipTypes> shipList, int x1, int y1){
        for (ShipTypes shipTypes : shipList) {
            for (int j = 0; j < shipTypes.shipCords.size(); j++) {
                int x = shipTypes.shipCords.get(j)[0];
                int y = shipTypes.shipCords.get(j)[1];

                if (x == x1 && y == y1) {
                    shipTypes.setHp(shipTypes.getHp() - 1);
                    if (shipTypes.getHp() == 0) {
                        shipTypes.isSunk = true;
                        System.out.printf("You sank my %s!\n", shipTypes.name);
                    }
                    break;
                }
            }
        }
    }

    public boolean isWin(ArrayList<ShipTypes> shipList) {
        int nShips = shipList.size();
        for (ShipTypes s : shipList) {
            if (s.isSunk) {
                nShips--;
            }

            if (nShips == 0) {return true;}
        }
        return false;
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
            if (GameBoard.proxCheck(GameBoard.arr, x1, y1) || GameBoard.proxCheck(GameBoard.arr, x2, y2)) {
                continue;
            }

            ArrayList<int[]> sc = new ArrayList<>();

            //Horizontal
            if (x1 == x2) {
                arr[x1][y1] = " O";
                int[] start = {x1, y1};
                sc.add(start);
                for (int i = 0; i < ship.size; i++) {
                    arr[x2][y2 - i] = " O";
                    int[] end = {x2, y2 - i};
                    sc.add(end);
                }
                printBoard(GameBoard.arr);
            }

            //Vertical
            else {
                arr[x1][y1] = " O";
                int[] start = {x1, y1};
                sc.add(start);
                for (int i = 0; i < ship.size; i++) {
                    arr[x2 - i][y2] = " O";
                    int[] end = {x2 - i, y2};
                    sc.add(end);
                }
                printBoard(GameBoard.arr);
            }
            shipList.add(new ShipTypes(ship.name, ship.size, sc, false));
            break;
        }
    }

    //====================================Non-methods below=============================================================

    //A list of all ships.
    public static ArrayList<ShipTypes> shipList = new ArrayList<>();


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

    //Enum ships and constructor.
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

    //Ship fields
    String name;
    int size;
    ArrayList<int[]> shipCords;
    boolean isSunk = false;

    //Ship constructors
    public ShipTypes() {}

    public ShipTypes(String name, int size, ArrayList<int[]> shipCords, boolean isSunk) {
        this.name = name;
        this.size = size;
        this.shipCords = shipCords;
        this.isSunk = isSunk;
        this.hp = size;
    }

    private int hp;
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
}
