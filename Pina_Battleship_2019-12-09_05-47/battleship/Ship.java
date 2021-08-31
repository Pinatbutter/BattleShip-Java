package battleship;

import java.io.PrintStream;

public class Ship {


    public static enum Direction {
        Horizontal,
        Vertical
    }

    public String name;
    public Location start;
    public Direction direction;
    public int length;
    public int hits = 0;
    public boolean sunk = false;

    public Ship(String name, Location start, Direction direction, int length) {
        this.name = name;
        this.start = start;
        this.direction = direction;
        this.length = length;
    }

    public boolean contains(Location location) {
        if (direction == Direction.Horizontal) {
            return (
                    location.row == start.row &&
                            location.col >= start.col &&
                            location.col <  start.col + length
            );
        }
        else {
            return (
                    location.col == start.col &&
                            location.row >= start.row &&
                            location.row <  start.row + length
            );
        }
    }
    public boolean overlaps(Ship ship) {
        if (this.direction == ship.direction) {
            return (
                    this.contains(ship.start) ||
                            ship.contains(this.start)
            );
        }

        if (this.direction == Direction.Horizontal) {
            return (
                    this.start.row >= ship.start.row &&
                            this.start.row <  ship.start.row + ship.length &&
                            ship.start.col >= this.start.col &&
                            ship.start.col <  this.start.col + this.length
            );
        }
        else {
            return (
                    ship.start.row >= this.start.row &&
                            ship.start.row <  this.start.row + this.length &&
                            this.start.col >= ship.start.col &&
                            this.start.col <  ship.start.col + ship.length
            );
        }
    }

    public void print(PrintStream stream) {
        System.out.print(name);
        System.out.print(' ');
        System.out.print((char)('A' + start.col));
        System.out.print(start.row);
        System.out.print(direction == Ship.Direction.Horizontal ? 'H' : 'V');
        System.out.println();
    }
}