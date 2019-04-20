package pieces;

import framework.Color;
import framework.Position;

import java.util.List;

public class Rook extends Piece {

    public Rook(Position position, Color color) {
        super(position, color);
    }

    @Override
    public String shortName() {
        return "R";
    }

    @Override
    public List<Position> getPossibleMoves(Piece[][] board) {
        return null;
    }

    @Override
    public String toString() {
        return toString('r');
    }
}
