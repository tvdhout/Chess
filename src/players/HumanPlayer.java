package players;

import framework.Color;
import framework.Game;
import framework.Move;
import framework.Position;

import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        System.out.println("Please submit your move by specifying the current position of a piece and the position " +
                "the piece should be moved to. Example: d4-d7.");
        String[] positions = retrieveConsoleInput(false);
        return new Move(new Position(positions[0]), new Position(positions[1]));
    }

    private String[] retrieveConsoleInput(boolean repeated) {
        if (repeated)
            System.out.println("The position could not be decoded. Please specify the current and the next position." +
                    System.lineSeparator() + "A position is denoted by a file (a-h) and a rank (1-8), so a position " +
                    "looks like a5 or h8.");
        String unparsedMove = consoleInput.nextLine();
        Pattern pattern = Pattern.compile("([a-hA-H][1-8]).*([a-hA-H][1-8])");
        Matcher matcher = pattern.matcher(unparsedMove);
        String [] positions = new String[2];
        System.out.println("counter: " + matcher.hitEnd());
        if (!matcher.find())
            return retrieveConsoleInput(true);
        for (int i = 1; i < 3; i++)
            positions[i-1] = matcher.group(i);
        return positions;
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
