package pieces;

import framework.*;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    private boolean isInCheck, isInCheckMate;

    public King(Position position, Color color) {
        super(position, color);
        isInCheck = false;
        isInCheckMate = false;
    }

    @Override
    public String shortName() {
        return color == Color.WHITE ? "k" : "K";
    }

    @Override
    public List<Position> getPossibleMoves(Piece[][] board) {
        List<Position> moves = new ArrayList<>();
        int[][] directions = {{-1, 1},{0, 1},{1, 1}, {-1, 0}, {1, 0}, {-1, -1}, {0, -1}, {1, -1}};

        for (int[] direction : directions)
            if (inBounds(direction))
                moves.add(new Position(position.getFile() + direction[0], position.getRank() + direction[1]));

        return moves;
    }

    @Override
    public String toString() {
        return toString('k');
    }
}
