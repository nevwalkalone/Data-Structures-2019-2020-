import java.io.PrintStream;
import java.util.NoSuchElementException;

//H LEITOURGIA AUTHS THS KLASHS EPEKSHGEITAI ANALUTIKA STO PDF

public class StringStackImpl<T> implements StringStack<T>{
	
	private  Node<T> top;  
	private  int size=0;  
	
	
	public StringStackImpl() {
		
	}
	
	public boolean isEmpty() {
	
		return top==null;
	}

	public void push(T item) {
		Node<T> node=new Node<>(item);
		size++;
		if (!isEmpty()) {
			
			node.next=top;
			
			
		}

		top=node;
	}
	
	public T pop()throws NoSuchElementException{
		
		if (isEmpty()) {
			throw new NoSuchElementException("Stack is Empty");
		}
		Node<T> previous_top=top;
		top=top.next;
		previous_top.next=null;
		size--;
		return previous_top.item;
	}
	
	public T peek() throws NoSuchElementException{
		if (isEmpty()) {
			throw new NoSuchElementException("Stack is Empty");
		}
		return top.item;
	}
	
	public void printStack(PrintStream stream) {
		if (isEmpty()) {
			stream.println("Stack is empty");
			return;
		}
		
			Node<T> node=top;
			while (node!=null) {
				stream.println(node.item);
				node=node.next;
			
		}
	}
	
	public int size() {
		return size;
	}
}
