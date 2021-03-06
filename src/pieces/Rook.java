package pieces;

import framework.Color;
import framework.Game;
import framework.Position;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    private int nrMoves;

    public Rook(Game game, Position position, Color color) {
        super(game, position, color);
        nrMoves = 0;
    }

    @Override
    void update() {
        return;
    }

    @Override
    public String shortName() {
        return color == Color.WHITE ? "r" : "R";
    }

    @Override
    public ArrayList<Position> getPossibleMoves(Piece[][] board) {
        ArrayList<Position> moves = new ArrayList<>();

        for(int newFile = 0; newFile < 8; newFile++) // Horizontal moves
            moves.add(new Position(newFile, position.getRank()));

        for(int newRank = 0; newRank < 8; newRank++) // Vertical moves
            moves.add(new Position(position.getFile(), newRank));

        return moves;
    }

    @Override
    public void move(Position position, Piece[][] board, boolean noCheck) {
        nrMoves++;
        super.move(position, board, noCheck);
    }

    @Override
    public void move(Position position, Piece[][] board){
        nrMoves++;
        super.move(position, board, false);
    }

    @Override
    public String toString() {
        return toString('r');
    }

    public int getNrMoves() {
        return nrMoves;
    }

    public boolean canCastle(){
        return nrMoves == 0;
    }
}
