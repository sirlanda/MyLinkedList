package mylinkedlist;

/**
 *
 * @author B.
 */
public class Tail {

    private Node lastNode;

    public Tail() {
        this.lastNode = null;
    }

    public Node getLastNode() {
        return lastNode;
    }

    public void setLastNode(Node lastNode) {
        this.lastNode = lastNode;
    }
}
