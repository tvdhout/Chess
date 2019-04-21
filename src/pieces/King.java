package pieces;

import framework.*;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    private boolean queenSideCastle, kingSideCastle;

    public King(Game game, Position position, Color color) {
        super(game, position, color);
        queenSideCastle = true;
        kingSideCastle = true;
    }

    @Override
    public String shortName() {
        return color == Color.WHITE ? "k" : "K";
    }

    @Override
    public List<Position> getPossibleMoves(Piece[][] board) {
        List<Position> moves = new ArrayList<>();
        int[][] directions = {{-1, 1}, {0, 1}, {1, 1}, {-1, 0}, {1, 0}, {-1, -1}, {0, -1}, {1, -1}};
        int backRank = color == color.WHITE ? 0 : 7;

        for (int[] direction : directions)
            if (inBounds(direction))
                moves.add(new Position(position.getFile() + direction[0], position.getRank() + direction[1]));

        // ------- Castles
        if (kingSideCastle && // Castles still possible
                !isInCheck(board) && // king not in check
                board[backRank][5] == null && board[backRank][6] == null && // path not occupied
                !game.isAttacked(board, new Position(5, backRank), color, true) && // not travelling through check
                !game.isAttacked(board, new Position(6, backRank), color, true))
            moves.add(new Position(6, backRank));

        if (queenSideCastle && // Castles still possible
                !isInCheck(board) && // king not in check
                // path not occupied
                board[backRank][1] == null && board[backRank][2] == null && board[backRank][3] == null &&
                // not travelling through check
                !game.isAttacked(board, new Position(1, backRank), color, true) &&
                !game.isAttacked(board, new Position(2, backRank), color, true) &&
                !game.isAttacked(board, new Position(3, backRank), color, true))
            moves.add(new Position(2, backRank));

        return moves;
    }

    @Override
    public void move(Position newPos, Piece[][] board) {
        queenSideCastle = false;
        kingSideCastle = false;
        // Check for castling
        boolean excludeKing = false;
        if (newPos.getFile() - position.getFile() == 2) {
            // move Rook
            board[newPos.getRank()][7].move(new Position(5, newPos.getRank()), board);
            excludeKing = true;
        }
        else if (newPos.getFile() - position.getFile() == -2) {
            // move Rook
            board[newPos.getRank()][0].move(new Position(3, newPos.getRank()), board);
            excludeKing = true;
        }

        super.move(newPos, board, false, excludeKing);
    }

    public boolean isInCheck(Piece[][] board) {
        return game.isAttacked(board, position, color, true);
    }

    @Override
    public String toString() {
        return toString('k');
    }

    public boolean isQueenSideCastle() {
        return queenSideCastle;
    }

    public boolean isKingSideCastle() {
        return kingSideCastle;
    }

    public void setQueenSideCastle(boolean queenSideCastle) {
        this.queenSideCastle = queenSideCastle;
    }

    public void setKingSideCastle(boolean kingSideCastle) {
        this.kingSideCastle = kingSideCastle;
    }
}
