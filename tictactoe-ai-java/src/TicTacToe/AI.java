package TicTacToe;
import Intelligence.*;
/**
 * Creates new boards bases on best next move for AI Player
 */

public class AI extends Player{
    
	/**
	 * Constructor -- Uses the constructore of Player.java
	 * @param PlayerName Name of player
	 */
	public AI(String PlayerName){
        super(PlayerName);
    }
    
	/**
	 * Draws board for best next move
	 * @param gBoard Current board
	 * @return intel.Move() New move
	 */
	public int NextMove(Board gBoard){
        Intelligence intel= new Intelligence(gBoard);
        return intel.Move();
    }

}

