/*
 * Billy G. J. Beltran(bibe1744) & Joakim Berglund(jobe7147)
 * Contact details: billy@caudimordax.org, joakimberglund@live.se
 */

package prop.assignment0;

import java.util.Map;

public class AssignmentNode implements INode {
	Lexeme ident;
	Lexeme operator;
	INode expressionNode;
	Lexeme semicolon;

	@Override
	public Object evaluate(Object[] args) throws Exception {
		String identifier = ident.value().toString();
		expressionNode.evaluate(args);
		if (args[0] instanceof Map) {
			Map<String, Object> varMap = (Map) args[0];
			varMap.put(identifier, args[1]);
		}
        return null;
	}

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        applyTabs(builder, tabs);
        tabs++;
        builder.append("AssignmentNode\n");
        applyTabs(builder, tabs);
        builder.append(ident).append("\n");
        applyTabs(builder, tabs);
        builder.append(operator).append("\n");
        if(expressionNode != null)
            expressionNode.buildString(builder, tabs);
        applyTabs(builder, tabs);
        builder.append(semicolon).append("\n");
    }

    private void applyTabs(StringBuilder builder, int tabs) {
        for (int i = 0; i < tabs; i++) {
            builder.append("\t");
        }
    }
}
