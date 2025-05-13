public class CustomStack {

    private final Object[] stack;
    private int size;

    CustomStack(int cap) {
        stack = new Object[cap];
        this.size = 0;
    }
    public int getSize() {
        return this.size;
    }

    public void push(Object o){
        if (this.size == stack.length) {
            throw new IllegalStateException("Stack is full");
        }
        stack[size] = o;
        size++;
    }

    public Object pop() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty!");
        }
        Object top = stack[size - 1];
        size--;
        return top;
    }

    public Object peek() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty!");
        }
        return stack[size - 1];
    }


    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }
}
