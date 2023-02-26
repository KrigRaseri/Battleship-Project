package battleship_project;

import java.util.ArrayList;

/**
 * A class for the ship types which includes ship related functionality.
 *
 * @author Krig Raseri (Pen name)
 * */
public class ShipTypes extends GameBoard{

    /**
     * When a ship is hit it will take those coordinates and find which ship it belongs to, and then subtract 1 from
     * that ships hp (health). If the ship in questions hp hits 0 isSunk will be set to true.
     *
     * @param shipList List of ship objects belonging to a player.
     * @param x1 x coordinate of the shot that hit the ship.
     * @param y1 x coordinate of the shot that hit the ship.
     * */
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

    /**
     * If all ships in the ship have isSunk as true then that player loses, and the player that made the final shot wins
     *
     * @param shipList List of ship objects belonging to a player.
     * */
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

    /**
     * Places ship at coordinates x1,y1  through x2, y2.
     *
     * @param map Represents the users map with ships on it.
     * @param ship Represents the Ships enumerator.
     * @param shipList List of ship objects belonging to a player.
     * */
    public void placeShip(String[][] map, Ships ship, ArrayList<ShipTypes> shipList) {
        while (true) {
            System.out.printf("Enter the coordinates of the %s (%d cells):\n", ship.name, ship.size);
            int[] cordArray = Util.toCordArray(new ShipTypes().reader);
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

            //Checks user input for mistakes.
            if (Util.checkInput(ship, x1, y1, x2, y2)) {
                continue;
            }

            //Checks the proximity of the input to other ships.
            if (GameBoard.proxCheck(map, x1, y1) || GameBoard.proxCheck(map, x2, y2)) {
                continue;
            }

            //A list of ship coordinates. (sc = shipCoordinates).
            ArrayList<int[]> sc = new ArrayList<>();

            //Horizontal
            if (x1 == x2) {
                map[x1][y1] = " O";
                int[] start = {x1, y1};
                sc.add(start);
                for (int i = 0; i < ship.size; i++) {
                    map[x2][y2 - i] = " O";
                    int[] end = {x2, y2 - i};
                    sc.add(end);
                }
                printBoard(map);
            }

            //Vertical
            else {
                map[x1][y1] = " O";
                int[] start = {x1, y1};
                sc.add(start);
                for (int i = 0; i < ship.size; i++) {
                    map[x2 - i][y2] = " O";
                    int[] end = {x2 - i, y2};
                    sc.add(end);
                }
                printBoard(map);
            }
            //Adds the ship that was placed to the ship list.
            shipList.add(new ShipTypes(ship.name, ship.size, sc, false));
            break;
        }
    }

    //====================================Non-methods below=============================================================

    //A list of all ships.
    public static ArrayList<ShipTypes> shipList = new ArrayList<>();

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
