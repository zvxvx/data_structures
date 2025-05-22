
import java.util.LinkedList;

public class Queue {

    private LinkedList<LinkedList> items = new LinkedList<>();

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void enq(LinkedList e) {
        items.addLast(e);
        System.out.println(e + " has been added to the queue");
    }

    public LinkedList deq() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty!");
        }
        LinkedList e = items.removeFirst();
        System.out.println(e + "has been removed from the queue.");
        return e;
    }
}
