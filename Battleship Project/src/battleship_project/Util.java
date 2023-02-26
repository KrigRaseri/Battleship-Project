package battleship_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class for all utility actions like checking input, menus, etc.
 *
 * @author Krig Raseri (Pen name)
 * */
public class Util {

    /**
     * Checks the users input during ship placement to make sure it is valid.
     *
     * @param ship represents the ship being placed.
     * @param (x, y) are the starting coordinates for the ship.
     * @param (x1, y1) are the ending coordinates for the ship.
     * */
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


    //Takes the users input of coordinates and converts it to an array for the placeShip method.
    public static int[] toCordArray(BufferedReader reader) {
        String inp = null;
        try {
            inp = reader.readLine().toUpperCase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert inp != null;
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

    //Starts the game and calls all relevant methods.
    public static void startGame() {
        ShipTypes st = new ShipTypes();
        GameBoard.createBoard(GameBoard.map);
        GameBoard.createBoard(GameBoard.fog);
        GameBoard.printBoard(GameBoard.map);

        GameBoard.createBoard(Player2.mapOpp);
        GameBoard.createBoard(Player2.fogOpp);
        GameBoard.printBoard(Player2.mapOpp);


        placeAllShips(GameBoard.map, Player2.mapOpp);

        System.out.println("The game starts!");
        while (true) {
            GameBoard.printFullField(GameBoard.map, GameBoard.fog);
            System.out.println("Player 1, it's your turn:");
            GameBoard.fire(Player2.mapOpp, GameBoard.fog, st.reader, Player2.shipListOpp);
            if (st.isWin(Player2.shipListOpp)) { break;}
            changePlayer(st.reader);

            GameBoard.printFullField(Player2.mapOpp, Player2.fogOpp);
            System.out.println("Player 2, it's your turn:");
            GameBoard.fire(GameBoard.map, Player2.fogOpp, st.reader, ShipTypes.shipList);
            if (st.isWin(ShipTypes.shipList)) { break; }
            changePlayer(st.reader);
        }
        System.out.println("You sank the last ship. You won. Congratulations!");
    }


    /**
     * Calls the placeShip method for each ship type for each player.
     *
     * @param map1 Player 1's map that has all of their ships.
     * @param mapOpp Player 2's map that has all of their ships.
     * */
    public static void placeAllShips(String[][] map1, String[][] mapOpp) {
        ShipTypes st = new ShipTypes();
        Player2 p2 = new Player2();
        ArrayList<ShipTypes> shl = ShipTypes.shipList;
        ArrayList<ShipTypes> shl2 = Player2.shipListOpp;


        System.out.println("Player 1, place your ships on the game field");
        st.placeShip(map1, ShipTypes.Ships.AIRCRAFT_CARRIER, shl);
        st.placeShip(map1, ShipTypes.Ships.BATTLESHIP, shl);
        st.placeShip(map1, ShipTypes.Ships.SUBMARINE, shl);
        st.placeShip(map1, ShipTypes.Ships.CRUISER, shl);
        st.placeShip(map1, ShipTypes.Ships.DESTROYER, shl);
        changePlayer(st.reader);

        System.out.println("Player 2, place your ships on the game field");
        p2.placeShip(mapOpp, ShipTypes.Ships.AIRCRAFT_CARRIER, shl2);
        p2.placeShip(mapOpp, ShipTypes.Ships.BATTLESHIP, shl2);
        p2.placeShip(mapOpp, ShipTypes.Ships.SUBMARINE, shl2);
        p2.placeShip(mapOpp, ShipTypes.Ships.CRUISER, shl2);
        p2.placeShip(mapOpp, ShipTypes.Ships.DESTROYER, shl2);
        changePlayer(st.reader);
    }

    //Asks for enter to be pressed so the users can switch to prevent naughty screen peeking.
    public static void changePlayer(BufferedReader reader) {
        System.out.println("Press Enter and pass the move to another player");
        try {
            String input = reader.readLine();
            while (!input.equals("")) {
                input = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

