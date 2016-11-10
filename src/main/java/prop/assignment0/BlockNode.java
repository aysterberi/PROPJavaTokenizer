/*
 * Billy G. J. Beltran(bibe1744) & Joakim Berglund(jobe7147)
 * Contact details: billy@caudimordax.org, joakimberglund@live.se
 */

package prop.assignment0;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class BlockNode implements INode {
    Lexeme left_curly;
    Lexeme right_curly;
    INode right;

    @Override
    public Object evaluate(Object[] args) throws Exception {
        Map<String, Object> varMap = new HashMap<>();
        Object[] newArgs = new Object[10];
        newArgs[0] = varMap;
        right.evaluate(newArgs);

        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Object> var : varMap.entrySet()) {
            String varKey = var.getKey();
            DecimalFormat decimalFormat = new DecimalFormat("#0.0");
            String varValue = "";
            if (var.getValue() != null) {
                varValue = decimalFormat.format(var.getValue());
            }
            stringBuilder.append(varKey).
                    append(" = ").
                    append(varValue).
                    append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        builder.append("BlockNode\n");
        tabs++;
        applyTabs(builder, tabs);
        builder.append(left_curly).
                append("\n");
        if (right != null)
            right.buildString(builder, tabs);
        applyTabs(builder, tabs);
        builder.append(right_curly).
                append("\n");
    }

    private void applyTabs(StringBuilder builder, int tabs) {
        for (int i = 0; i < tabs; i++) {
            builder.append("\t");
        }
    }
}
