

package TicTacToe;

/**
 * Class to keep track of Player's wins and losses.
 *@author Utsav
 */
public class Player {
    
	public String playerName;
    public String symbol;
    public int playerWin;
    public int playerLoss;
    
    /**
     * Constructor
     * @param playerName Name of the player
     */
    public Player(String playerName){
        this.playerName=playerName;
    }
    
    /**
     * Increment win for each win
     */
    public void PlayerWin(){
        playerWin++;
    }
    
    /**
     * Increment loss for each loss
     */
    public void PlayerLoss(){
        playerLoss++;
    }
    
    /**
     * Return number of wins player has
     * @return playerWin
     */
    public int PlayerWinCount(){
        return playerWin;
    }
    
    /**
     * Return number of losses the player has
     * @return playerLoss
     */
    public int PlayerLossCount(){
        return playerLoss;
    }

}
