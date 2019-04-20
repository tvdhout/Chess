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
        int rank = position.getRank();
        int file = position.getFile();
        int up = (color == Color.WHITE ? 1: -1); // move up

        if(firstMove)
            if (rank + 2 * up <= 7 && rank + 2 * up >= 0)
                moves.add(new Position(file, rank + 2 * up));

        if (rank + up <= 7 && rank + up >= 0)
            moves.add(new Position(file, file + up));

        try {
            if (board[rank + up][file + 1] != null &&
                    board[rank + up][file + 1].color != color)
                moves.add(new Position(rank + up, file + 1));
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
