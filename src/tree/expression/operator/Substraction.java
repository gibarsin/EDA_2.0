package tree.expression.operator;

public class Substraction implements Operator {
    private static Substraction instance = null;

    private Substraction() {

    }

    public static Substraction getInstance() {
        if (instance == null) {
            instance = new Substraction();
        }
        return instance;
    }

    @Override
    public double apply(double op1, double op2) {
        return op1 - op2;
    }
}
