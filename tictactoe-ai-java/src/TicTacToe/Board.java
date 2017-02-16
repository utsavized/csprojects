package TicTacToe;

/**
 * Sets up the board, calculates state, player turns and game states
 */

public class Board {
    private int intBoard[]= new int[9];
    private int intMoveSymbol=2; //0=empty;1=Player1;2=Player2
    public int moveCount=0;
    
    /**
     * Constructor Sets up an empty board 
     */
    public Board(){
        for (int x =0;x<=8;x++){
            intBoard[x]=0;
        }
    }
    
    /**
     * Constructor Sets up current board
     * @param intBoard
     */
    public Board(int[] intBoard){
        for (int x =0;x<=8;x++){
            this.intBoard[x]= intBoard[x];
        }      
    }
    
    /**
     * Constructor receives Board and sets number of moves, the current move symbol
     * and sets up the current board
     * @param board
     */
    public Board(Board board){
        copy(board);
    	//this.moveCount=board.moveCount;
        //this.intMoveSymbol=board.intMoveSymbol;
        //System.arraycopy(board.intBoard, 0,this.intBoard, 0, 9);
    }
    
    /**
     * Receives Board and sets number of moves, the current move symbol
     * and sets up the current board
     * @param board
     */
    public void copy(Board board){
        this.moveCount=board.moveCount;
        this.intMoveSymbol=board.intMoveSymbol;
        System.arraycopy(board.intBoard, 0,  this.intBoard, 0, 9);
    }
    
    /**
     * Alternates moves between players
     * @param boxNo
     * @return if move is set
     */
    public boolean SetMove(int boxNo){
        if(intBoard[boxNo]!=0){
        	return false;
        }
        if (intMoveSymbol==1){
            intMoveSymbol=2;
        }else{
            intMoveSymbol=1;
        }
        intBoard[boxNo]=intMoveSymbol;
        moveCount++;
        return true;
    }
    
    /**
     * Check the state of the game
     * @return Win for either player or draw or no end state
     * (-1 being draw and 0 being no end state). For player
     * win, return winning symbol (position).
     */
    public int checkGameState()
    {
        //Check game for row 1
    	if (intBoard[0]==intBoard[1] & intBoard[1]==intBoard[2] & intBoard[2]!=0)
            return intBoard[0];
    	//Check game for row 2
    	else if (intBoard[3]==intBoard[4] & intBoard[4]==intBoard[5]& intBoard[5]!=0)
            return intBoard[3];
    	//Check game for row 3
    	else if (intBoard[6]==intBoard[7] & intBoard[7]==intBoard[8]& intBoard[8]!=0)
            return intBoard[6];
    	//Check game for column 1
    	else if (intBoard[0]==intBoard[3] & intBoard[3]==intBoard[6]& intBoard[6]!=0)
            return intBoard[0];
    	//Check game for column 2
    	else if (intBoard[1]==intBoard[4] & intBoard[4]==intBoard[7]& intBoard[7]!=0)
            return intBoard[1];
    	//Check game for column 3
    	else if (intBoard[2]==intBoard[5] & intBoard[5]==intBoard[8]& intBoard[8]!=0)
            return intBoard[2];
    	//Check game for diagonal left to right
    	else if (intBoard[0]==intBoard[4] & intBoard[4]==intBoard[8]& intBoard[8]!=0)
            return intBoard[0];
        //Check game for diagonal right to left
    	else if (intBoard[2]==intBoard[4] & intBoard[4]==intBoard[6]& intBoard[6]!=0)
            return intBoard[2];
        else
        	//Check if any empty space is left in the board
            for(int x=0;x<9;x++)
                if (intBoard[x]==0)
                    return 0;	//No End State; Continue game
        return -1;	//Draw
    }

    /**
     * Print the board with formatting
     *  0 | 1 | 2 
     * ---+---+---
     *  3 | 4 | 5 
     * ---+---+---
     *  6 | 7 | 8 
     */
    public void Print(){
        for (int x=0;x<3;x++){
            System.out.println();
            for (int y=0;y<3;y++){
                if(y<3 && y!=0)
                	System.out.print("|");
                if (intBoard[y+(3*x)]!=0)
                    if (intBoard[y+(3*x)]==1 ){
                        System.out.print(" X ");
                    }else
                        System.out.print(" O ");
                else
                    System.out.print( " "+ (y+(3*x)) + " ");
                //System.out.print("|");
            }
            System.out.println();
            if(x<2)
            	System.out.print("---+---+---");
        }
    }   

}
