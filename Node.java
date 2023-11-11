public class Node<E> {
    private Node<E> previous;
    private E element;
    private Node<E> next;
    public Node(Node<E> previous, E element, Node<E> next) {
        this.previous = previous;
        this.element = element;
        this.next = next;
    }
    public void setPrevious(Node<E> previous) {
        this.previous = previous;
    }
    public Node<E> getPrevious() {
        return previous;
    }
    public E getElement() {
        return element;
    }
    public void setNext(Node<E> next) {
        this.next = next;
    }
    public Node<E> getNext() {
        return next;
    }
}