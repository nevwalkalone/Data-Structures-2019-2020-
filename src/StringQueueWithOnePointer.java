import java.io.PrintStream;
import java.util.NoSuchElementException;

/*
 * Class that implements the Queue Interface's methods with only one pointer.
 * More comments for each method can be found in StringQueue.java. Generics and
 * a circular linked list were used.
 */

public class StringQueueWithOnePointer<T> implements StringQueue<T> {

	public Node<T> last;
	private int size = 0;

	// Default Constructor
	public StringQueueWithOnePointer() {

	}

	public boolean isEmpty() {
		return last == null;
	}

	public void put(T item) {

		// creating a new node object that will be inserted
		Node<T> node = new Node<>(item);
		size++;

		/*
		 * If the queue is empty this node will be the new last pointer, and it will
		 * point to its self, because it is the only element and we are using a circular
		 * linked list
		 */
		if (isEmpty()) {
			last = node;
			last.next = node;

		}

		/*
		 * else we connect the previous last node to the newly inserted node, and this
		 * node to the first element of the queue, in order this list remains circular
		 */
		else {
			node.next = last.next;
			last.next = node;
			last = node;
		}
	}

	public T get() throws NoSuchElementException {

		// If queue is empty throw an Exception
		if (isEmpty()) {
			throw new NoSuchElementException("Queue is empty");
		}

		/*
		 * If the size of queue is 1 just remove the last item, else make the previous
		 * item as last and connect it to the first item. Return the old last item
		 */
		Node<T> node = last.next;
		if (size == 1) {
			last = null;
		}

		else {
			last.next = node.next;
		}

		size--;
		return node.item;
	}

	public T peek() throws NoSuchElementException {

		// if queue is empty throw an exception
		if (isEmpty()) {
			throw new NoSuchElementException("QUEUE IS EMPTY");
		}

		// else return the last item
		return last.next.item;
	}

	public void printQueue(PrintStream stream) {

		// If queue is empty print an appropriate message and return
		if (isEmpty()) {
			stream.println("Queue is Empty");
			return;
		}

		// else print each node
		Node<T> node = last.next;
		for (int i = 1; i <= size; i++) {
			stream.println(node.item);
			node = node.next;
		}
	}

	public int size() {
		return size;
	}

}
