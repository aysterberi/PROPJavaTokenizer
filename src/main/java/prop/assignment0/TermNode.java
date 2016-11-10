/*
 * Billy G. J. Beltran(bibe1744) & Joakim Berglund(jobe7147)
 * Contact details: billy@caudimordax.org, joakimberglund@live.se
 */

package prop.assignment0;

/**
 * This class represents
 * the carrying out of multiplication and division
 * on given operands and returns a factor.
 * If given only a factor, it will return a factor.
 */
public class TermNode implements INode {
    public INode factorNode;
    public Lexeme operator;
    public INode termNode;

    @Override
    public Object evaluate(Object[] args) throws Exception {
        Object t = args[1];
        double otherBit = (double) factorNode.evaluate(args);
        Token op = (Token) args[2];
        if (t != null && op != null) {
            double result = (double) t;
            args[1] = null;
            args[2] = null;
            switch (op) {
                case MULT_OP:
                    args[1] = mul(result, otherBit);
                    break;
                case DIV_OP:
                    args[1] = div(result, otherBit);
                    break;
                default:
                    assert false : operator.token();
            }
        } else {
            args[1] = otherBit;
        }
        if (operator != null) {
            args[2] = operator.token();
            termNode.evaluate(args);
        }
        return null;
    }

    private double div(double x, double y) {
        return x / y;
    }

    private double mul(double x, double y) {
        return x * y;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        applyTabs(builder, tabs);
        tabs++;
        builder.append("TermNode\n");
        if (factorNode != null)
            factorNode.buildString(builder, tabs);
        if (operator != null) {
            applyTabs(builder, tabs);
            builder.append(operator).
                    append("\n");
        }
        if (termNode != null)
            termNode.buildString(builder, tabs);
    }

    private void applyTabs(StringBuilder builder, int tabs) {
        for (int i = 0; i < tabs; i++) {
            builder.append("\t");
        }
    }
}
