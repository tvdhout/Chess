package pieces;

import framework.*;

import java.util.List;

public class Pawn extends Piece {

    private boolean firstMove, enPassant;

    public Pawn(Position position, Color color) {
        super(position, color);
        firstMove = true;
        enPassant = false;
    }

    @Override
    public String shortName() {
        return "P";
    }

    @Override
    public List<Position> getPossibleMoves() {
        return null;
    }

    @Override
    public List<Position> getLegalMoves() {
        return null;
    }

    @Override
    public String toString() {
        return toString('p');
    }
}
