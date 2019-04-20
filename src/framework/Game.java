package framework;

import pieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    private Piece[][] board;
    private int numberOfMoves;

    public Game() {
        this.board = new Piece[8][8];
        resetBoard();
        numberOfMoves = 0;
    }

    private void resetBoard() {
        board = new Piece[8][8];
        for(int i = 0; i < 8; i+=7){
            Color c = i == 0 ? Color.WHITE : Color.BLACK;
            board[i][0] = new Rook(i, 0, c);
            board[i][7] = new Rook(i, 7, c);
            board[i][1] = new Knight(i, 1, c);
            board[i][6] = new Knight(i, 6, c);
            board[i][2] = new Bishop(i, 2, c);
            board[i][5] = new Bishop(i, 5, c);
            board[i][3] = new King(i, 3, c);
            board[i][4] = new Queen(i, 4, c);
        }
        for(int r = 1; r < 7; r+=5) {
            Color c = r == 1 ? Color.WHITE : Color.BLACK;
            for (int f = 0; f < 8; f++)
                board[r][f] = new Pawn(r, f, c);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int rank = 7; rank >= 0; rank--){
            for(Piece piece : board[rank]){
                sb.append(piece == null ? "| " : "|"+piece.shortName());
            }
            sb.append("|\n");
        }
        return sb.toString();
    }
}
