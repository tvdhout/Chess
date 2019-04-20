package framework;

public abstract class Piece {
    protected Position position;
    protected Color color;

    public Piece(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public String toString(char letter) {
        return color == Color.WHITE ? ""+letter+position : ""+Character.toUpperCase(letter)+position;
    }
}
