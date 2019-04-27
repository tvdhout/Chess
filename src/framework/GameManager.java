package framework;

import pieces.Piece;
import players.Player;

import java.util.ArrayList;

/**
 * The GameManger class handles all actions that concern the game of chess.
 */
public class GameManager {

    private Game game;
    private Player[] players = new Player[2];
    private int playerToMove;
    private ArrayList<Move> moveHistory = new ArrayList<Move>();

    public GameManager(Game game, Player white, Player black) {
        this.game = game;
        players[0] = white;
        players[1] = black;
    }

    public void startGame() {
        System.out.println("Initial state: ");
        System.out.println(game);
        runGame();
    }

    /**
     * Give turns to each player and update TUI and information about the game.
     */
    private void runGame() {
        while(moveHistory.size() < 10) {
            Move playerMove = players[playerToMove].getNextPosition(game);
            Piece pieceToMove = game.getBoard()[playerMove.getOldPosition().getRank()][playerMove.getOldPosition().getFile()];
//            if (pieceToMove.getMovesInRange(game.getBoard()).contains(playerMove.getNewPosition()))
                pieceToMove.move(playerMove.getNewPosition(), game.getBoard());
            moveHistory.add(playerMove);

            updateTUI(playerMove);

            playerToMove = (playerToMove + 1) % 2;
        }
    }

    private void updateTUI(Move playerMove) {
        System.out.println("Turn number " + moveHistory.size() + ": " + players[playerToMove] + " moves "
                + playerMove.getOldPosition() + " to " + playerMove.getNewPosition());
        System.out.println(game);
        System.out.println("Aantal black pieces: " + game.getBlackActivePieces().size());
        System.out.println("Aantal white pieces: " + game.getWhiteActivePieces().size());

        System.out.println(System.lineSeparator());
    }

}
