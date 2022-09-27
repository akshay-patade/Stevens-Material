/*
 * Name: Akshay Pradeep Patade
 * CWID: 20009092
 */

package assignment5;

import java.util.*;

public class Treap < E extends Comparable < E > > {

	//Creating the root and object of class random for the treap
	private Node < E > root;
	private Random priorityGenerator;
	
	//Defining default constructor for the treap
	public Treap() {

		root = null;
		priorityGenerator = new Random();
	}

	//Defining parameterized constructor for the treap
	public Treap(long seed) {
		
		root = null;
		priorityGenerator = new Random(seed);
	}

	//Method to add the node in the treap
	boolean add(E key) {
		
		//Check if the key is null or not. If yes throw an exception
		if(key == null)
			throw new NullPointerException();
		
		//assign a random priority to the node and add the value in the treap
		int priority = priorityGenerator.nextInt();
		return add(key, priority);
	}
	
	//Actual logic of the add method 
	boolean add(E key, int priority) {
		
		//Check if the key is null or not. If yes throw an exception
		if(key == null)
			throw new NullPointerException();
			
		//Creating a new node  and assigning key and priority to the node
		Node < E > new_node = new Node < E >(key, priority);
		
		//keeping track of the node where it is added in the treap
		Stack<Node < E > > path = new Stack < Node < E > >();
		
		//If the root is null i.e. it is an empty treap. New node will be the root of the treap
		if (root == null) {
			
			root = new_node;
			return true;
		}
		
		Node < E > current = root;
		while (true) {

			path.push(current);
			
			//key is already present in the treap so we cannot add
			if(key.compareTo(current.data) == 0)
				return false;
			
			/*if  the new node key is greater than the current node data then we must add the new node  
			 to the right
			 */
			else if (current.data.compareTo(key) < 0) {
				
				if (current.right == null) {
					
					current.right = new_node;
					break;
				}
				
				current = current.right;
			}
			
			/*if  the new node key is less than the current node data then we must add the new node  
			 to the left
			 */
			
			else {
				
				if (current.left == null) {
					
					current.left = new_node;
					break;
				}
				
				current = current.left;
			
			}
		}
		
		reheap(path, new_node);
		return true;
	}

	
	//This function is used so that the property of the heap is not violated
	private boolean reheap(Stack<Node < E > > path, Node < E > new_node) {
		
		
		Node < E > temp = null;
		
		//We will be performing left or right rotaions operations if the heap property is violated
		while (path.peek().priority < new_node.priority) {
			
			temp = path.pop();
			if(!path.isEmpty()) {
				
				
				//if the new node key is less than temp key perform right rotation operation
				if (temp.data.compareTo(new_node.data) > 0) {
					
					temp = temp.rotateRight();

					if (path.peek().data.compareTo(temp.data) > 0)
						path.peek().left = temp;
					
					else
						path.peek().right = temp;
			
				}
				
				else {
					
					//if the new node key is less than temp key perform right rotation operation
					temp = temp.rotateLeft();

					if (path.peek().data.compareTo(temp.data) > 0)
						path.peek().left = temp;
					
					else
						path.peek().right = temp;
				} 
			}
			
			else {
				
				if (new_node.equals(temp.left)) {
					
					root = temp.rotateRight();
					return true;
				} 
				else {
				
					root = temp.rotateLeft();
					return true;
				}
			}
		}
		return true;
		
	}
	
	//Function to delete a node in the treap
	boolean delete(E key) {
		
		//If the key is null throw an exception
		if(key == null)
			throw new NullPointerException();
		
		//If the root is not null and there is a key in the treap we will traverse the treap
		else if (root != null && find(key)) {
			
			root = delete(root, key);
			return true;
		}
		
		return false;

	}
	
