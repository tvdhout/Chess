package framework;

import pieces.*;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        System.out.println(game);
        King myKing = (King)game.getWhiteActivePieces().get(game.getWhiteActivePieces().size()-1);
        Rook myRook = (Rook)game.getWhiteActivePieces().get(game.getWhiteActivePieces().size()-2);

        myRook.move(new Position(0, 5), game.getBoard());

        System.out.println(game);

        System.out.println(myKing.getLegalMoves(game.getBoard()));

        myKing.move(new Position(6,0), game.getBoard());

        System.out.println(game);

        game.undoLastMove();

        System.out.println(game);
        System.out.println(myKing.isQueenSideCastle()+" "+myKing.isKingSideCastle());

    }
}
