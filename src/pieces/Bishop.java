package pieces;

import framework.*;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(Game game, Position position, Color color) {
        super(game, position, color);
    }

    @Override
    void update() {
        return;
    }

    @Override
    public String shortName() {
        return color == Color.WHITE ? "b" : "B";
    }

    @Override
    public ArrayList<Position> getPossibleMoves(Piece[][] board) {
        ArrayList<Position> moves = new ArrayList<>();
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
