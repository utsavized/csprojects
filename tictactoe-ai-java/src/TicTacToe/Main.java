
package TicTacToe;
import java.util.Scanner;

/**
 * Gathers information for the game to begin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Player 1 Name:");
        //Read player 1 name
        String playerOneName = in.nextLine();
        System.out.println("1) One PLayer Game");
        System.out.println("2) Two PLayer Game");
        System.out.print("Select 1 or 2:");

        //If 2 player game read player 2 name
        if (in.nextLine().equals("2")) {
            System.out.print("Enter Player 2 Name:");
            //Start 2 player game
            game = new Game(playerOneName, in.nextLine());
        } else {
            //Start 1 player game
        	game = new Game(playerOneName);
        }
        do {
        	//Number of games desired this session
            System.out.print("Number of games you want to play:");
            game.totalGamesInSession = in.nextInt();

            do {
            	//Start new board and set player numbers
                game.NewBoard();
                int gameState = 0;	//Game state
                int pNo = 0;
                do {
                    if (pNo == 1)
                        pNo = 2;
                    else
                        pNo = 1;
                    game.gBoard.Print();
                    System.out.println();
                    System.out.print(game.PlayerName(pNo) + "'s Move:");
                    //If game is 1 player
                    if (game.twoPlayer == false && pNo == 2) {
                        //Create new AI player and assign it to player 2
                    	AI a = (AI) game.player2;
                        //Calculate AI move
                    	int aiMove = a.NextMove(game.gBoard);
                        System.out.print(aiMove);
                        //Make AI move
                        gameState = game.Move(aiMove);
                    } else {
                    	//Make player move
                        gameState = game.Move(in.nextInt());

                    }
                    
                    //If move has already been made
                    System.out.println();
                    if (gameState == 3) {
                        System.out.println("Illegal Move, Please Try Again.");
                        if (pNo == 1) {
                            pNo = 2;
                        } else {
                            pNo = 1;
                        }
                    //If legit move
                    } else if (gameState != 0) {
                        //Print board
                    	game.gBoard.Print();
                        System.out.println();
                        //If either player wins
                        if (gameState == 1 || gameState == 2) {
                            //Print winner
                        	System.out.println(game.PlayerName(pNo) + " Wins");
                            //Increment win for that player
                        	game.Win(pNo);
                        }
                        //Game drawn
                        if (gameState == -1) {
                            System.out.println("The game ended in a draw.");
                        }
                        break;
                    }
                } while (true);
            } while (!game.endGame()); //Keep playing until derired games in session condition is met
            
            /*
             * Print out session results
             */
            
            System.out.println(game.PlayerName(1) + " Wins: " + game.Score(1));
            System.out.println(game.PlayerName(2) + " Wins: " + game.Score(2));
            System.out.println("Total Games in this Session : " + game.totalGamesInSession + " games");
            //Reset scores
            game.Reset();
            System.out.println();
            System.out.println("Want to play more?");
            System.out.print("1 to continue or any other key to end:");
            in.nextLine();
        } while (in.nextLine().equals("1"));
    }
}
