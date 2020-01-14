package hu.benya77.mylinkedlist;

/**
 *
 * @author B.
 */
public class Tail<E> {

    private Node<E> lastNode;

    public Tail() {
        this.lastNode = null;
    }

    public Node<E> getLastNode() {
        return lastNode;
    }

    public void setLastNode(Node<E> lastNode) {
        this.lastNode = lastNode;
    }
}