	//Actual logic of the delete function
	private Node < E > delete(Node < E > current, E key) {
		
		if(current == null)
			return current;
		
		else {
			
			if(key.compareTo(current.data) > 0)
				current.right = delete(current.right, key);
			
			else if(key.compareTo(current.data) < 0)
				current.left = delete(current.left, key);
			
			else {
				
				if(current.right == null)
					current = current.left;
				
				else if(current.left == null)
					current = current.right;
				
				else {
					
					if(current.left.priority > current.right.priority) {
						
						current = current.rotateRight();
						current.right = delete(current.right, key);
					}
					
					else {
						
						current = current.rotateLeft();
						current.left = delete(current.left, key);
					}
					
				}
				
			}
			
		}
		
		return current;
	}
	
	//Function to find a key in the treap
	private boolean find(Node < E > root, E key) {
		
		//If the key is null throw an exception
		if(key == null)
			throw new NullPointerException();
		
		Node < E > temp = root;
		
		//Traverse until we get to the end of the tree
		while(temp != null) {
			
			//Key found return true
			if(temp.data == key)
				return true;
			
			else if(temp.data.compareTo(key) < 0)
				temp = temp.right;
			
			else
				temp = temp.left;
			
		}
		
		//key is not found return false
		return false;
	}
	
	//Function to find a key in the treap
	public boolean find(E key) {
		
		//If the key is null throw an exception
		if(key == null)
			throw new NullPointerException();
		
		Node < E > temp = root;
		
		//Traverse until we get to the end of the tree
		while(temp != null) {
			
			//Key found return true
			if(temp.data == key)
				return true;
			
			else if(temp.data.compareTo(key) < 0)
				temp = temp.right;
			
			else
				temp = temp.left;
			
		}
		
		//key is not found return false
		return false;
	}


	//Function to print the treap
	public String toString() {
		
		//Using StringBuilder instead of string because it is efficient. Also stringBuilder is mutable
		StringBuilder temp = new StringBuilder();
		preOrder(root, 0, temp);	
		return temp.toString();
	}

	//We will be first traversing root node then left node and finally right node
	public void preOrder(Node < E > current, int height, StringBuilder temp) {
		
		for(int i = 0; i < height; i++)
			temp.append(" ");
		
		if(current == null)
			temp.append("null\n");
		
		else {
			temp.append(current.toString());
			temp.append("\n");
			preOrder(current.left, height + 1, temp);
			preOrder(current.right, height + 1, temp);
		}
		
	}
	
	private static class Node <E> {
		
		//Declaring the parameters for each node in the tree;
		public E data;
		public int priority;
		Node <E> left;
		Node <E> right;
		
		//Using parameterized constructor to set the values for the node
		public Node (E data, int priority) {
			
			if(data == null)
				throw new NullPointerException();
			
			this.data = data;
			this.priority = priority;
			left = null;
			right = null;
		
		}
		
		//Function to perform left rotation
		Node <E> rotateLeft() {
			
			Node new_root = this.right;
			Node temp = this.right.left;
			
			new_root.left = this;
			this.right = temp;
			
			return new_root;
		}
		
		//Function to perform right rotation
		Node <E> rotateRight() {
			
			Node new_root = this.left;
			Node temp = this.left.right;
			
			new_root.right = this;
			this.left = temp;
			
			return new_root;
			
		}
		
		public String toString() {
			
			return "(key ="+this.data+", priority ="+this.priority+")";
		}
	
	}

	public static void main(String[] args) {
		Treap < Integer > treaps = new Treap < Integer >();

		treaps.add(4, 19);
		treaps.add(2, 31);
		treaps.add(6, 70);
		treaps.add(1, 84);
		treaps.add(3, 12);
		treaps.add(5, 83);
		treaps.add(7, 26);

		
		System.out.println("Below is the Treap \n" + treaps.toString());
		
		System.out.println("Is the node present in the treap: " + treaps.find(18));
		
		System.out.println("Is the node deleted from the treap : " + treaps.delete(5));
		
		
		System.out.println("Below is the Treap \n" + treaps.toString());


	}
}