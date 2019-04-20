package pieces;

import framework.Color;
import framework.Position;

public class Knight extends Piece {

    public Knight(int rank, int file, Color color) {
        super(rank, file, color);
    }

    @Override
    public String shortName() {
        return "N";
    }

    @Override
    public String toString() {
        return toString('n');
    }
}
