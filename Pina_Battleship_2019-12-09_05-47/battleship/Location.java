package battleship;

public class Location {
    public final int row;
    public final int col;

    public Location(int row, int col) {
        if (row < 0) {
            this.row = 0;
        }
        else if (row > 9) {
            this.row = 9;
        }
        else {
            this.row = row;
        }

        if (col < 0) {
            this.col = 0;
        }
        else if (col > 9) {
            this.col = 9;
        }
        else {
            this.col = col;
        }
    }
}