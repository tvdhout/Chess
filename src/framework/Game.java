package framework;

import pieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    private Piece[][] board;
    private int numberOfMoves;
    private List<Piece> activePieces;


    public Game() {
        this.board = new Piece[8][8];
        activePieces = new ArrayList<>();
        numberOfMoves = 0;
        resetBoard();
    }

    public Game(String FEN){
        // TODO: Load game from parsed FEN string
    }

    private void resetBoard() {
        board = new Piece[8][8];
        for(int i = 0; i < 8; i+=7){
            Color c = i == 0 ? Color.WHITE : Color.BLACK;
            activePieces.add(new Rook(new Position(0, i), c));
            board[i][0] = activePieces.get(activePieces.size()-1);
            activePieces.add(new Rook(new Position(7, i), c));
            board[i][7] = activePieces.get(activePieces.size()-1);
            activePieces.add(new Knight(new Position(1, i), c));
            board[i][1] = activePieces.get(activePieces.size()-1);
            activePieces.add(new Knight(new Position(6, i), c));
            board[i][6] = activePieces.get(activePieces.size()-1);
            activePieces.add(new Bishop(new Position(2, i), c));
            board[i][2] = activePieces.get(activePieces.size()-1);
            activePieces.add(new Bishop(new Position(5, i), c));
            board[i][5] = activePieces.get(activePieces.size()-1);
            activePieces.add(new King(new Position(3, i), c));
            board[i][3] = activePieces.get(activePieces.size()-1);
            activePieces.add(new Queen(new Position(4, i), c));
            board[i][4] = activePieces.get(activePieces.size()-1);
        }
        for(int r = 1; r < 7; r+=5) {
            Color c = r == 1 ? Color.WHITE : Color.BLACK;
            for (int f = 0; f < 8; f++) {
                activePieces.add(new Pawn(new Position(f, r), c));
                board[r][f] = activePieces.get(activePieces.size() - 1);
            }
        }

        //Test piece
//        activePieces.add(new King(new Position(2, 3), Color.BLACK));
//        board[3][2] = activePieces.get(activePieces.size()-1);
//        activePieces.add(new King(new Position(3, 3), Color.WHITE));
//        board[3][3] = activePieces.get(activePieces.size()-1);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int rank = 7; rank >= 0; rank--){
            sb.append(rank+1);
            for(Piece piece : board[rank]){
                sb.append(piece == null ? "| " : "|"+piece.shortName());
            }
            sb.append("|\n");
        }
        sb.append("  a b c d e f g h");
        return sb.toString();
    }

    public String getFEN(){
        StringBuilder sb = new StringBuilder();
        // TODO: create FEN representation of game
        return sb.toString();
    }

    public Piece[][] getBoard() {
        return board;
    }

    public List<Piece> getActivePieces() {
        return activePieces;
    }
}
