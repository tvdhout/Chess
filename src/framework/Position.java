package framework;

import java.util.InputMismatchException;
import java.util.Objects;

public class Position {

    private final int file, rank;

    public Position(int file, int rank) {
        if(file > 7 || file < 0 || rank > 7 || rank < 0){
            throw new IllegalArgumentException(String.format("Illegal Position coordinates: %d, %d", file, rank));
        }
        this.file = file;
        this.rank = rank;
    }

    /**
     * Create a position from a human readable String like "a1" to denote a position on the chessboard.
     * Positions are in standard chess form, so File is 1 to 8 and rank a to h.
     * @param position String in the form of filerank like "a1". File goes from 1 to 8 and rank from a to h.
     */
    public Position(String position) {
        if (position.length() > 1) {
            int file = (int) position.charAt(0);
            int rank = (int) position.charAt(1);
            System.out.println("file: " + file + "rank: " + rank);
            if (file > 96 && file < 105 && rank > 48 && rank < 57) {
                this.file = file - 97; // checked to be a to h
                this.rank = (rank) - 48 - 1; // checked to be 1 to 8
            }
            else
                throw new InputMismatchException("Unexpected input: \"" + position + "\". Expected a position denoted " +
                        "like \"a1\"");
        }
        else
            throw new InputMismatchException("Unexpected input: \"" + position + "\". Expected a position denoted " +
                    "like \"a1hkjkkjjk\" to \"h8\"");
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public String toString(){
        return String.format("%s%d", (char)(file + 'a'), rank+1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return file == position.file &&
                rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
