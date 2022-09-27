package assignment3;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class IDLList<E> {
	
	//Declaring head,tail,ArrayList (to store the nodes)  and size (storing the length of indexed doubly linked list) variables
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	//Constructor defined to initialize the value to the variables
	public IDLList() {
		
		size = 0;
		head = null;
		tail = null;
		indices = new ArrayList<>();
	}
	
	//Add an element at a particular index
	public boolean add(int index, E elem) {
		
		//Checking if the elem is valid or not. If not throwing an exception
		if(elem == null)
			throw new IllegalArgumentException();
		
		//Checking if the index location is valid or not. If not throwing an exception
		else if(index > size || index < 0)
			throw new ArrayIndexOutOfBoundsException();
		
		//Inserting at the end of the indexed doubly linked list
		else if(index == size) {
			Node successor = indices.get(index - 1);
			successor.next = new Node(elem,null,successor);
			tail = successor.next;
			indices.add(tail);
		}
		
		//inserting at the beginning of the indexed doubly linked list
		else if(index == 0) {
			Node successor = indices.get(index);
			successor.prev = new Node(elem,head,null);
			head = successor.prev;
			indices.add(0,head);
			
		}
		
		//inserting in between of the indexed doubly linked list
		else {
			Node successor = indices.get(index);
			Node predecessor = indices.get(index - 1);
			predecessor.next = new Node(elem,successor,predecessor);
			successor.prev = predecessor.next;
			indices.add(index, predecessor.next);
		}
		
		//updating size and returning true
		size++;
		
		
		return true;	
	}
	
	//Adding an element at the beginning of the indexed doubly linked list
	public boolean add(E elem) {
		
		//checking if the elem is valid or not
		if(elem == null)
			throw new IllegalArgumentException();
		
		
		//Checking if the indexed doubly linked list is empty or not
		else if(head == null) {
			head = new Node<E>(elem,null,null);
			tail = head;
		}
		
		//Adding the element at the beginning of the indexed doubly linked list 
		else {
		head.prev = new Node<E>(elem,head,null);
		head = head.prev;
		}
		
		size++;
		indices.add(0,head);
		
		return true;
	}
	
	//Adding an element at the end of the indexed doubly linked list
	public boolean append(E elem) {
		
		//checking if the elem is valid or not
		if(elem == null)
			throw new IllegalArgumentException();
		
		//Checking if the indexed doubly linked list is empty or not
		else if(head == null) {
			head = new Node<E>(elem);
			tail = head;	
		}
		
		//Adding the element at the beginning of the indexed doubly linked list 
		else {
			tail.next = new Node<E>(elem,null,tail);
			tail = tail.next;
		}
		
		indices.add(tail);
		size++;
		
		return true;
	}
	
	//Returning an element at a particular index
	public E get(int index) {
		
		//Checking if the index is valid or not. If not throwing an exception
		if(index > size || size < 0)
			throw new ArrayIndexOutOfBoundsException();
		
		else if(index == size)
			return indices.get(index - 1).data;
		
		return indices.get(index).data;
	}
	
	//Returning the element present at the head
	public E getHead() {
		
		if(size == 0)
			throw new NoSuchElementException();
		
		return head.data;
	}
	
	//Returning the element present at the tail
	public E getLast() {
		
		if(size == 0)
			throw new NoSuchElementException();
		
		return tail.data;
	}
	
	//returning the size of the indexed doubly linked list
	public int size() {
		
		return size;
	}
	
	//Removing an element at the beginning of the indexed doubly linked list
	public E remove () {
		
		//Checking if the indexed doubly linked list is empty or not
		if(size == 0)
			throw new NoSuchElementException();
		
		//If head == tail meaning there is only one element in the indexed doubly linked list
		else if(head == tail) {
			
			E temp = head.data;
			head = null;
			tail = null;
			indices.remove(0);
			size--;
			return temp;
		}
		
		else {
			
			E temp = head.data;
			head.next.prev = null;
			head = head.next;
			indices.remove(0);
			size--;
			return temp;	
		}	
	}
	
	//Removing an element at the end of the indexed doubly linked list
	public E removeLast () {
		
		//Checking if the indexed doubly linked list is empty or not
		if(size == 0)
			throw new NoSuchElementException();
		
		//If head == tail meaning there is only one element in the indexed doubly linked list
		else if(head == tail) {
			E temp = tail.data;
			head = null;
			tail = null;
			indices.remove(0);
			size--;
			return temp;
		}
		
		else {
			
		E temp = tail.data;
		tail.prev.next = null;
		tail = tail.prev;
		indices.remove(size - 1);
		size--;
		return temp;
		}
	}
	
	//Removing an element at a particular index
	public E removeAt (int index) {
		
		//Checking if the index is valid or not. If not throwing an exception
		if(index < 0 || index >= size)
			throw new ArrayIndexOutOfBoundsException();
		
		
		//if the index is 0 it means remove the first element
		else if(index == 0) {
			E temp = head.data;
			head = null;
			tail = null;
			indices.remove(0);
			size--;
			return temp;
		}
		
		//if the index is size - 1 it means remove the last element
		else if(index == size - 1) {
			E temp = tail.data;
			tail.prev.next = null;
			tail.prev = tail;
			indices.remove(index);
			size--;
			return temp;
		}
		
		else {
			E temp = indices.get(index).data;
			Node remove = indices.get(index);
			remove.prev.next = remove.next;
			remove.next.prev = remove.prev;
			indices.remove(index);
			size--;
			return temp;
		
		}	
	}
	
	//removes the first occurrence of elem in the list and returns true. Return false if elem was not in the list.
	public boolean remove (E elem) {
		
		//pointer variable is used to get the position of the element present in the indexed doubly linked list
		int pointer = 0;
		Node current = head;
		
		//iterating until we find the element or the indexed doubly linked list gets empty
		while(current != null && current.data != elem) {
			current = current.next;
			pointer++;
		}
		
		//element not present in the indexed doubly linked list
		if(current == null)
			return false;
		
		//found the element
		else if(current.data == elem) {
			
			if(current == head && current == tail) {
				head = null;
				tail = null;
				head.next = null;
				tail.prev = null;
				indices.remove(0);
			}
			
			else if(current == head) {
				head.next.prev = null;
				head = head.next;
				indices.remove(0);
			}
			
			else if(current == tail) {
				tail.prev.next = null;
				tail = tail.prev;
				indices.remove(size - 1);
			}
			
			else {
				current.prev.next = current.next;
				current.next.prev = current.prev;
				current.next = null;
				current.prev = null;
				indices.remove(pointer);
			}
			
			size--;
			return true;
		}
		
		return false;	
	}
	
	//Representation of the indexed doubly linked list in string format
	public String toString() {
		
		String result = "";
		
		if(head == null)
			result += "No data in the indexed doubly linked list";
		
		else {
			Node current = head;
			while(current != null) {
				result += current.data+" ";
				current = current.next;
			}
		}
		
		return result;
	}
	
	private class Node<E> {
		private E data;
		private Node<E> next;
		private Node<E> prev;
		
		Node(E elem) {
			data = elem;
			this.next = null;
			this.prev = null;
		}
		
		Node(E elem, Node<E> next, Node<E> prev) {
			data = elem;
			this.next = next;
			this.prev = prev;
		}
}
}
