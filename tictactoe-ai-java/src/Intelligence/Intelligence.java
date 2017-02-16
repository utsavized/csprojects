package Intelligence;
import TicTacToe.*;

/**
 * Calculates AI move using Minimax algorithm
 * with Alpha Beta Pruning
 */
public class Intelligence {

    public int nodeCount;
    public Node rootNode;

    /**
     * Constructor - New board
     * @param b
     */
    public Intelligence(Board b) {
        Board newB = new Board(b);
        rootNode = new Node(newB);
    }

    /**
     * Calculate next move
     * @return
     */
    public int Move() {
        int max = -10;
        Node bestNode= new Node();
        for (int x = 0; x <= 8; x++) {
            Node n = new Node();
            n.copy(rootNode);
            if (n.board.SetMove(x) == true) {
                rootNode.AddChildren(n);
                n.moveBox = x;
                int val = alphaBeta(n, true, -10, 10);
                if (val >= max) {
                    max = val;
                    bestNode = n;
                }
            }
        }
        return bestNode.moveBox;
    }

    /**
     * Minimax algorithm with Alpha Beta Pruning
     * @param node
     * @param min
     * @param alpha
     * @param beta
     * @return game state
     */
    
     private int alphaBeta(Node node, boolean min, int alpha, int beta) {
        //if(game over in current board position)
    	if(boardPoint(node) != -2){
           node.point = boardPoint(node);
           return boardPoint(node);	//return winner
        }
    	
    	else {	
            //if(min's turn)
    		if(min == true) {
            	//for each child
            	for (int x = 0; x <= 8; x++) {
                    Node n = new Node();
                    n.copy(node);
                    if (n.board.SetMove(x) == true) {
                        node.AddChildren(n);
                        n.moveBox = x;
                        //score = alpha-beta(child,other player,alpha,beta)
                        int val = alphaBeta(n, false, alpha, beta);
                        //if score < beta then beta = score (opponent has found a better worse move)
                        if (val < beta) {
                            beta = val;
                            n.parent.point = val;
                        }
                    }
                }
            	//return beta (this is the opponent's best move)
                return beta;
            }
    		//if(max's turn)
    		else if (min == false) {
    			//for each child
                for (int x = 0; x <= 8; x++) {
                    Node n = new Node();
                    n.copy(node);
                    if (n.board.SetMove(x) == true) {
                        node.AddChildren(n);
                        n.moveBox = x;
                        //score = alpha-beta(child,other player,alpha,beta)
                        int val = alphaBeta(n, true, alpha, beta);
                        //if score > alpha then alpha = score (we have found a better best move)
                        if (val > alpha) {
                            alpha = val;
                            n.parent.point = val;
                        }
                    }
                }
                //return alpha (this is our best move)
                return alpha;
            }
        }
        return -100; //Just to satisfy the return type; this will never be reached
    }

    /**
     * Checks for game state
     * @param n
     * @return game state
     */
    private int boardPoint(Node n) {
        if (n.board.checkGameState() == 1) {
            return -1;
        }
        if (n.board.checkGameState() == 2) {
            return 1;
        }
        if (n.board.checkGameState() == -1) {
            return 0;
        }
        return -2;

    }
}
