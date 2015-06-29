package tree.expression.operator;

public class Product implements Operator {
    private static Product instance = null;

    private Product() {

    }

    public static Product getInstance() {
        if (instance == null) {
            instance = new Product();
        }
        return instance;
    }

    @Override
    public double apply(double op1, double op2) {
        return op1 * op2;
    }
}