package framework;

public class Position {

    private final int file, rank;

    public Position(int file, int rank) {
        if(file > 7 || file < 0 || rank > 7 || rank < 0){
            throw new IllegalArgumentException(String.format("Illegal Position coordinates: %d, %d", file, rank));
        }
        this.file = file;
        this.rank = rank;
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
}
