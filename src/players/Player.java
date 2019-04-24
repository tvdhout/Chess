package players;

import framework.Color;
import framework.Game;
import framework.Move;

public interface Player {

    /**
     * The player is asked for a move given the new game situation.
     * @param gameState the gameState for which the Player should give a new Position
     * @return the move the player wants to make
     */
    public Move getNextPosition(Game gameState);

    /**
     * Gives the color the Player has in the game.
     * @return the Players color.
     */
    public Color getColor();

    /**
     * A String representation of the Player.
     * @return the name of the Player.
     */
    public String toString();
}
