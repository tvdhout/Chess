package pieces;

import framework.Color;
import framework.Position;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(Position position, Color color) {
        super(position, color);
    }

    @Override
    public String shortName() {
        return "B";
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
        return toString('b');
    }
}
