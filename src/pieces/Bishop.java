package pieces;

import framework.Color;
import framework.Position;

public class Bishop extends Piece {

    public Bishop(int rank, int file, Color color) {
        super(rank, file, color);
    }

    @Override
    public String shortName() {
        return "B";
    }

    @Override
    public String toString() {
        return toString('b');
    }
}
