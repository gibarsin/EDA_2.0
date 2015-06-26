package tree.expression.operator;

public class Sum implements Operator {
    private static Sum instance = null;

    private Sum() {

    }

    public static Sum getInstance() {
        if(instance == null) {
            instance = new Sum();
        }
        return instance;
    }

    @Override
    public double apply(double op1, double op2) {
        return op1 + op2;
    }
}
