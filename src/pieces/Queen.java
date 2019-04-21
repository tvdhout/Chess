package pieces;

import framework.Color;
import framework.Position;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(Position position, Color color) {
        super(position, color);
    }

    @Override
    public String shortName() {
        return color == Color.WHITE ? "q" : "Q";
    }

    @Override
    public List<Position> getPossibleMoves(Piece[][] board) {
        List<Position> moves = new ArrayList<>();

        Bishop dummyBishop = new Bishop(position, color);
        Rook dummyRook = new Rook(position, color);

        moves.addAll(dummyBishop.getPossibleMoves(board));
        moves.addAll(dummyRook.getPossibleMoves(board));

        // For the garbage collector to feed on
        dummyBishop = null;
        dummyRook = null;
        System.gc(); // Goodbye dummies

        return moves;
    }

    @Override
    public String toString() {
        return toString('q');
    }
}
