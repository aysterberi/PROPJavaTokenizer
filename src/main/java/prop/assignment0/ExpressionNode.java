/*
 * Billy G. J. Beltran(bibe1744) & Joakim Berglund(jobe7147)
 * Contact details: billy@caudimordax.org, joakimberglund@live.se
 */

package prop.assignment0;

public class ExpressionNode implements INode {
    public INode termNode;
    public Lexeme operator;
    public INode expressionNode;

    @Override
    public Object evaluate(Object[] args) throws Exception {
        Object t = args[1];
        Token op = (Token) args[2];
        if (t != null && op != null) {
            double result = (double) t;
            args[1] = null;
            args[2] = null;
            termNode.evaluate(args);
            switch (op) {
                case ADD_OP:
                    args[1] = add(result, (double) args[1]);
                    break;
                case SUB_OP:
                    args[1] = sub(result, (double) args[1]);
                    break;
                default:
                    assert false : operator.token();
            }
        } else {
            termNode.evaluate(args);
        }
        if (operator != null) {
            args[2] = operator.token();
            expressionNode.evaluate(args);
        }
        return null;
    }

    private double add(double x, double y) {
        return x + y;
    }

    private double sub(double x, double y) {
        return x - y;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        applyTabs(builder, tabs);
        tabs++;
        builder.append("ExpressionNode\n");
        if (termNode != null)
            termNode.buildString(builder, tabs);
        if (operator != null) {
            applyTabs(builder, tabs);
            builder.append(operator).
                    append("\n");
        }
        if (expressionNode != null)
            expressionNode.buildString(builder, tabs);
    }

    private void applyTabs(StringBuilder builder, int tabs) {
        for (int i = 0; i < tabs; i++) {
            builder.append("\t");
        }
    }
}
