package pieces;

import framework.Color;
import framework.Position;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(Position position, Color color) {
        super(position, color);
    }

    @Override
    public String shortName() {
        return color == Color.WHITE ? "b" : "B";
    }

    @Override
    public List<Position> getPossibleMoves(Piece[][] board) {
        List<Position> moves = new ArrayList<>();
        int rank = position.getRank();
        int file = position.getFile();

        for(int newFile = 0; newFile < 8; newFile++){ // For each file
            int rankChange = Math.abs(newFile - file); // change in file == change in rank
            if(rank+rankChange >= 0 && rank+rankChange < 8)
                moves.add(new Position(newFile, rank+rankChange)); // position if bishop moves up to new file
            if(rank-rankChange >= 0 && rank-rankChange < 8)
                moves.add(new Position(newFile, rank-rankChange)); // position if bishop moves down to new file
        }

        return moves;
    }

    @Override
    public String toString() {
        return toString('b');
    }
}
