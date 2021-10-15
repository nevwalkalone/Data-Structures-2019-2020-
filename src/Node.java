/**
 * Class representing a Node, contains an item and a reference to the next node.
 * Generics were used.
 */

public class Node<T> {

	public T item;
	public Node<T> next;

	public Node(T item) {
		this.item = item;
		this.next = null;
	}
}
