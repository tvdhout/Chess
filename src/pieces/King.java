package pieces;

import framework.*;
import javafx.geometry.Pos;

import java.util.List;

public class King extends Piece {

    private boolean isInCheck, isInCheckMate;

    public King(Position position, Color color) {
        super(position, color);
        isInCheck = false;
        isInCheckMate = false;
    }

    @Override
    public String shortName() {
        return "K";
    }

    @Override
    public List<Position> getPossibleMoves(Piece[][] board) {
        return null;
    }

    @Override
    public String toString() {
        return toString('k');
    }
}
