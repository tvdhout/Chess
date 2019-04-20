package pieces;

import framework.Color;
import framework.Position;

public class Queen extends Piece {

    public Queen(int rank, int file, Color color) {
        super(rank, file, color);
    }

    @Override
    public String shortName() {
        return "Q";
    }

    @Override
    public String toString() {
        return toString('q');
    }
}
