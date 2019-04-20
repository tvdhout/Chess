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

        for(Position newPos : allMoves) {
            // Obstructed check

            // No discovered checks

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