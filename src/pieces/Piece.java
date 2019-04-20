package pieces;

import framework.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    protected Position position;
    protected Color color;

    public Piece(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public void move(Position position) {
        throw new UnsupportedOperationException();
    }

    public List<Position> getLegalMoves(Piece[][] board){
        List<Position> allMoves = getPossibleMoves(board);
        List<Position> legalMoves = new ArrayList<>();

        int prevRank = position.getRank();
        int prevFile = position.getFile();

        for(Position newPos : allMoves) { // For each "possible" move:
            int newRank = newPos.getRank();
            int newFile = newPos.getFile();

            // --------- Obstructed check

            // Position occupied by own color
            if(board[newPos.getRank()][newPos.getFile()] != null &&
                    board[newPos.getRank()][newPos.getFile()].color == color)
                continue;

            // Path to destination obstructed
            int dRank = newPos.getRank() - position.getRank(); // Change in rank
            int dFile = newPos.getFile() - position.getFile(); // Change in file

            if(dRank == dFile){ // bottom-left top-right diagonal move
                int steps = dRank-(int)Math.signum(dRank); // number of steps + direction (sign) to check on the path
                for(int pathIdx = (int)Math.signum(steps); pathIdx < Math.abs(steps); pathIdx += Math.signum(steps))
                    if(board[prevRank+pathIdx][prevFile+pathIdx] != null)
                        continue;
            }
            // TODO: top-left to bottom-right diagonals (dRank == -dFile)

            // TODO: horizontal / vertical moves

            // No discovered checks
            // TODO: check for discovered checks

            // TODO: skip parameter for knights / check if this doesn't break for king / pawn

            legalMoves.add(newPos);
        }

        return legalMoves;
    }

    public String toString(char letter) {
        return ""+Character.toUpperCase(letter)+position;
//        return color == Color.WHITE ? ""+letter+position : ""+Character.toUpperCase(letter)+position;
    }

    public abstract String shortName();
    public abstract List<Position> getPossibleMoves(Piece[][] board);

}