import java.util.LinkedList;

public class DoublyLinkedList {

	ListNode root;

	public DoublyLinkedList() {

	}
	public DoublyLinkedList(int x) {
		root = new ListNode(x);
	}

	public ListNode get(int index) {
		ListNode tempListNode = root;
		for (int i = 0; i < index; i ++) {
			if (tempListNode.hasNext()) {
				tempListNode = tempListNode.next();
			}
			else {
				return null;
			}
		}
		return tempListNode;
	}
	public void add(int x) {
		if (root == null) {
			root = new ListNode(x);
		}
		else {
			ListNode listNode = new ListNode(x);
			listNode.setPrevious(end());
			end().setNext(listNode);
		}
	}
	public void add(ListNode listNode, int index) {

	}
	public ListNode end() {
		if (!root.hasNext()) {
			return root;
		}
		else {
			ListNode tempListNode = root;
			while (tempListNode.hasNext()) {
				tempListNode = tempListNode.next();
			}
			return tempListNode;
		}
	}
	public String toString() {
		ListNode tempListNode = root;
		String temp = "" + tempListNode.getValue();

		while (tempListNode.hasNext()) {
			tempListNode = tempListNode.next();
			temp += ", " + tempListNode.getValue();
		}

		return temp;
	}
	public void clear() {
		root = null;
	}








	public class ListNode {

		int value;
		int index;
		ListNode previousListNode;
		ListNode nextListNode;

		public ListNode(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int i) {
			index = i;
		}

		public ListNode previous() {
			return previousListNode;
		}
		public ListNode next() {
			return nextListNode;
		}
		public void setPrevious(ListNode listNode) {
			previousListNode = listNode;
		}
		public void setNext(ListNode listNode) {
			nextListNode = listNode;
		}
		public boolean hasPrevious() {
			return !(previousListNode == null);
		}
		public boolean hasNext() {
			return !(nextListNode == null);
		}
		public String toString() {
			return "" + value;
		}

	}
}