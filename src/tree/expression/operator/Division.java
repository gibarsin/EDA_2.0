package tree.expression.operator;

public class Division implements Operator {
    private static Division instance = null;

    private Division() {

    }

    public static Division getInstance() {
        if(instance == null) {
            instance = new Division();
        }
        return instance;
    }

    @Override
    public double apply(double op1, double op2) {
        return op1 / op2;
    }
}