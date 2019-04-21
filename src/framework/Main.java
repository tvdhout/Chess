package framework;

import pieces.*;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        System.out.println(game);
        Piece myPiece = game.getActivePieces().get(game.getActivePieces().size()-1);
        System.out.println(String.format("%s %s on %s", myPiece.getColor(), myPiece.getClass(), myPiece.getPosition()));
        System.out.println("Legal moves: "+myPiece.getLegalMoves(game.getBoard()));
    }
}
