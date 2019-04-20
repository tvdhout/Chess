package pieces;

import framework.*;

import java.util.ArrayList;
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
    public List<Position> getPossibleMoves(Piece[][] board) {
        List<Position> moves = new ArrayList<>();
        int up = (color == Color.WHITE ? 1: -1); // move up

        if(firstMove)
            moves.add(new Position(position.getFile(),position.getRank() + 2*up));
        moves.add(new Position(position.getFile(), position.getRank() + up));

        try {
            if (board[position.getRank() + up][position.getFile() + 1] != null &&
                    board[position.getRank() + up][position.getFile() + 1].color != color)
                moves.add(new Position(position.getRank() + up, position.getFile() + 1));
        } catch (Exception e) {
            // can't check for pieces out of bounds
        }

        try {
            if (board[position.getRank() + up][position.getFile() - 1] != null &&
                    board[position.getRank() + up][position.getFile() - 1].color != color)
                moves.add(new Position(position.getRank() + up, position.getFile() + 1));
        } catch (Exception e) {
            // can't check for pieces out of bounds
        }

        return moves;
    }


    @Override
    public String toString() {
        return toString('p');
    }
}
