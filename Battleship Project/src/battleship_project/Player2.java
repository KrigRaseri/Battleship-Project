package battleship_project;

import java.util.ArrayList;

/**
 * A class for player 2.
 *
 * @author Krig Raseri (Pen name)
 * */
public class Player2 extends ShipTypes{
    //Player 2's list of all ships.
    public static ArrayList<ShipTypes> shipListOpp = new ArrayList<>();

    //Player 2's ship map and fog map.
    public static String[][] mapOpp = new String[10][10];
    public static String[][] fogOpp = new String[10][10];


}
