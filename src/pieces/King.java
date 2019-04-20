package pieces;

import framework.*;

public class King extends Piece {

    private boolean isInCheck, isInCheckMate;

    public King(int rank, int file, Color color) {
        super(rank, file, color);
        isInCheck = false;
        isInCheckMate = false;
    }

    @Override
    public String shortName() {
        return "K";
    }

    @Override
    public String toString() {
        return toString('k');
    }
}
