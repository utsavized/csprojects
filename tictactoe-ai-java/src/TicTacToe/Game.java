package TicTacToe;

/**
 * Sets up the game
 */
public class Game {
    public Board gBoard= new Board();
    public Player player1;
    public Player player2;
    public int Player1Score;
    public int Player2Score;
    public boolean twoPlayer;		//If the game is 2 player or not
    public int gameCount;			//Number of games played in current session
    public int totalGamesInSession;	//Total number of games user wants to be in current session

    /**
     * Constructor - Sets up 1 Player game against AI
     * @param player1Name
     */
    public Game(String player1Name){
        gBoard =new Board();
        this.player1= new Player(player1Name);
        this.player2= new AI("AI");
        this.twoPlayer=false;
    }
    
    /**
     * Constructor - Sets up 2 Player game
     * @param player1Name
     * @param player2Name
     */
    public Game(String player1Name,String player2Name){
        gBoard =new Board();
        this.player1= new Player(player1Name);
        this.player2= new Player(player2Name);
        this.twoPlayer=true;
    }
    
    /**
     * Creates new board
     */
    public void NewBoard(){
        gBoard=new Board();
        gameCount++;
    }
    
    /**
     * Sets player move
     * @param box
     * @return Game state if any move is possible;
     * if move already made, returns 3;
     */
    public int Move(int box){
        //Check whether box is empty
    	if(gBoard.SetMove(box)==false)
            return 3;	//If not, return 3
        //Else return game state
    	return gBoard.checkGameState();       
    }
    
    /**
     * Returns player name based on their player number
     * @param playerNo
     * @return playerName
     */
    public String PlayerName(int playerNo){
        if (playerNo==1)
           return player1.playerName;
        return player2.playerName;
    }
    
    /**
     * Increments win count for winning player
     * @param playerNo
     */
    public void Win(int playerNo){
        if (playerNo==1)
            this.Player1Score++;
        else
            this.Player2Score++;
    }
    
    /**
     * Returns number of wins for a player
     * @param playerNo
     * @return Score
     */
    public int Score(int playerNo){
        if (playerNo==1)
           return this.Player1Score;
        else
           return this.Player2Score;
    }
    
    /**
     * Resets the game count
     */
    public void Reset(){
        this.Player1Score=0;
        this.Player2Score=0;
        this.gameCount=0;
    }
    
    /**
     * Ends game based on totalGamesInSession
     * @return true/false
     */
    public boolean endGame(){
        //If total games desired in current session have been played
    	if(totalGamesInSession==gameCount){
            return true;	//End game
        }	//Else
        return false;	//Continue
    }
}
