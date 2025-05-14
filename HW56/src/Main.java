
public class Main {
    public static void main(String[] args) throws Exception {
        InfixToEval solver = new InfixToEval();
        String postfix = solver.infixToPostfix();
        solver.postFixToEval(postfix);
    }
}