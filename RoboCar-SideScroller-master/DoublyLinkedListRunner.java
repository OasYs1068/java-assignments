public class DoublyLinkedListRunner {

	public DoublyLinkedListRunner() {
		DoublyLinkedList list = new DoublyLinkedList();

		list.add(5);
		list.add(4);
		list.add(3);
		list.add(2);
		list.add(1);

		System.out.println(list);

		System.out.println(list.end());

		list.clear();

		System.out.println(list);

		//DoublyLinkedList.ListNode listNode = new DoublyLinkedList.ListNode(5);




	}

	public static void main(String[]args) {
		DoublyLinkedListRunner app = new DoublyLinkedListRunner();
	}
}