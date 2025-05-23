
import java.util.LinkedList;

/**
 * Author: Greg Pappas
 */
@SuppressWarnings({"rawtypes", "unchecked", "FieldMayBeFinal"})
public class CDoublyLinkedList {

    private class Node {

        private Object data;
        private Node next, prev;

        private Node(Object data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node head;
    private int size;

    public CDoublyLinkedList() {
        this.head = new Node(null, null, null);
        this.head.next = this.head;
        this.head.prev = this.head;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.head == this.head.next;
    }

    public int getSize() {
        return this.size;
    }

    public void addFirst(Object data) {
        Node nn = new Node(data, this.head, this.head.next);
        this.head.next.prev = nn;
        this.head.next = nn;
        this.size++;
    }

    public void addLast(Object data) {
        Node last = this.head.prev;
        Node nn = new Node(data, last, this.head);
        last.next = nn;
        this.head.prev = nn;
        this.size--;
    }

    public void insertionSort() {
        Node lastSorted, sortedWalker;
        Comparable firstUnsortedData;
        for (lastSorted = this.head.next; lastSorted != this.head.prev; lastSorted = lastSorted.next) {
            firstUnsortedData = (Comparable) lastSorted.next.data;
            for (sortedWalker = lastSorted; sortedWalker != head
                    && ((Comparable) sortedWalker.data).compareTo(firstUnsortedData) > 0;
                    sortedWalker = sortedWalker.prev) {
                sortedWalker.next.data = sortedWalker.data;
            }
            sortedWalker.next.data = firstUnsortedData;
        }
    }

    public void mergeSort() {
        Queue q = new Queue();
        CDoublyLinkedList sub1 = new CDoublyLinkedList();
        CDoublyLinkedList sub2 = new CDoublyLinkedList();
        for (Node cur = this.head.next; cur != this.head; cur = cur.next) {
            LinkedList tempList = new LinkedList();
            tempList.addFirst(cur.data);
            q.enq(tempList);
        }
        while (!q.isEmpty()) {
            LinkedList tempList = new LinkedList();
            LinkedList comp1 = q.deq();
            LinkedList comp2 = q.deq();
            if (((Comparable) comp1.getFirst()).compareTo(comp2.getFirst()) < 1) {
                sub1.addLast(comp1);
                sub2.addLast(comp2);
            } else {
                sub1.addLast(comp2);
                sub2.addLast(comp1);
            }
        }
    }

    public boolean isSorted() {
        return false;
    }

    @Override
    public String toString() {
        String result = "{";
        for (Node node = this.head.next; node != this.head; node = node.next) {
            if (node.next != this.head) {
                result += node.data + "->";
            } else {
                result += node.data;
            }
        }
        return result + "}";
    }
}
