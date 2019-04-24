package framework;

import pieces.*;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Piece[][] board;
    private List<Piece> whiteActivePieces;
    private List<Piece> blackActivePieces;
    private List<Position[]> moves;
    private King whiteKing, blackKing;
    private Rook whiteKingsRook, whiteQueensRook, blackKingsRook, blackQueensRook;

    public Game() {
        this.board = new Piece[8][8];
        whiteActivePieces = new ArrayList<>();
        blackActivePieces = new ArrayList<>();
        moves = new ArrayList<>();
        resetBoard();
    }

    public Game(String FEN) {
        // TODO: Load game from parsed FEN string
    }

    private void resetBoard() {
        board = new Piece[8][8];
//        List<Piece> list = whiteActivePieces;
//        for(int i = 0; i < 8; i+=7){
//            Color c = i == 0 ? Color.WHITE : Color.BLACK;
//            list.add(new Rook(this, new Position(0, i), c));
//            board[i][0] = list.get(list.size()-1);
//            list.add(new Rook(this, new Position(7, i), c));
//            board[i][7] = list.get(list.size()-1);
//            list.add(new Knight(this, new Position(1, i), c));
//            board[i][1] = list.get(list.size()-1);
//            list.add(new Knight(this, new Position(6, i), c));
//            board[i][6] = list.get(list.size()-1);
//            list.add(new Bishop(this, new Position(2, i), c));
//            board[i][2] = list.get(list.size()-1);
//            list.add(new Bishop(this, new Position(5, i), c));
//            board[i][5] = list.get(list.size()-1);
//            list.add(new King(this, new Position(3, i), c));
//            board[i][3] = list.get(list.size()-1);
//            if(c == Color.WHITE)
//                whiteKing = (King)list.get(list.size()-1);
//            else
//                blackKing = (King)list.get(list.size()-1);
//            list.add(new Queen(this, new Position(4, i), c));
//            board[i][4] = list.get(list.size()-1);
//            list = blackActivePieces;
//        }
//        list = whiteActivePieces;
//        for(int r = 1; r < 7; r+=5) {
//            Color c = r == 1 ? Color.WHITE : Color.BLACK;
//            for (int f = 0; f < 8; f++) {
//                list.add(new Pawn(this, new Position(f, r), c));
//                board[r][f] = list.get(list.size() - 1);
//            }
//            list = blackActivePieces;
//        }

        //Test piece
        whiteKingsRook = new Rook(this, new Position(7, 0), Color.WHITE);
        whiteActivePieces.add(whiteKingsRook);
        board[0][7] = whiteActivePieces.get(whiteActivePieces.size() - 1);
        whiteQueensRook = new Rook(this, new Position(0, 0), Color.WHITE);
        whiteActivePieces.add(whiteQueensRook);
        board[0][0] = whiteActivePieces.get(whiteActivePieces.size() - 1);

        whiteKing = new King(this, new Position(4, 0), Color.WHITE);
        whiteActivePieces.add(whiteKing);
        board[0][4] = whiteActivePieces.get(whiteActivePieces.size() - 1);

        blackKing = new King(this, new Position(5, 5), Color.BLACK);
        blackActivePieces.add(blackKing);
        board[5][5] = blackActivePieces.get(blackActivePieces.size() - 1);

    }

    public void addMove(Position[] move) {
        assert move.length == 2 : "Moves should have a start and end position";
        moves.add(move);
    }

    public void undoLastMove() {
        if (moves.isEmpty())
            return;
        Position[] move = moves.remove(moves.size() - 1);
        Position newPos = move[1];
        Position oldPos = move[0];
        Piece piece = board[newPos.getRank()][newPos.getFile()];
        piece.move(oldPos, board, true, true);

        // If last move was e1/7 to c1/7 or g1/7 (maybe castles)
        if (!moves.isEmpty() && oldPos.getFile() == 4 && oldPos.getRank() % 7 == 0 &&
                (newPos.getFile() == 2 || newPos.getFile() == 6) && newPos.getRank() % 7 == 0) {
            Position[] move2 = moves.remove(moves.size() - 1);
            Position newPos2 = move2[1];
            Position oldPos2 = move2[0];
            Piece piece2 = board[newPos2.getRank()][newPos2.getFile()];

            // Next move is rook castle move (has to be because this move would otherwise be illegal after the previous)
            if (oldPos2.getFile() % 7 == 0 && oldPos2.getRank() % 7 == 0 &&
                    newPos2.getRank() % 7 == 0 && (newPos2.getFile() == 3 || newPos2.getFile() == 5)) {
                piece2.move(oldPos2, board, true, true);
                Rook kingsRook = piece2.getColor() == Color.WHITE ? whiteKingsRook : blackKingsRook;
                Rook queensRook = piece2.getColor() == Color.WHITE ? whiteQueensRook : whiteQueensRook;
                if(kingsRook.getNrMoves() == 0)
                    ((King) piece).setKingSideCastle(true);
                if(queensRook.getNrMoves() == 0)
                    ((King) piece).setQueenSideCastle(true);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int rank = 7; rank >= 0; rank--) {
            sb.append(rank + 1);
            for (Piece piece : board[rank]) {
                sb.append(piece == null ? "| " : "|" + piece.shortName());
            }
            sb.append("|\n");
        }
        sb.append("  a b c d e f g h");
        return sb.toString();
    }

    /**
     * return whether position pos is attacked for color (by other color)
     *
     * @param pos   position to check for attacks
     * @param board board on which to check position
     * @param color color to receive the attack
     */
    public boolean isAttacked(Piece[][] board, Position pos, Color color) {
        return isAttacked(board, pos, color, false);
    }

    public boolean isAttacked(Piece[][] board, Position pos, Color color, boolean excludeKing) {
        List<Piece> list = null;
        if (excludeKing) { // is used to find checks, getting legal moves for king will result in infinite loop
            King king = color == Color.WHITE ? whiteKing : blackKing;
            list = color == Color.WHITE ? whiteActivePieces : blackActivePieces;
            list.remove(king);
        } else
            list = color == Color.WHITE ? whiteActivePieces : blackActivePieces;

        for (Piece piece : list)
            if (piece.getLegalMoves(board).contains(pos))
                return true;
        return false;
    }

    // --------------------- Getters

    public String getFEN() {
        StringBuilder sb = new StringBuilder();
        // TODO: create FEN representation of game
        return sb.toString();
    }

    public Piece[][] getBoard() {
        return board;
    }

    public King getWhiteKing() {
        return whiteKing;
    }

    public King getBlackKing() {
        return blackKing;
    }

    public List<Piece> getWhiteActivePieces() {
        return whiteActivePieces;
    }

    public List<Piece> getBlackActivePieces() {
        return blackActivePieces;
    }

    public List<Position[]> getMoves() {
        return moves;
    }

    public boolean isOver() { //TODO: register when the game is over
        return true;
    }
}
