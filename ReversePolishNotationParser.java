/**
 * Created by ovchval on 25.07.2014.
 */
public class ReversePolishNotationParser {

    public static void main(String[] args) {
        String expr = "3 8 9 * + 6 4 - 1 + /";
        String[] exp = expr.split(" ");
        System.out.println(calculateExpression(exp, exp.length));
    }

    private static int lhs;

    private static int calculateExpression(String[] exp, int length) {
        lhs = length - 1;
        while (isOp(exp[lhs])) --lhs;
        int op = lhs + 1;
        int rhs = intFromString(exp[lhs]);
        int left;

        while (op < length) {
            --lhs;
            if (isOp(exp[lhs])) {
                left = calculateExpression(exp, lhs + 1);
            } else {
                left = intFromString(exp[lhs]);
            }
            rhs = calc(left, rhs, exp[op]);
            ++op;
        }

        return rhs;
    }

    private static boolean isOp(String c) {
        try {
            Integer.valueOf(c);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private static int intFromString(String i) {
        return Integer.valueOf(i);
    }

    private static  int calc(int lhs, int rhs, String op) {
        switch(op) {
            case "+":
                return lhs + rhs;
            case "-":
                return lhs - rhs;
            case "*":
                return lhs * rhs;
            case "/":
                return lhs / rhs;
            default:
                throw new RuntimeException("Operation " + op + " is not supported!");
        }
    }
}
