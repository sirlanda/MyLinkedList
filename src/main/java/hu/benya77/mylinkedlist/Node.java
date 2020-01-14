package hu.benya77.mylinkedlist;

/**
 *
 * @author B.
 */
public class Node<E> {
    
    private E data;
    private Node<E> next;
    private Node<E> prev;

    public Node(){
        this.data = null;
        this.prev = null;
        this.next = null;
    }
    
    public Node(E data){
        this.data = data;
        this.prev = null;
        this.next = null;
    }
    
    public Node(E data, Node<E> prev, Node<E> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public void setData(E item) {
        this.data = item;
    }

    public Node<E> getNextNode() {
        return next;
    }

    public void setNextNode(Node<E> nextNode) {
        this.next = nextNode;
    }

    public Node<E> getPreviousNode() {
        return prev;
    }

    public void setPreviousNode(Node<E> previousNode) {
        this.prev = previousNode;
    }

}
