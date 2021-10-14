import java.io.PrintStream;
import java.util.NoSuchElementException; 


// H LEITOURGIA AUTHS THS KLASHS EPEKSHGEITAI ANALUTIKA STO PDF

public class StringQueueWithOnePointer<T> implements StringQueue<T> {
	public Node<T> last;
	private int size=0;
	
	public StringQueueWithOnePointer() {
		
	}

	public boolean isEmpty() {
		return last==null;
	}
	public void put(T item) {
		Node<T> node=new Node<>(item);
		size++;
		if (isEmpty()) {
			last=node;
			last.next=node;
			
			
		}
		else {
			node.next=last.next;
			last.next=node;
			last=node;
		}
	}
	public T get() throws NoSuchElementException{
		if (isEmpty()) {
			throw new NoSuchElementException("Queue is empty");
		}
		
		Node <T> node=last.next;
		if(size==1) {
			last=null;
			
			
		}
		else {
			last.next=node.next;
			
		}
		size--;
		return node.item;
	}
	public T peek() throws NoSuchElementException{
		if (isEmpty()) {
			throw new NoSuchElementException("QUEUE IS EMPTY");
		}
		
		return last.next.item;
	}
	public void printQueue(PrintStream stream) {
		if (isEmpty()) {
			stream.println("Queue is Empty");
			return;
		}
		
		
			Node <T>node=last.next;
			for(int i=1; i<=size;i++) {
				stream.println(node.item);
				node=node.next;
			
		}
			
	}
		public int size() {
			return size;
		}
	}

