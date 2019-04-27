package pieces;

import framework.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Pawn extends Piece {

    private boolean firstMove, enPassant;

    public Pawn(Game game, Position position, Color color) {
        super(game, position, color);
        firstMove = true;
        enPassant = false;
    }

    @Override
    public String shortName() {
        return color == Color.WHITE ? "p" : "P";
    }

    @Override
    public List<Position> getPossibleMoves(Piece[][] board) {
        List<Position> moves = new ArrayList<>();
        int rank = position.getRank();
        int file = position.getFile();
        int up = (color == Color.WHITE ? 1 : -1); // move up

        if (rank + up <= 7 && rank + up >= 0)
            moves.add(new Position(file, rank + up));

        if (firstMove)
            if (rank + 2 * up <= 7 && rank + 2 * up >= 0)
                moves.add(new Position(file, rank + 2 * up));


        for (int i = -1; i < 2; i += 2) {
            // Taking pieces regularly
            try {
                if (board[rank + up][file + i] != null &&
                        board[rank + up][file + i].color != color)
                    moves.add(new Position(file + i, rank + up));
            } catch (Exception e) {
                // can't check for pieces out of bounds
            }

            // Taking pawns en passant
            try {
                if (board[rank][file + i] != null &&
                        board[rank][file + i].color != color &&
                        board[rank][file + i] instanceof Pawn &&
                        ((Pawn) board[rank][file + i]).canTakeEnPassant())
                    moves.add(new Position(file + i, rank + up));
            } catch (Exception e) {
                // can't check for pieces out of bounds
            }
        }

        return moves;
    }

    @Override
    public void move(Position position, Piece[][] board) {
        move(position, board, false);
    }

    @Override
    public void move(Position position, Piece[][] board, boolean noCheck) {
        if (firstMove && Math.abs(position.getRank() - this.position.getRank()) == 2)
            enPassant = true;

        int diff = position.getFile() - this.position.getFile();
        if (diff != 0) {
            //Taking en passant:
            if (board[position.getRank()][position.getFile()] == null) {
                board[position.getRank()][position.getFile()] =
                        board[this.position.getRank()][this.position.getFile() + diff];
                board[this.position.getRank()][this.position.getFile() + diff] = null;
            }
        }
        super.move(position, board, noCheck);
        firstMove = false;
    }

    @Override
    /**
     * called every move of any piece
     */
    protected void update() {
        if (enPassant) enPassant = false;
    }

    @Override
    public String toString() {
        return toString('p');
    }

    @Override
    public List<Position> getMovesInRange(Piece[][] board) {
        List<Position> moves = super.getMovesInRange(board);
        Iterator<Position> lit = moves.iterator();
        while(lit.hasNext()) {
            Position move = lit.next();
            // Pawn can't take vertically, remove these moves.
            if (position.getFile() == move.getFile() && board[move.getRank()][move.getFile()] != null)
                lit.remove();
        }
        return moves;
    }

    public boolean canTakeEnPassant() {
        return enPassant;
    }
}
