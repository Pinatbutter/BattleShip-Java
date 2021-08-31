import battleship.*;
import java.util.Scanner;

public class Game {
    private static Scanner scanner = new Scanner(System.in);
    private static Ship[] whichShip(String[] thisShip) {
        String[] names = {
                "Carrier",
                "Battleship",
                "Cruiser",
                "Submarine",
                "Destroyer"
        };

        int[] lengths = { 5, 4, 3, 3, 2 };
        int count = Math.min(thisShip.length, names.length);

        Ship[] ships = new Ship[count];
        for (int i = 0; i < count; ++i) {
            String arg = thisShip[i];
            Location start = new Location(
                    arg.charAt(1) - '0',
                    Character.toLowerCase(arg.charAt(0)) - 'a'
            );
            Ship.Direction direction =
                    Character.toLowerCase(arg.charAt(2)) == 'v' ?
                            Ship.Direction.Vertical : Ship.Direction.Horizontal;
            ships[i] = new Ship(names[i], start, direction, lengths[i]);
        }

        return ships;
    }


    public void battle(String[] args) {

        Board gameBoard = new Board(args);
        gameBoard.outputBoard();
        String convertLine;
        Scanner next = new Scanner(System.in);
        System.out.print("? ");

        int i = 0;
        while (!gameBoard.end()) {
            boolean validMove;

            if (next.hasNext()) {

                convertLine = next.nextLine();
                validMove = true;

                Location l = gameBoard.convertToLocation(convertLine);


                if (validMove) {
                    gameBoard.hitOrMiss(l);
                }
                validMove = true;
                gameBoard.end();

                if (!gameBoard.end()){
                    gameBoard.outputBoard();
                    System.out.print("? ");
                }
            }
            else {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.battle(args);
    }

}