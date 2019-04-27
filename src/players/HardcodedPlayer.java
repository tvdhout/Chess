package players;

import framework.Color;
import framework.Game;
import framework.Move;

public class HardcodedPlayer implements Player {

    private Color color;
    private String name;
    private Move[] moves;
    private int movesPerformed = 0;

    public HardcodedPlayer(Color playerColor, String playerName, Move[] hardcodedMoves) {
        this.color = playerColor;
        this.name = playerName;
        this.moves = hardcodedMoves;
    }

    @Override
    public Move getNextPosition(Game gameState) {
        movesPerformed++;
        return moves[movesPerformed-1];
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
