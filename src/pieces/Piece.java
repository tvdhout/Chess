package pieces;

import framework.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.signum;
import static java.lang.Math.max;

public abstract class Piece {
    protected Position position;

    protected final Color color;

    public Piece(Position position, Color color) {
        this.position = position;
        this.color = color;
    }
    
    public void move(Position position) {
        throw new UnsupportedOperationException();
    }

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

            // --------- Assert no discovered checks
            // TODO: check for discovered checks

            // --------- Obstructed path check
            if(!(this instanceof Knight)) { // Knights can jump over pieces
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
//        return ""+Character.toUpperCase(letter)+position;
        return color == Color.WHITE ? "" + letter + position : "" + Character.toUpperCase(letter) + position;
    }

    public Position getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public boolean inBounds(int[] direction){
        return position.getFile()+direction[0] >= 0 && position.getFile()+direction[0] < 8 &&
                position.getRank()+direction[1] >= 0 && position.getRank()+direction[1] < 8;
    }

    public abstract String shortName();

    public abstract List<Position> getPossibleMoves(Piece[][] board);

}
