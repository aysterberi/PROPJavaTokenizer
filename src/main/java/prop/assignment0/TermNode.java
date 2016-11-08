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
		double first = Double.parseDouble(factorNode.evaluate(args).toString());
		if (operator != null) {
			//ask for the right term eval
			double second = Double.parseDouble(termNode.evaluate(args).toString());
			switch (operator.token()) {
				case MULT_OP:
					return first * second;
				case DIV_OP:
					return first / second;
				default:
					assert false : operator.token();
			}
		}
		return first;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {

	}
}
