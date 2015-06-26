package tree.expression;

import tree.expression.operator.Operator;

public class OperatorNode implements Node {
    private Node op1, op2;
    private Operator operator;

    public OperatorNode(Node op1, Node op2, Operator operator) {
        this.op1 = op1;
        this.op2 = op2;
        this.operator = operator;
    }

    @Override
    public double evaluate() {
        return operator.apply(op1.evaluate(), op2.evaluate());
    }
}
