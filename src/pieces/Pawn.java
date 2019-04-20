package pieces;

import framework.*;

public class Pawn extends Piece {

    private boolean firstMove, enPassant;

    public Pawn(int rank, int file, Color color) {
        super(rank, file, color);
        firstMove = true;
        enPassant = false;
    }

    @Override
    public String shortName() {
        return "P";
    }

    @Override
    public String toString() {
        return toString('p');
    }
}
