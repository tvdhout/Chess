package framework;

public class Pawn extends Piece {

    private boolean firstMove, enPassant;

    public Pawn(Position position, Color color) {
        super(position, color);
        firstMove = true;
        enPassant = false;
    }

    @Override
    public String toString() {
        return toString('p');
    }
}
