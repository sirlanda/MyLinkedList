package mylinkedlist;

/**
 *
 * @author B.
 */
public class MyLinkedList {

    private Head head;
    private Tail tail;

    private Node currentNode;
    private Node newNode;
    private int size;

    /**
     * Constructs an empty list
     */
    public MyLinkedList() {
        this.head = new Head();
        this.tail = new Tail();
        this.currentNode = null;
        this.newNode = null;
        this.size = 0;
    }

    /**
     * Inserts the specified element to the end of the list.
     *
     * @param data element to add.
     */
    public <E> void add(E data) {
        if (getFirstNode() == null) {
            newNode = new Node(data);
            setFirstNode(newNode);
            setLastNode(newNode);
        } else {
            insertLastNode(data);
        }
        //setLastNode(newNode);
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
    public <E> boolean contains(E data) {
        currentNode = getFirstNode();
        if (currentNode == null) {
            return false;
        }
        while (currentNode != getLastNode() && !currentNode.getData().equals(data)) {
            currentNode = nextNode();
        }
        return currentNode.getData().equals(data);
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
        currentNode = getFirstNode();
        while (currentNode != null) {
            System.out.println(currentNode.getData());
            currentNode = nextNode();
        }
    }

    /**
     * Removes the specified element at its <b>first occurence</b>.
     * <p>
     * Note: <em>Shifts any remaining emelents to the left.</em></p>
     *
     * @param data element to remove.
     */
    public <E> void removeByData(E data) {
        if (contains(data)) {
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
        currentNode = getNodeByIndex(index);
        removeNode(currentNode);
    }

    /**
     * Returns the element at the specified position.
     *
     * @param index index of the element.
     * @return returns the elemen at the specified position.
     */
    public Object get(int index) {
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
    public int getIndexOf(Object obj) {
        int index = 0;
        if (contains(obj)) {
            currentNode = getFirstNode();

            while (currentNode != null && currentNode.getData() != obj) {
                currentNode = nextNode();
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
    public <E> void insert(int index, E data) {
        if (isFirstNode(index) && size > 0) {
            insertFirstNode(data);
        } else if (isLastNode(index)) {
            insertLastNode(data);
        } else {
            currentNode = getNodeByIndex(index);
            newNode = new Node(data);
            previousNode().setNextNode(newNode);
            newNode.setPreviousNode(previousNode());
            currentNode.setPreviousNode(newNode);
            newNode.setNextNode(currentNode);
        }
        increaseSize();
    }

    /**
     * Removes <b>every</b> element from the list.
     */
    public void clear() {
        currentNode = getFirstNode().getNextNode();
        while (currentNode != getLastNode()) {
            previousNode().setPreviousNode(null);
            previousNode().setNextNode(null);
            currentNode.setPreviousNode(null);
            currentNode = nextNode();
        }
        setFirstNode(null);
        setLastNode(null);
        size = 0;
    }

    //private methods
    private boolean isFirstNode(int index) {
        return getNodeByIndex(index) == getFirstNode();
    }

    private boolean isLastNode(int index) {
        return getNodeByIndex(index) == getLastNode();
    }

    private boolean isTheOnlyNode(int index) {
        return isFirstNode(index) && isLastNode(index);
    }

    private Node getNodeByIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        currentNode = getFirstNode();
        for (int i = 0; i < index; i++) {
            currentNode = nextNode();
        }
        return currentNode;
    }

    private void removeNode(Node node) {
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

    private Node getFirstNode() {
        return head.getFirstNode();
    }

    private <E> void insertFirstNode(E data) {
        newNode = new Node(data);
        getFirstNode().setPreviousNode(newNode);
        newNode.setNextNode(getFirstNode());
        setFirstNode(newNode);
    }

    private <E> void insertLastNode(E data) {
        newNode = new Node(data, getLastNode(), null);
        getLastNode().setNextNode(newNode);
        setLastNode(newNode);
    }

    private void setFirstNode(Node node) {
        head.setFirstNode(node);
    }

    private void setLastNode(Node node) {
        tail.setLastNode(node);
    }

    private void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    private Node getLastNode() {
        return tail.getLastNode();
    }

    private void increaseSize() {
        size++;
    }

    private void decreaseLength() {
        size--;
    }

    private Head getHead() {
        return head;
    }

    private Node nextNode() {
        return currentNode.getNextNode();
    }

    private Node previousNode() {
        return currentNode.getPreviousNode();
    }
}
