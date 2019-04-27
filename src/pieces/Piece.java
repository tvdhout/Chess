package pieces;

import framework.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.signum;
import static java.lang.Math.max;

public abstract class Piece {
    protected Position position;
    protected Game game;
    protected final Color color;

    public Piece(Game game, Position position, Color color) {
        this.game = game;
        this.position = position;
        this.color = color;
    }

    /**
     * Overloaded method for move without optional parameters
     *
     * @param position
     * @param board
     */
    public void move(Position position, Piece[][] board) {
        move(position, board, false);
    }

    /**
     * Move this piece to "position" on "board". Undoing a move? revert = true. no need to check legal moves? noCheck
     *
     * @param position position to move to
     * @param board    board to make move on
     * @param noCheck  true if skipping checking for legality (for castling the king)
     */
    public void move(Position position, Piece[][] board, boolean noCheck) {
        if (noCheck || (getMovesInRange(board).contains(position) && !resultingCheck(board, position))) {
            // remove piece from active pieces if move takes a piece
            if (board[position.getRank()][position.getFile()] != null) {
                List<Piece> activePieces = color == Color.WHITE ? game.getBlackActivePieces() :
                        game.getWhiteActivePieces(); // opposite colored active pieces
                activePieces.remove(board[position.getRank()][position.getFile()]); // remove taken piece
            }
            board[position.getRank()][position.getFile()] = this;
            board[this.position.getRank()][this.position.getFile()] = null;
            game.addMove(new Position[]{this.position, position});
            this.position = position;

            // Inform all active pieces of move
            for(Piece piece : game.getBlackActivePieces()) if(!piece.equals(this)) piece.update();
            for(Piece piece : game.getWhiteActivePieces()) if(!piece.equals(this)) piece.update();
        } else {
            System.err.println("Illegal move!");
        }
    }

    public boolean inBounds(int[] direction) {
        return position.getFile() + direction[0] >= 0 && position.getFile() + direction[0] < 8 &&
                position.getRank() + direction[1] >= 0 && position.getRank() + direction[1] < 8;
    }

    /**
     * Get list of moves in range from the current position of this piece.
     *
     * @param board board to look for moves on
     * @return List<Position> of position to move to
     */
    public List<Position> getMovesInRange(Piece[][] board) {
        List<Position> allMoves = getPossibleMoves(board);
        List<Position> legalMoves = new ArrayList<>();

        int prevRank = position.getRank();
        int prevFile = position.getFile();

        for (Position newPos : allMoves) { // For each "possible" move:
            int newRank = newPos.getRank();
            int newFile = newPos.getFile();

            // --------- Destination occupied by own color
            if (board[newRank][newFile] != null && board[newRank][newFile].color == color)
                continue;

            // --------- Obstructed path check
            if (!(this instanceof Knight)) { // Knights can jump over pieces
                int dRank = newRank - prevRank; // Change in rank
                int dFile = newFile - prevFile; // Change in file

                int step; // previous position + step is position to check
                int nrSteps = max(abs(dRank), abs(dFile)); // total number of steps to check
                for (step = 1; step < nrSteps; step++) // for each position on the path to the destination (excluded)
                    // if there is a piece on the position, the move is illegal
                    if (board[prevRank + step * (int) signum(dRank)][prevFile + step * (int) signum(dFile)] != null)
                        break;
                if (step != nrSteps) // loop broken prematurely: obstruction found
                    continue; // move no longer legal, move on to the next
            }

            legalMoves.add(newPos);
        }
        return legalMoves;
    }

    public boolean resultingCheck(Piece[][] board, Position newPos) {
        // --------- Assert no discovered checks // moves while check
        King myKing = color == Color.WHITE ? game.getWhiteKing() : game.getBlackKing();
        board[position.getRank()][position.getFile()] = null;
        board[newPos.getRank()][newPos.getFile()] = this;
        for(Piece piece : color == Color.WHITE ? game.getBlackActivePieces() : game.getWhiteActivePieces())
            if(piece.getMovesInRange(board).contains(myKing.getPosition())) {
                board[position.getRank()][position.getFile()] = this;
                board[newPos.getRank()][newPos.getFile()] = null;
                return true;
            }
        board[position.getRank()][position.getFile()] = this;
        board[newPos.getRank()][newPos.getFile()] = null;
        return false;
    }

    abstract void update();

    public String toString(char letter) {
        return color == Color.WHITE ? "" + letter + position : "" + Character.toUpperCase(letter) + position;
    }

    public Position getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public abstract String shortName();

    public abstract List<Position> getPossibleMoves(Piece[][] board);

}
