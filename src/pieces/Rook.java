package pieces;

import framework.Color;
import framework.Position;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(Position position, Color color) {
        super(position, color);
    }

    @Override
    public String shortName() {
        return color == Color.WHITE ? "r" : "R";
    }

    @Override
    public List<Position> getPossibleMoves(Piece[][] board) {
        List<Position> moves = new ArrayList<>();

        for(int newFile = 0; newFile < 8; newFile++) // Horizontal moves
            moves.add(new Position(newFile, position.getRank()));

        for(int newRank = 0; newRank < 8; newRank++) // Vertical moves
            moves.add(new Position(position.getFile(), newRank));

        return moves;
    }

    @Override
    public String toString() {
        return toString('r');
    }
}
