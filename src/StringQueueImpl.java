import java.io.PrintStream;
import java.util.NoSuchElementException; 

//H LEITOURGIA AUTHS THS KLASHS EPEKSHGEITAI ANALUTIKA STO PDF

public class StringQueueImpl<T> implements StringQueue <T>{
	
	private  Node<T> head;
	private  int size=0;
	private  Node<T> tail;
	
	public StringQueueImpl() {
		
	}
	
	public  boolean isEmpty() {
		return head==null;
	}
	
	public  void put(T item) {
		
		Node<T> node=new Node<>(item);
		size++;
		if (isEmpty()) {
			head=node;
			
		}
		else {
			
			tail.next=node;
			
			
			
		}

		tail=node;
	}
	
	
	public T get() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue is Empty");
		}
		
		Node<T> oldhead=head;
		
		head=head.next;
		oldhead.next=null;
		size--;
		return oldhead.item;
		}
		
	
	
	public T peek()throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue is Empty");
		}
		return head.item;
	}
	
	
	public void printQueue(PrintStream stream) {
		if (isEmpty()) {
			stream.println("Queue is empty");
			return;
		}
		Node<T> node=head;
		while(node!=null) {
			stream.println(node.item);
			node=node.next;
		}
	}
	
	public int size() {
		return size;
	}
	}

