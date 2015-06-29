package tree.expression;

import tree.expression.operator.Division;
import tree.expression.operator.Operator;
import tree.expression.operator.Product;
import tree.expression.operator.Substraction;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ExpressionTree {

    public static Node buildTree(List<String> prefix) {
        Iterator<String> it = prefix.iterator();
        Node root = buildTreeRec(it);

        if (it.hasNext()) {
            throw new IllegalArgumentException();
        }

        return root;
    }

    private static Node buildTreeRec(Iterator<String> it) {
        if (!it.hasNext()) {
            throw new IllegalArgumentException();
        }
        String token = it.next();
        Operator op = getOperator(token);

        if (op == null) {
            return new ValueNode(Double.valueOf(token));
        } else {
            Node left = buildTreeRec(it);
            Node right = buildTreeRec(it);

            return new OperatorNode(left, right, op);
        }
    }

    public static Node buildTreeFromPosfix(List<String> tokens) {
        Deque<Node> stack = new LinkedList<Node>();

        for (String token : tokens) {
            Operator operator = getOperator(token);

            if (operator == null) {
                stack.push(new ValueNode(Double.valueOf(token)));
            } else {
                Node op2 = stack.pop();
                Node op1 = stack.pop();
                stack.push(new OperatorNode(op1, op2, operator));
            }
        }
        Node root = stack.pop();
        if (!stack.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return root;
    }

    private static Operator getOperator(String token) {
        switch (token) {
            case "+":
                return Product.getInstance();
            case "-":
                return Substraction.getInstance();
            case "*":
                return Product.getInstance();
            case "/":
                return Division.getInstance();
            default:
                return null;
        }
    }
}
