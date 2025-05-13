import java.beans.Customizer;
import java.util.Hashtable;
import java.util.Stack;
import java.util.regex.PatternSyntaxException;

public class Main {
    public static void main(String[] args){
        String postfix = infixToPostfix("2*3+4");
        System.out.println(postfix);
       System.out.println(postFixToEval(postfix));
    }

    public static String infixToPostfix(String input) {
        StringBuilder postfix = new StringBuilder();
        CustomStack stack = new CustomStack(input.length());

        Hashtable<Character, Integer> onStack = new Hashtable<Character,
                Integer>();
        onStack.put('(', 0);
        onStack.put('^', 5);
        onStack.put('*', 4);
        onStack.put('/', 4);
        onStack.put('%', 4);
        onStack.put('+', 2);
        onStack.put('-', 2);

        Hashtable<Character, Integer> curItem = new Hashtable<Character,
                Integer>();
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
                while (stack.getSize() != 0 && onStack.get(stack.peek()) > curItem.get(input.charAt(i))) {
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

    public static int postFixToEval(String input) {
        CustomStack stack = new CustomStack(input.length());

        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                int num = Character.getNumericValue(input.charAt(i));
                stack.push(num);
            } else {
                char operator = input.charAt(i);
                int right = (int)stack.pop();
                int left = (int)stack.pop();
                switch(operator) {
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
                        stack.push(Math.pow(left,right));
                }
            }
        }
        if (stack.getSize() == 1) {
            return (int)stack.pop();
        } else {
            return -99999999;
        }
    }
}