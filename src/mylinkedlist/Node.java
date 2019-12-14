package mylinkedlist;

/**
 *
 * @author B.
 */
public class Node<E> {
    
    private E data;
    private Node next;
    private Node prev;

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
    
    public Node(E data, Node prev, Node next) {
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

    public Node getNextNode() {
        return next;
    }

    public void setNextNode(Node nextNode) {
        this.next = nextNode;
    }

    public Node getPreviousNode() {
        return prev;
    }

    public void setPreviousNode(Node previousNode) {
        this.prev = previousNode;
    }

}
