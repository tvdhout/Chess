package pieces;

import framework.Color;
import framework.Game;
import framework.Position;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(Game game, Position position, Color color) {
        super(game, position, color);
    }

    @Override
    void update() {
        return;
    }

    @Override
    public String shortName() {
        return color == Color.WHITE ? "n" : "N";
    }

    @Override
    public ArrayList<Position> getPossibleMoves(Piece[][] board) {
        ArrayList<Position> moves = new ArrayList<>();
        int[][] directions = {{-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}};

        for (int[] direction : directions)
            if (inBounds(direction))
                moves.add(new Position(position.getFile() + direction[0], position.getRank() + direction[1]));

        return moves;
    }

    @Override
    public String toString() {
        return toString('n');
    }
}
