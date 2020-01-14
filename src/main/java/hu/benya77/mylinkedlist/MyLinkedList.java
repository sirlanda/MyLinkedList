package hu.benya77.mylinkedlist;

/**
 *
 * @author B.
 */
public class MyLinkedList<E> {
    
    private Head<E> head;
    private Tail<E> tail;
    private int size;

    /**
     * Constructs an empty list
     */
    public MyLinkedList() {
        this.head = new Head<>();
        this.tail = new Tail<>();
        this.size = 0;
    }

    /**
     * Inserts the specified element to the end of the list.
     *
     * @param data element to add.
     */
    public void add(E data) {
        if (getFirstNode() == null) {
            Node<E> newNode = new Node<>(data);
            setFirstNode(newNode);
            setLastNode(newNode);
        } else {
            insertLastNode(data);
        }
        increaseSize();
    }

    /**
     * Returns true if the list contains the specified element. Returns false if
     * the list does not contain the specified element.
     *
     * @param data element to look for.
     * @return true if the list contains the specified element. Returns false if
     * the list does not contain the specified element.
     */
    public boolean contains(E data) {
        if (getFirstNode() == null) {
            return false;
        }
        Node<E> currentNode = getNodeByData(data);
        return currentNode.getData().equals(data);
    }

    private Node<E> getNodeByData(E data) {
        Node<E> currentNode = getFirstNode();
        while (currentNode != getLastNode() && !currentNode.getData().equals(data)) {
            currentNode = currentNode.getNextNode();
        }
        return currentNode;
    }

    /**
     * Returns the number of elements contained by the list.
     *
     * @return returns the number of elements contained by the list.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the contents of the list.
     */
    public void printList() {
        Node<E> currentNode = getFirstNode();
        while (currentNode != null) {
            System.out.println(currentNode.getData());
            currentNode = currentNode.getNextNode();
        }
    }

    /**
     * Removes the specified element at its <b>first occurence</b>.
     * <p>
     * Note: <em>Shifts any remaining emelents to the left.</em></p>
     *
     * @param data element to remove.
     */
    public void removeByData(E data) {
        Node<E> currentNode = getNodeByData(data);
        if (currentNode != null) {
            removeNode(currentNode);
        } else {
            throw new MyNoSuchElementException("There is no such element!");
        }
    }

    /**
     * Removes the element at the specified position.
     * <p>
     * Note: <em>Shifts any remaining emelents to the left.</em></p>
     *
     * @param index index at which to remove the element.
     */
    public void removeByIndex(int index) {
        Node<E> currentNode = getNodeByIndex(index);
        removeNode(currentNode);
    }

    /**
     * Returns the element at the specified position.
     *
     * @param index index of the element.
     * @return returns the elemen at the specified position.
     */
    public E get(int index) {
        return getNodeByIndex(index).getData();
    }

    /**
     * Returns the index of the <b>first occurence</b> of the specified element.<br>
     * Returns <b>-1</b> if the list does <b>not</b> contain the element.
     *
     * @param obj element to search for.
     * @return the index of the <b>first occurence</b> of the specified element.
     * Returns <b>-1</b> if the list does <b>not</b> contain the element.
     */
    public int getIndexOf(E obj) {
        int index = 0;
        if (contains(obj)) {
            Node<E> currentNode = getFirstNode();

            while (currentNode != null && currentNode.getData() != obj) {
                currentNode = currentNode.getNextNode();
                index++;
            }
        } else {
            return -1;
        }
        return index;
    }

    /**
     * Inserts the specified element at the specified position.
     * <p>
     * Note: <em>Shifts any remaining emelents to the right.</em></p>
     *
     * @param index index at which to insert the element.
     * @param data element to insert.
     */
    public void insert(int index, E data) {
        if (isFirstNode(index) && size > 0) {
            insertFirstNode(data);
        } else {
            Node<E> currentNode = getNodeByIndex(index);
            Node<E> newNode = new Node<>(data);
            currentNode.getPreviousNode().setNextNode(newNode);
            newNode.setPreviousNode(currentNode.getPreviousNode());
            currentNode.setPreviousNode(newNode);
            newNode.setNextNode(currentNode);
        }
        increaseSize();
    }

    /**
     * Removes <b>every</b> element from the list.
     */
    public void clear() {
        Node<E> currentNode = getFirstNode().getNextNode();
        while (currentNode != getLastNode()) {
            currentNode.getPreviousNode().setPreviousNode(null);
            currentNode.getPreviousNode().setNextNode(null);
            currentNode.setPreviousNode(null);
            currentNode = currentNode.getNextNode();
        }
        setFirstNode(null);
        setLastNode(null);
        size = 0;
    }

    private boolean isFirstNode(int index) {
        return getNodeByIndex(index) == getFirstNode();
    }

    private boolean isLastNode(int index) {
        return getNodeByIndex(index) == getLastNode();
    }

    private Node<E> getNodeByIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> currentNode = getFirstNode();
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextNode();
        }
        return currentNode;
    }

    private void removeNode(Node<E> node) {
        if (node != getFirstNode() && node != getLastNode()) {
            node.getPreviousNode().setNextNode(node.getNextNode());
            node.getNextNode().setPreviousNode(node.getPreviousNode());
            node.setNextNode(null);
            node.setPreviousNode(null);
            node.setData(null);
            decreaseLength();
        } else if (node == getFirstNode() && node == getLastNode()) {
            node.setPreviousNode(null);
            node.setNextNode(null);
            node.setData(null);
            setFirstNode(null);
            setLastNode(null);
            decreaseLength();
        } else if (node == getFirstNode()) {
            node.getNextNode().setPreviousNode(null);
            setFirstNode(node.getNextNode());
            node.setNextNode(null);
            node.setData(null);
            decreaseLength();
        } else if (node == getLastNode()) {
            node.getPreviousNode().setNextNode(null);
            setLastNode(node.getPreviousNode());
            node.setPreviousNode(null);
            node.setData(null);
            decreaseLength();
        }
    }

    private Node<E> getFirstNode() {
        return head.getFirstNode();
    }

    private void insertFirstNode(E data) {
        Node<E> newNode = new Node<>(data);
        getFirstNode().setPreviousNode(newNode);
        newNode.setNextNode(getFirstNode());
        setFirstNode(newNode);
    }

    private void insertLastNode(E data) {
        Node<E> newNode = new Node<>(data, getLastNode(), null);
        getLastNode().setNextNode(newNode);
        setLastNode(newNode);
    }

    private void setFirstNode(Node<E> node) {
        head.setFirstNode(node);
    }

    private void setLastNode(Node<E> node) {
        tail.setLastNode(node);
    }

    private Node<E> getLastNode() {
        return tail.getLastNode();
    }

    private void increaseSize() {
        size++;
    }

    private void decreaseLength() {
        size--;
    }

    private Head<E> getHead() {
        return head;
    }
}
