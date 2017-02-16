package Intelligence;
import TicTacToe.*;
import java.util.*;

/**
 * Makes the game tree
 */
public class Node {

    public int moveBox;
    public int point;
    public Board board;
    public Node parent;
    public ArrayList<Node> childrenNode = new ArrayList<Node>();

    /**
     * Constructor creates new board
     */
    public Node() {
        this.board = new Board();
    }

    /**
     * New node
     * @param board
     */
    public Node(Board board) {
        this.board = board;
    }
    
    /**
     * Add children
     * @param node
     */
    public void AddChildren(Node node) {
        childrenNode.add(node);
        node.Parent(this);
    }

    /**
     * Set parent
     * @param node
     */
    private void Parent(Node node) {
        this.parent = node;
    }

    /**
     * Copy node
     * @param n
     */
    public void copy(Node n) {
        this.point=n.point;
        this.board.copy(n.board);
    }

    /**
     * Prints out the board
     */
    public void Print() {
        System.out.println();
        System.out.println("Point" + this.point);
        System.out.println("moveBox" + this.moveBox);
        this.board.Print();
        System.out.println();
    }
}
