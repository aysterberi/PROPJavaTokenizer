package prop.assignment0;

import java.util.Map;

public class AssignmentNode implements INode {
	public Lexeme ident;
	public Lexeme operator;
	public INode expressionNode;
	public Lexeme semicolon;

	@Override
	public Object evaluate(Object[] args) throws Exception {
		//create var ident
		String identifier = ident.value().toString();
		//eval var ident
		Object varEval = expressionNode.evaluate(args);
		//extract symbol table from args
		//store results of eval with ident
		if (args[0] instanceof Map) {
			Map<String, Object> varMap = (Map) args[0];
			varMap.put(identifier, varEval);
		}
		StringBuilder sb = new StringBuilder();
		sb.append(identifier).append("=").append(varEval);
		return sb.toString();
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
