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
     * @param position
     * @param board
     */
    public void move(Position position, Piece[][] board) {
        move(position, board, false, false);
    }

    /**
     * Move this piece to "position" on "board". Undoing a move? revert = true. no need to check legal moves? noCheck
     * @param position position to move to
     * @param board board to make move on
     * @param revert true is undoing a move, no need to check for legality and not adding to move list
     * @param noCheck true if skipping checking for legality (for castling the king)
     */
    public void move(Position position, Piece[][] board, boolean revert, boolean noCheck) {
        if(revert){ // move to undo last move
            board[position.getRank()][position.getFile()] = this;
            board[this.position.getRank()][this.position.getFile()] = null;
            this.position = position;
        }
        else if (noCheck || getLegalMoves(board).contains(position)) {
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
        } else {
            System.err.println("Illegal move!");
        }
    }

    /**
     * Get list of legal moves in the current position of this piece.
     * @param board board to look for moves on
     * @return List<Position> of position to move to
     */
    public List<Position> getLegalMoves(Piece[][] board) {
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

            // --------- Assert no discovered checks // moves while check
            // TODO: results in check
            // play move;

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

    public String toString(char letter) {
        return color == Color.WHITE ? "" + letter + position : "" + Character.toUpperCase(letter) + position;
    }

    public Position getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public boolean inBounds(int[] direction) {
        return position.getFile() + direction[0] >= 0 && position.getFile() + direction[0] < 8 &&
                position.getRank() + direction[1] >= 0 && position.getRank() + direction[1] < 8;
    }

    public abstract String shortName();

    public abstract List<Position> getPossibleMoves(Piece[][] board);

}
