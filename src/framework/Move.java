package framework;

import pieces.Piece;

/**
 * A representation of a Move. A piece is set to a new position.
 */
public class Move {

    private Position oldPosition;
    private Position newPosition;

    public Move(Position oldPosition, Position newPosition) {
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
    }

    public Position getOldPosition() {
        return oldPosition;
    }

    public Position getNewPosition() {
        return newPosition;
    }

    @Override
    public String toString() {
        return "(" + oldPosition + "," + newPosition + ")";
    }
}
