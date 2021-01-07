
/**
 * Sorted circular linked list.
 * 
 * @author Thang Le.
 *
 */
public class Main {
	public static void main_111(String[] args) {
		try {
			Main m = new Main();
			LinkedList list = m.new LinkedList();

			// Creating the linkedlist
			int arr[] = new int[] { 12, 56, 2, 11, 1, 90 };

			/* start with empty linked list */
			Node temp = null;

			/*
			 * Create linked list from the array arr[]. Created linked list will be
			 * 1->2->11->12->56->90
			 */
			for (int i = 0; i < 6; i++) {
				temp = m.new Node(arr[i]);
				list.sortedInsert(temp);
			}

			list.printList();
		} catch (Exception e) {
		}
	}

	class Node {
		int data;
		Node next;

		Node(int d) {
			data = d;
			next = null;
		}
	}

	class LinkedList {
		Node head;

		// Constructor
		LinkedList() {
			head = null;
		}

		public void sortedInsert(Node new_node) {
			Node current = head;
			// Case 1: empty list
			if (current == null) {
				new_node.next = new_node;
				head = new_node;
			}
			// case 2: head smaller/equal than the new node
			else if (current.data <= new_node.data) {
				Node next = current.next;
				while (true) {
					if (new_node.data <= next.data && next.equals(head) == false) {
						current.next = new_node;
						new_node.next = next;
						break;
					} else if (next.equals(head) == true) {
						current.next = new_node;
						new_node.next = head;
						break;
					}

					current = next;
					next = next.next;
				}
			} else
			// case 3: head is bigger than the new node
			{
				Node next = current.next;

				while (true) {
					if (next.equals(head) == true) {
						new_node.next = head;
						head = new_node;
						current.next = head;
						break;
					}
					current = next;
					next = next.next;
				}
			}
		}

		public void printList() {
			if (head != null) {
				Node temp = head;
				do {
					System.out.print(temp.data + " ");
					temp = temp.next;
				} while (temp != head);
			}
		}
	}

}