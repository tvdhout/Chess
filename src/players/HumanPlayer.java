package players;

import framework.Color;
import framework.Game;
import framework.Move;
import framework.Position;

import java.util.Scanner;

/**
 * A Player that can be controlled by a human via the console.
 */
public class HumanPlayer implements Player {

    private Color color;
    private String name;
    private Scanner consoleInput = new Scanner(System.in);

    public HumanPlayer(Color playerColor, String playerName) {
        this.color = playerColor;
        this.name = playerName;
    }

    @Override
    public Move getNextPosition(Game gameState) {
        System.out.println("Please submit your move by specifying the current position of a piece and the position" +
                "the piece should be moved to. Example: d4-d7.");
        String unparsedMove = consoleInput.nextLine();
        String[] positions = unparsedMove.split("-");
        return new Move(new Position(positions[0]), new Position(positions[1]));
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
