package hu.benya77.mylinkedlist;

/**
 *
 * @author B.
 */
public class Head<E> {

    private Node<E> firstNode;

    public Head() {
        firstNode = null;
    }

    public Node<E> getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(Node<E> firstNode) {
        this.firstNode = firstNode;
    }

}
