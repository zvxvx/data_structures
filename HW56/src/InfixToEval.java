import java.util.Hashtable;
import java.util.Scanner;

public class InfixToEval {

    public String infixToPostfix() {
        Scanner kb = new Scanner(System.in);
        System.out.print("Please enter the infix expression to process: ");
        String input = kb.next();
        kb.close();
        System.out.println("------------------");
        System.out.println("Infix expression to evaluate: " + input);
        StringBuilder postfix = new StringBuilder();

        CustomStack stack = new CustomStack(input.length());

        Hashtable<Character, Integer> onStack = new Hashtable<>();
        onStack.put('(', 0);
        onStack.put('^', 5);
        onStack.put('*', 4);
        onStack.put('/', 4);
        onStack.put('%', 4);
        onStack.put('+', 2);
        onStack.put('-', 2);

        Hashtable<Character, Integer> curItem = new Hashtable<>();
        curItem.put('(', 100);
        curItem.put(')', 0);
        curItem.put('^', 6);
        curItem.put('*', 3);
        curItem.put('/', 3);
        curItem.put('%', 3);
        curItem.put('+', 1);
        curItem.put('-', 1);

        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                postfix.append(input.charAt(i));
            } else if (input.charAt(i) == '(') {
                stack.push('(');
            } else if (input.charAt(i) == ')') {
                while (!stack.peek().equals('(')) {
                    Object operator = stack.pop();
                    postfix.append(operator);
                }
                stack.pop();
            } else {
                while (
                    stack.getSize() != 0 &&
                    onStack.get(stack.peek()) > curItem.get(input.charAt(i))
                ) {
                    Object operator = stack.pop();
                    postfix.append(operator);
                }
                stack.push(input.charAt(i));
            }
        }
        while (stack.getSize() != 0) {
            Object operator = stack.pop();
            postfix.append(operator);
        }
        return postfix.toString();
    }

    public void postFixToEval(String input) throws Exception {
        System.out.println(
            "The postfix expression for the input infix is: " + input
        );
        CustomStack stack = new CustomStack(input.length());
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                int num = Character.getNumericValue(input.charAt(i));
                stack.push(num);
            } else {
                char operator = input.charAt(i);
                int right = (int) stack.pop();
                int left = (int) stack.pop();
                switch (operator) {
                    case '+':
                        stack.push(left + right);
                        break;
                    case '-':
                        stack.push(left - right);
                        break;
                    case '*':
                        stack.push(left * right);
                        break;
                    case '/':
                        stack.push(left / right);
                        break;
                    case '^':
                        stack.push((int) Math.pow(left, right));
                }
            }
        }
        if (stack.getSize() == 1) {
            System.out.println(
                "The final result after evaluating the postfix is: " +
                (int) stack.pop()
            );
            System.out.println("------------------");
        } else {
            throw new Exception("Error with postfix input.");
        }
    }
}
