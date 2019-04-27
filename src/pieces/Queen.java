package pieces;

import framework.Color;
import framework.Game;
import framework.Position;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(Game game, Position position, Color color) {
        super(game, position, color);
    }

    @Override
    void update() {
        return;
    }

    @Override
    public String shortName() {
        return color == Color.WHITE ? "q" : "Q";
    }

    @Override
    public List<Position> getPossibleMoves(Piece[][] board) {
        List<Position> moves = new ArrayList<>();

        Bishop dummyBishop = new Bishop(game, position, color);
        Rook dummyRook = new Rook(game, position, color);

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
