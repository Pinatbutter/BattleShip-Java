package battleship;

import java.util.Random;

public class Board {
    public Ship.Direction shipDirection;
    private final char[][] dots = new char[10][10];
    private Ship[] ships;

    public Board(String[] nams) {
        for (int i = 0; i < 10; i++) {
            for (int e = 0; e < 10; e++){
                dots[i][e] = '.';
            }
        }
        if (nams != null && nams.length >= 1) {
            setUpShips(nams.length, nams);
        }
        else{
            ships = new Ship[5];
            Random r = new Random();
            ships[0] = createShip("Carrier", 5, r);
            ships[1] = createShip("Battleship", 4, r);
            ships[2] = createShip("Cruiser", 3, r);
            ships[3] = createShip("Submarine", 3, r);
            ships[4] = createShip("Destroyer", 2, r);
        }
    }
    public void setUpShips(int length, String[] nams){
        ships = new Ship[length];
        if (length >= 1) {
            Location l = startLocation(nams[0]);
            ships[0] = new Ship("Carrier", l, shipDirection, 5);
        }
        if (length >= 2) {
            Location l = startLocation(nams[1]);
            ships[1] = new Ship("Battleship", l, shipDirection, 4);
        }
        if (length >= 3) {
            Location l = startLocation(nams[2]);
            ships[2] = new Ship("Cruiser", l, shipDirection, 3);
        }
        if (length >= 4) {
            Location l = startLocation(nams[3]);
            ships[3] = new Ship("Submarine", l, shipDirection, 3);
        }
        if (length >= 5) {
            Location l = startLocation(nams[4]);
            ships[4] = new Ship("Destroyer", l, shipDirection, 2);
        }
    }

    private Ship createShip(String name, int length, Random random) {
        int full = random.nextInt(10);
        int limited = random.nextInt(11 - length);

        Ship ship;

        Ship.Direction direction;
        Location start;
        if (random.nextBoolean()) {
            direction = Ship.Direction.Horizontal;
            start = new Location(full, limited);
        }
        else {
            direction = Ship.Direction.Vertical;
            start = new Location(limited, full);
        }
        ship = new Ship(name, start, direction, length);

        while (Overlaps(ship))  {
            if (random.nextBoolean()) {
                direction = Ship.Direction.Horizontal;
                start = new Location(full, limited);
            }
            else {
                direction = Ship.Direction.Vertical;
                start = new Location(limited, full);
            }
            ship = new Ship(name, start, direction, length);
        }
        return ship;
    }

    public Location startLocation(String input) {
        int row;
        int col;
        int d;

        col = input.toUpperCase().charAt(0);
        col -= 'A';
        row = input.charAt(1);
        row -= '0';
        d = input.charAt(2);
        if (d == 72 || d == 104)
            shipDirection = Ship.Direction.Horizontal;
        if (d == 86 || d == 118)
            shipDirection = Ship.Direction.Vertical;
        Location location = new Location(row, col);
        return location;
    }

    public Location convertToLocation(String input) {
        int row;
        int col;
        col = input.toUpperCase().charAt(0);
        col -= 'A';
        row = input.charAt(1);
        row -= '0';
        Location location = new Location(row, col);
        return location;
    }

    public void outputBoard() {
        System.out.println("  ABCDEFGHIJ");
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
            System.out.print(' ');
            for (int k = 0; k < 10; k++){
                System.out.print(dots[i][k]);
            }
            System.out.println();
        }
    }

    public boolean findShip(Location l) {
        for (int i = 0; i < ships.length; i++){
            if (ships[i].contains(l)) {
                if (dots[l.row][l.col] == '.') {
                    ships[i].hits++;
                }
                return true;
            }
        }
        return false;
    }

    public void hitOrMiss(Location l) {
        String shipName = "N/A";
        for (int i = 0; i < ships.length; i++){
            if (ships[i].contains(l)) {
                shipName = ships[i].name;
            }
        }

        if (findShip(l)) {
            System.out.println("Hit " + shipName);
            dots[l.row][l.col] = 'X';
        }
        else if (!findShip(l)) {
            System.out.println("Miss");
            dots[l.row][l.col] = 'O';
        }
    }

    public boolean end() {
        boolean a = true;
        for (int i = 0; i < ships.length; i++) {
            if (ships[i].length == ships[i].hits && ships[i].sunk == false) {
                System.out.println("You sank my " + ships[i].name);
                ships[i].sunk = true;
            }
            if (ships[i].sunk == false) {
                a = false;
            }
        }
        return a;
    }

    private boolean Overlaps(Ship ship) {
        boolean doesOverlap = false;
        for (Ship value : ships) {
            if (value != null && ship.overlaps(value)) {
                doesOverlap = true;
            }
        }
        return doesOverlap;
    }

}