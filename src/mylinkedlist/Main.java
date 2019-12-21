package mylinkedlist;

/**
 *
 * @author B.
 */
public class Main {

    public static void main(String[] args) {
        MyLinkedList<String> lista = new MyLinkedList<>();
        lista.add("text");  //accepted
        lista.add(7);       //compile time error
    }

}
