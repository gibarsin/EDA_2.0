package tree.expression.brackets;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ExpressionBrackets {
    private static final List<Character> keys = Arrays.asList('(', '[', '{', ')', ']', '}');

    public static boolean isBalanced(char[] exp) {
        Deque<Character> stack = new LinkedList<>();

        for (Character c : exp) {
            if (isKey(c)) {
                if (isClosedKey(c)) {
                    if (stack.isEmpty() || !stack.pop().equals(getOpening(c))) {
                        return false;
                    }
                } else {
                    stack.push(c);
                }
            } else if (!isDigit(c)) {
                throw new IllegalArgumentException("Not a valid expression");
            }
        }

        return stack.isEmpty();
    }

    private static boolean isDigit(Character c) {
        return c >= '0' && c <= '9';
    }

    private static Character getOpening(Character c) {
        return keys.get(keys.indexOf(c) % 3);
    }

    private static boolean isClosedKey(Character c) {
        return keys.indexOf(c) >= 3;
    }

    private static boolean isKey(Character c) {
        return keys.contains(c);
    }
}
