package pieces;

import framework.Color;
import framework.Position;

import java.util.List;

public class Queen extends Piece {

    public Queen(Position position, Color color) {
        super(position, color);
    }

    @Override
    public String shortName() {
        return "Q";
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
        return toString('q');
    }
}
