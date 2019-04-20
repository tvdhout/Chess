package pieces;

import framework.*;

import java.util.List;

public abstract class Piece {
    protected Position position;
    protected Color color;

    public Piece(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public String toString(char letter) {
        return ""+Character.toUpperCase(letter)+position;
//        return color == Color.WHITE ? ""+letter+position : ""+Character.toUpperCase(letter)+position;
    }

    public abstract String shortName();
    public abstract List<Position> getPossibleMoves();
    public abstract List<Position> getLegalMoves();
}
