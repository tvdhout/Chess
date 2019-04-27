package pieces;

import framework.*;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    private int nrMoves;

    public King(Game game, Position position, Color color) {
        super(game, position, color);
        nrMoves = 0;
    }

    @Override
    void update() {
        return;
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
        if (canCastle() && // Castles still possible
                // Check if Rook can still castle
                color == Color.WHITE ? game.getWhiteKingsRook().canCastle() : game.getBlackKingsRook().canCastle() &&
                // king not in check
                !isInCheck(board) &&
                // path not occupied
                board[backRank][5] == null && board[backRank][6] == null &&
                // not travelling through check
                !game.isAttacked(board, new Position(5, backRank), color, true) &&
                !game.isAttacked(board, new Position(6, backRank), color, true))
            // King side castling is legal!
            moves.add(new Position(6, backRank));

        if (canCastle() && // Castles still possible
                // Check if Rook can still castle
                color == Color.WHITE ? game.getWhiteQueensRook().canCastle() : game.getBlackQueensRook().canCastle() &&
                // king not in check
                !isInCheck(board) &&
                // path not occupied
                board[backRank][1] == null && board[backRank][2] == null && board[backRank][3] == null &&
                // not travelling through check
                !game.isAttacked(board, new Position(1, backRank), color, true) &&
                !game.isAttacked(board, new Position(2, backRank), color, true) &&
                !game.isAttacked(board, new Position(3, backRank), color, true))
            // Queen side castling is legal!
            moves.add(new Position(2, backRank));

        return moves;
    }

    public boolean canCastle() {
        return nrMoves == 0;
    }

    @Override
    public void move(Position position, Piece[][] board, boolean noCheck) {
        nrMoves++;
        super.move(position, board, noCheck);
    }

    @Override
    public void move(Position newPos, Piece[][] board) {
        nrMoves++;

        // Check for castling
        boolean excludeKing = false;
        if (newPos.getFile() - position.getFile() == 2) {
            // is king-side castling, also move Rook
            board[newPos.getRank()][7].move(new Position(5, newPos.getRank()), board, true);
            excludeKing = true; // do not check if the king's move is legal because it's passing through the rook
        }
        else if (newPos.getFile() - position.getFile() == -2) {
            // is queen-side castling, also move Rook
            board[newPos.getRank()][0].move(new Position(3, newPos.getRank()), board, true);
            excludeKing = true; // do not check if the king's move is legal because it's passing through the rook
        }

        super.move(newPos, board, excludeKing);
    }

    public boolean isInCheck(Piece[][] board) {
        return game.isAttacked(board, position, color);
    }

    @Override
    public String toString() {
        return toString('k');
    }

}
