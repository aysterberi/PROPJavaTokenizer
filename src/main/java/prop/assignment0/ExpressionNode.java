package prop.assignment0;

public class ExpressionNode implements INode {
	public INode termNode;
	public Lexeme operator;
	public INode expressionNode;

	@Override
	public Object evaluate(Object[] args) throws Exception {
		if (termNode != null) {
			double first = (Double) Double.parseDouble(termNode.evaluate(args).toString());
			if (expressionNode != null) {
				double second = (Double) Double.parseDouble(expressionNode.evaluate(args).toString());

				switch (operator.token()) {
					case ADD_OP:
						return first + second;
					case SUB_OP:
						return first - second;
					default:
						assert false : operator.token();
				}
			}
			return first;
		}
		return null;
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
