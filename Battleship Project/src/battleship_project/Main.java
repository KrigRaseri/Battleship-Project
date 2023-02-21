package battleship_project;

public class Main {
    public static void main(String[] args)  {
        GameBoard gb = new GameBoard();
        gb.createBoard(gb.arr);
        gb.printBoard(gb.arr);
    }
}
