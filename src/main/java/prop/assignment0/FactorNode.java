package prop.assignment0;

public class FactorNode implements INode {
	public Lexeme leftParen;
	public Lexeme rightParen;
	public Lexeme ident;
	public INode expressionNode;

	@Override
	public Object evaluate(Object[] args) throws Exception {
		Object result = null;

		if (ident != null) {
			switch (ident.token()) {
				case INT_LIT:
					result = ident.value();
					break;
				case IDENT:
					String s = (String) ident.value();
					result = s;
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

	}
}
