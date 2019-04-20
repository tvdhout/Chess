package pieces;

import framework.*;

public abstract class Piece {
    protected int rank, file;
    protected Color color;

    public Piece(int rank, int file, Color color) {
        if(file > 7 || file < 0 || rank > 7 || rank < 0){
            throw new IllegalArgumentException(String.format("Illegal Position coordinates: %d, %d", file, rank));
        }
        this.rank = rank;
        this.file = file;
        this.color = color;
    }

    public String toString(char letter) {
        return ""+Character.toUpperCase(letter)+(char)(file+'a')+rank;
//        return color == Color.WHITE ? ""+letter+position : ""+Character.toUpperCase(letter)+position;
    }

    public abstract String shortName();
}
