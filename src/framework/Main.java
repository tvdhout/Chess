package framework;

import pieces.*;
import players.HardcodedPlayer;
import players.HumanPlayer;
import players.Player;

public class Main {

    public static void main(String[] args) {
        //scriptedGame();
        hardcodedGame();
    }

    private static void hardcodedGame() {
        Game game = new Game();

        King myKing = (King)game.getWhiteActivePieces().get(game.getWhiteActivePieces().size()-1);
        Rook myRook = (Rook)game.getWhiteActivePieces().get(game.getWhiteActivePieces().size()-2);
        Move whiteMove1 = new Move(myRook.getPosition(), new Position(0, 5));
        Move whiteMove2 = new Move(myKing.getPosition(), new Position(6, 0));
        Move[] whiteMoves = {whiteMove1, whiteMove2};
        Player whiteHardCoded = new HardcodedPlayer(Color.WHITE, "Dennis de Destroyer", whiteMoves);

        King myBlackKing = (King)game.getBlackActivePieces().get(game.getBlackActivePieces().size()-1);
        Move blackMove1 = new Move(myBlackKing.getPosition(), new Position(5, 6));
        Move[] blackMoves = {blackMove1};
        Player blackHardCoded = new HardcodedPlayer(Color.BLACK, "GM hardcode", blackMoves);
        Player human = new HumanPlayer(Color.WHITE, "ik");
        Player human2 = new HumanPlayer(Color.BLACK, "Alter ego");

        GameManager gm = new GameManager(game, human2, human);
        gm.startGame();
    }

}
