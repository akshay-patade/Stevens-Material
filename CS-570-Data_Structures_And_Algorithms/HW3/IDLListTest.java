package assignment3;

public class IDLListTest {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		//Creating a indexed doubly linked list
		
		System.out.println("Creating a new indexed doubly linked list");
		IDLList<Integer> dll = new IDLList<Integer>();
		
		System.out.println("Appending elements at the end of the linked list");
		dll.append(2);
		dll.append(9);
		dll.append(71);
		
		System.out.println("Adding elements at a particular index");
		dll.add(1,21);
		dll.add(4,43);
		dll.add(2,92);
		dll.add(3,28);
		dll.add(5,54);
		dll.add(3,71);
		dll.add(5,71);
		dll.add(6,71);
		
		System.out.println("Adding elements at a beginning of the indexed doubly linked list");
		dll.add(98);
		dll.add(71);
		
		System.out.println("Element at index  5 is "+dll.get(5));
		System.out.println("Element at index 7 is "+dll.get(7));
		System.out.println("Element at index 2 is "+dll.get(2));
		
		System.out.println("Printing indexed doubly linked list");
		System.out.println(dll.toString());
		
		System.out.println("Size of the indexed doubly linked list is "+dll.size());
		
		
		System.out.println("Element present at head is "+dll.getHead());
		System.out.println("Element present at tail is "+dll.getLast());
		
		System.out.println("Element removed from the head is "+dll.remove());
		System.out.println("Element removed from the tail is "+dll.removeLast());
		System.out.println("Element removed from the head is "+dll.remove());
		System.out.println("Element removed from the tail is "+dll.removeLast());
		
		System.out.println("Element removed from index 3 is  "+dll.removeAt(3));
		System.out.println("Element removed from index 1 is  "+dll.removeAt(1));
		
		System.out.println("Printing indexed doubly linked list");
		System.out.println(dll.toString());
		
		System.out.println("Status if 53 is removed or not is: "+dll.remove(53));
		System.out.println("Status if 71 is removed or not is: "+dll.remove(71));
		
		System.out.println("Printing indexed doubly linked list");
		System.out.println(dll.toString());
		
		System.out.println("Size of the indexed doubly linked list is "+dll.size());
		}

}
