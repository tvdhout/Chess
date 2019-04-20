package pieces;

import framework.Color;
import framework.Position;

public class Rook extends Piece {

    public Rook(int rank, int file, Color color) {
        super(rank, file, color);
    }

    @Override
    public String shortName() {
        return "R";
    }

    @Override
    public String toString() {
        return toString('r');
    }
}
