package prop.assignment0;

import java.util.Map;

public class FactorNode implements INode {
	public Lexeme leftParen;
	public Lexeme rightParen;
	public Lexeme ident;
	public INode expressionNode;

	@Override
	public Object evaluate(Object[] args) throws Exception {


		Object result = null;

		if (ident != null) {
			String identName = ident.value().toString();
			Map<String, Object> varMap = null;
			double value;
			if (ident.token() == Token.IDENT) {
				if (args[0] instanceof Map) {
					varMap = (Map) args[0];
					if (varMap.containsKey(identName) && (varMap.get(identName) != null)) {
						value = (Double) varMap.get(identName);
					} else {
						varMap.put(identName, null);
					}
				}
			}
			switch (ident.token()) {
				case INT_LIT:
					value = (Double) ident.value();
					result = value;
					break;
				case IDENT:
					String s = (String) ident.value();
					double d = (Double) varMap.get(s);
					result = d;
					break;
			}
		} else if (expressionNode != null) {
			//recurse expr eval
			result = expressionNode.evaluate(args);
		}
		return result;
	}

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        applyTabs(builder, tabs);
        tabs++;
        builder.append("FactorNode\n");
        if (expressionNode != null) {
            applyTabs(builder, tabs);
            builder.append(leftParen).
                    append("\n");
            expressionNode.buildString(builder, tabs);
            applyTabs(builder, tabs);
            builder.append(rightParen).
                    append("\n");
        } else {
            applyTabs(builder, tabs);
            builder.append(ident).
                    append("\n");
        }
    }

    private void applyTabs(StringBuilder builder, int tabs) {
        for (int i = 0; i < tabs; i++) {
            builder.append("\t");
        }
    }
}
